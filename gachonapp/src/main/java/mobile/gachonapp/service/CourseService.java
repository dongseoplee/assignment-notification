package mobile.gachonapp.service;

import lombok.RequiredArgsConstructor;
import mobile.gachonapp.domain.Course;
import mobile.gachonapp.domain.User;
import mobile.gachonapp.domain.dto.CourseResponse;
import mobile.gachonapp.domain.dto.CourseStatusRequest;
import mobile.gachonapp.repository.CourseRepository;
import mobile.gachonapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository UserRepository;

    //
    public List<CourseResponse> getCourses(String session) {
        User findUser = UserRepository.findBySession(session).get();
        List<Course> findCourses = courseRepository.findCoursesByUserId(findUser.getUserId());
        return findCourses.stream()
                .map(CourseResponse::createResponse)
                .collect(Collectors.toList());
    }

    public void updateCourseStatus(String session, List<CourseStatusRequest> courseStatusRequests) {
        User findUser = UserRepository.findBySession(session).get();
        List<Course> findCourses = courseRepository.findCoursesByUserId(findUser.getUserId());
        updateViewStatuses(findCourses, courseStatusRequests);
    }

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
