package mobile.gachonapp.api;

import lombok.RequiredArgsConstructor;
import mobile.gachonapp.api.response.Result;
import mobile.gachonapp.api.response.SuccessResponse;
import mobile.gachonapp.domain.dto.CourseResponse;
import mobile.gachonapp.domain.dto.CourseStatusRequest;
import mobile.gachonapp.service.CourseService;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseApiController {

    private final CourseService courseService;

    @GetMapping("/api/course")
    public Result getAssignments(@CookieValue(name = "MoodleSession") String session) {
        List<CourseResponse> courseResponses = courseService.getCourses(session);
        return new Result(SuccessResponse.ASSIGNMENT_LIST_SUCCESS,courseResponses);
    }

    @PostMapping("/api/course/view-status")
    public Result updateSubjectStatus(@CookieValue(name = "MoodleSession") String session,
                                      List<CourseStatusRequest> courseStatusRequests) {

        courseService.updateCourseStatus(session, courseStatusRequests);
        //json에서 subjectname 과 상태를 받는다.
        //해당 session으로 사용자 찾고 해당 사용자의 subjectname을 찾아 상태를 바꾼다
        //리턴값은 data 없이 상태만 반환
        return null;
    }

    /*@GetMapping("/api/subject/webex")
    public Result getWebLink() {
        //모든 과목 웹엑스 링크 준다 (링크가 없는 과목 어떻게 해결?? -> 우리가 db에 직접 넣어라...)
        //webLink 반환
        return null;
    }*/






}
