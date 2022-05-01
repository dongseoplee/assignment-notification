package mobile.gachonapp.api;


import lombok.RequiredArgsConstructor;
import mobile.gachonapp.api.response.Result;
import mobile.gachonapp.api.response.SuccessResponse;
import mobile.gachonapp.domain.dto.AssignmentResponse;
import mobile.gachonapp.domain.dto.SubmitStatusRequest;
import mobile.gachonapp.service.AssignmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AssignmentApiController {

    private final AssignmentService assignmentService;

    @GetMapping("/api/assignment/list")
    public Result getAssignments(@CookieValue(name = "MoodleSession") String session){
        List<AssignmentResponse> assignmentResponses = assignmentService.getAssignments(session);
        return new Result<>(SuccessResponse.ASSIGNMENT_LIST_SUCCESS,assignmentResponses);
    }

    @PostMapping("/api/assignment/submit-status")
    public Result updateAssignmentSubmitStatus(
            @CookieValue(name = "MoodleSession") String session,
            @RequestBody SubmitStatusRequest submitStatusRequest) {
        assignmentService.updateSubmitStats(session,submitStatusRequest);
        return new Result<>(SuccessResponse.ASSIGNMENT_LIST_SUCCESS,null);
    }
}
