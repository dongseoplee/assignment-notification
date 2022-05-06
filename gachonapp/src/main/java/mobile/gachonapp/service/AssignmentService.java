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
        List<Course> crawledCourses = crawlingService.crawlAssignments(findUser.getSession());

        //처음사용자 -> 처음사용자는 course가 비어있다
        if (findUser.getCourses().isEmpty()) {
            //연관관계 매핑
            for (Course crawledCourse : crawledCourses) {
                crawledCourse.setUser(findUser);
                for (Assignment assignment : crawledCourse.getAssignments()) {
                    assignment.setUser(findUser);
                }
            }
            courseRepository.saveAll(crawledCourses);
        }

        List<Assignment> findAssignments = assignmentRepository.findByUserId(userId);
        List<Assignment> crawledAssignments = new ArrayList<>();

        //리스트 비교후 업데이트 로직
        for (Course crawledCours : crawledCourses) {
            for (Assignment assignment : crawledCours.getAssignments()) {
                crawledAssignments.add(assignment);
            }
        }

        System.out.println("findAssignments.size() = " + findAssignments.size());
        for(int i = 0 ; i < findAssignments.size(); i++) {
            System.out.println("findAssignments = " + findAssignments.get(i).getAssignmentName());
            System.out.println("crawledAssignments = " + crawledAssignments.get(i).getAssignmentName());
        }


        //if()findAssignment crawledAssignments
        List<Assignment> toBeSubmittedAssignments = assignmentRepository.findToBeSubmitByUserId(findUser.getUserId());

        return toBeSubmittedAssignments.stream()
                .map(AssignmentResponse::createResponse)
                .collect(Collectors.toList());
    }

    // 크롤링을 새로해서 가져오는 경우 - 새로고침 - (세션이 만료되었을때 처리 로직을 고민해야한다.)
    // 새로고침시 세션을 서버에 주었을때 세션만료이면() 로그인 진행 후 크롤링
    // 과목비교x assignment만 비교
    // 세션만료가 아니면 기존 세션으로 크롤링
    public List<AssignmentResponse> refreshAssignments(String session) {
        User findUser = userRepository.findBySession(session)
                .orElseThrow(NoSuchElementException::new);

        List<Course> CrawledCourses = crawlingService.crawlAssignments(session);
        List<Assignment> findCourse = assignmentRepository.findToBeSubmitByUserId(findUser.getUserId());
        //course 비교  findCourse  == CrawledCourses


        //dto 생성후 반환 findCourse데이터 변경
        return findCourse.stream()
                .map(AssignmentResponse::createResponse)
                .collect(Collectors.toList());

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

    /*public void updateSubmitStats(String session, SubmitStatusRequest submitStatusRequest) {

        User findUser = userRepository.findBySession(session)
                .orElseThrow(NoSuchElementException::new);

        Assignment findAssignment = assignmentRepository.findByUserIdAndAssignmentName(findUser.getUserId(),
                submitStatusRequest.getAssignmentName()).orElseThrow(NoSuchElementException::new);

        findAssignment.changeSubmitStatus(submitStatusRequest.getAssignmentSubmitStatus());

    }*/


}
