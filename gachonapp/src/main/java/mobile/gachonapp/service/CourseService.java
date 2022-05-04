package mobile.gachonapp.service;

import lombok.RequiredArgsConstructor;
import mobile.gachonapp.domain.Course;
import mobile.gachonapp.domain.User;
import mobile.gachonapp.domain.dto.CourseResponse;
import mobile.gachonapp.domain.dto.CourseStatusRequest;
import mobile.gachonapp.exception.NotFindSessionException;
import mobile.gachonapp.repository.CourseRepository;
import mobile.gachonapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    //
    public List<CourseResponse> getCourses(String userId) {
        User findUser = userRepository.findByUserId(userId)
                .orElseThrow(NoSuchElementException::new);

        List<Course> findCourses = courseRepository.findByUserId(findUser.getUserId());

        return findCourses.stream()
                .map(CourseResponse::createResponse)
                .collect(Collectors.toList());
    }

    public void updateCourseStatus(String userId, List<CourseStatusRequest> courseStatusRequests) {
        User findUser = userRepository.findByUserId(userId)
                .orElseThrow(NoSuchElementException::new);
        List<Course> findCourses = courseRepository.findByUserId(findUser.getUserId());
        updateViewStatuses(findCourses, courseStatusRequests);
    }


    //for문 리팩토링
    private void updateViewStatuses(List<Course> findCourses, List<CourseStatusRequest> courseStatusRequests) {
        for (Course course : findCourses) {
            for (CourseStatusRequest courseStatusRequest : courseStatusRequests) {
                if (course.isSame(courseStatusRequest)) {
                    course.changeViewStatus(courseStatusRequest.getCourseViewStatus());
                }
            }
        }
    }


}
