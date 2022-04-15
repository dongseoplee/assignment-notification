package mobile.gachonapp.api;


import lombok.RequiredArgsConstructor;
import mobile.gachonapp.api.response.Result;
import mobile.gachonapp.api.response.SuccessResponse;
import mobile.gachonapp.dto.SubmitStatusRequest;
import mobile.gachonapp.service.AssignmentService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AssignmentApiController {

    private final AssignmentService assignmentService;

    @GetMapping("/api/assignment/list")
    public Result getAssignment(@CookieValue(name = "MoodleSession") String session){
        assignmentService.getAssignmentList(session);
        List list = new ArrayList(Arrays.asList(1,session));
        return new Result<>(SuccessResponse.ASSIGNMENT_LIST_SUCCESS,list);
    }

    //TODO update 메소드로 리팩토링
    //dto에 리스트로 담기기
    @PostMapping("/api/assignment/submit-status")
    public Result updateAssignmentSubmitStatus(
            @CookieValue(name = "MoodleSession") String Session,
            @RequestBody SubmitStatusRequest submitStatusRequest) {

        assignmentService.updateSubmitStats(submitStatusRequest);
        return null;
    }
}
