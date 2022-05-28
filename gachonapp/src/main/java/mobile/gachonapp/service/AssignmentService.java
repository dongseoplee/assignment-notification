package mobile.gachonapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.domain.Assignment;
import mobile.gachonapp.domain.Course;
import mobile.gachonapp.domain.User;
import mobile.gachonapp.domain.dto.AssignmentResponse;
import mobile.gachonapp.repository.AssignmentRepository;
import mobile.gachonapp.repository.CourseRepository;
import mobile.gachonapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AssignmentService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final AssignmentRepository assignmentRepository;
    private final CrawlingService crawlingService;


    public List<AssignmentResponse> getAssignments(String userId) {

        User findUser = userRepository.findByUserId(userId)
                .orElseThrow(NoSuchElementException::new);

        /*List<Course> crawledCourses = crawlingService.crawlAssignments(findUser.getSession());
        List<Assignment> findAssignments = assignmentRepository.findByUserId(userId);*/

        /*//user와 연관관계 매핑
        for (Course crawledCourse : crawledCourses) {
            crawledCourse.setUser(findUser);
            for (Assignment assignment : crawledCourse.getAssignments()) {
                assignment.setUser(findUser);
            }
        }

        //db에 사용자가 없는 경우
        if (findAssignments.isEmpty()) {
            courseRepository.saveAll(crawledCourses);
            List<Assignment> toBeSubmittedAssignments = assignmentRepository.findToBeSubmitByUserId(findUser.getUserId());

            return toBeSubmittedAssignments.stream()
                    .map(AssignmentResponse::createResponse)
                    .collect(Collectors.toList());
        }


        List<Assignment> crawledAssignments = new ArrayList<>();

        //크롤링 assignment을 한 리스트에
        for (Course crawledCours : crawledCourses) {
            for (Assignment assignment : crawledCours.getAssignments()) {
                crawledAssignments.add(assignment);
            }
        }

        //추가된 assignment 모으기
        List<Assignment> addedAssignments = crawledAssignments.stream()
                .filter(list -> findAssignments.stream().noneMatch( s-> s.getAssignmentName().equals(list.getAssignmentName())))
                .collect(Collectors.toList());

        for (Assignment addedAssignment : addedAssignments) {
            System.out.println("addedAssignments = " + addedAssignment.getAssignmentName());
        }

        //추가된게 assignment가 있으면 추가한다.
        if(!addedAssignments.isEmpty()) {
            for (Assignment addedAssignment : addedAssignments) {
                addedAssignment.setUser(findUser);
                assignmentRepository.save(addedAssignment);
            }
        }

        //assignment 내용이 변경된게 있으면 변경한다.
        for (Assignment findAssignment : findAssignments) {
            for (Assignment crawledAssignment : crawledAssignments) {
                if(findAssignment.isSameAssignment(crawledAssignment)) {
                    findAssignment.updateInfo(crawledAssignment);
                }
            }
        }*/

        List<Assignment> toBeSubmittedAssignments = assignmentRepository.findToBeSubmitByUserId(findUser.getUserId());

        return toBeSubmittedAssignments.stream()
                .map(AssignmentResponse::createResponse)
                .collect(Collectors.toList());
    }

    // 크롤링을 새로해서 가져오는 경우 - 새로고침 - (세션이 만료되었을때 처리 로직을 고민해야한다.)
    // 새로고침시 세션을 서버에 주었을때 세션만료이면() 로그인 진행 후 크롤링
    // 과목비교x assignment만 비교
    // 세션만료가 아니면 기존 세션으로 크롤링
    public void refreshAssignments(String userId) {
        User findUser = userRepository.findByUserId(userId)
                .orElseThrow(NoSuchElementException::new);
        List<Course> crawledCourses = crawlingService.crawlAssignments(findUser.getSession());
        List<Assignment> findAssignments = assignmentRepository.findByUserId(userId);

        //user와 연관관계 매핑
        for (Course crawledCourse : crawledCourses) {
            crawledCourse.setUser(findUser);
            for (Assignment assignment : crawledCourse.getAssignments()) {
                assignment.setUser(findUser);
            }
        }



        //TODO: save시 벌크로 저장하기!
        //db에 사용자가 없는 경우(앱에 처음으로 로그인 한 사용자인 경우)
        if (findAssignments.isEmpty()) {
            courseRepository.saveAll(crawledCourses);
        }

        List<Assignment> crawledAssignments = new ArrayList<>();

        //크롤링한 assignment를 리스트에 담는다
        for (Course crawledCours : crawledCourses) {
            for (Assignment assignment : crawledCours.getAssignments()) {
                crawledAssignments.add(assignment);
            }
        }

        //가천대 홈페이지에서 추가된 assignment 리스트를 가져온다
        List<Assignment> addedAssignments = crawledAssignments.stream()
                .filter(list -> findAssignments.stream().noneMatch( s-> s.getAssignmentName().equals(list.getAssignmentName())))
                .collect(Collectors.toList());

        //추가된 assignment가 있으면 추가한다.
        if(!addedAssignments.isEmpty()) {
            for (Assignment addedAssignment : addedAssignments) {
                addedAssignment.setUser(findUser);
                assignmentRepository.save(addedAssignment);
            }
        }
        //assignment 내용이 변경된게 있으면 변경한다.
        updateChangedAssignmnet(findAssignments, crawledAssignments);

    }

    private void updateChangedAssignmnet(List<Assignment> findAssignments, List<Assignment> crawledAssignments) {
        for (Assignment findAssignment : findAssignments) {
            for (Assignment crawledAssignment : crawledAssignments) {
                if(findAssignment.isSameAssignment(crawledAssignment)) {
                    findAssignment.updateInfo(crawledAssignment);
                }
            }
        }
    }

    public List<AssignmentResponse> getSubmittedAssignments(String userId) {
        User findUser = userRepository.findByUserId(userId)
                .orElseThrow(NoSuchElementException::new);
        List<Assignment> findAssignment = assignmentRepository.findSubmitByUserId(findUser.getUserId());
        return findAssignment.stream()
                .map(AssignmentResponse::createResponse)
                .collect(Collectors.toList());
    }

    public List<AssignmentResponse> getNotSubmittedAssignments(String userId) {
        User findUser = userRepository.findByUserId(userId)
                .orElseThrow(NoSuchElementException::new);

        List<Assignment> findAssignment = assignmentRepository.findNotSubmitByUserId(findUser.getUserId());
        return findAssignment.stream()
                .map(AssignmentResponse::createResponse)
                .collect(Collectors.toList());
    }

}
