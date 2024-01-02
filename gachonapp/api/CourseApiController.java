package mobile.gachonapp.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.response.NoDataResult;
import mobile.gachonapp.api.response.Result;
import mobile.gachonapp.api.response.SuccessResponse;
import mobile.gachonapp.domain.User;
import mobile.gachonapp.domain.dto.CourseResponse;
import mobile.gachonapp.domain.dto.CourseStatusRequest;
import mobile.gachonapp.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CourseApiController {

    private final CourseService courseService;

    @GetMapping("/api/course")
    public Result getCourses(@RequestAttribute User user) {
        List<CourseResponse> courseResponses = courseService.getCourses(user.getUserId());
        return new Result(SuccessResponse.COURSE_LIST_SUCCESS,courseResponses);
    }

    @GetMapping("/api/course/view-status")
    public NoDataResult updateCourseViewStatus(@RequestAttribute User user,
                                               @RequestBody List<CourseStatusRequest> courseStatusRequests) {

        courseService.updateCourseViewStatus(user.getSession(), courseStatusRequests);

        for (CourseStatusRequest courseStatusRequest : courseStatusRequests) {
            log.info("NAME :" + courseStatusRequest.getCourseName() + "STATUS" + courseStatusRequest.getCourseViewStatus());
        }
        return new NoDataResult(SuccessResponse.COURSE_VIEWSTATUS_SUCCESS);
    }

}
