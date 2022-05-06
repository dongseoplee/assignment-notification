package mobile.gachonapp.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.response.Result;
import mobile.gachonapp.api.response.SuccessResponse;
import mobile.gachonapp.domain.User;
import mobile.gachonapp.domain.dto.AssignmentResponse;
import mobile.gachonapp.domain.dto.SubmitStatusRequest;
import mobile.gachonapp.service.AssignmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AssignmentApiController {

    private final AssignmentService assignmentService;

    @GetMapping("/api/assignment/list")
    public Result getAssignments(@RequestAttribute User user){
        List<AssignmentResponse> assignmentResponses = assignmentService.getAssignments(user.getUserId());
        return new Result<>(SuccessResponse.ASSIGNMENT_LIST_SUCCESS,assignmentResponses);
    }

    @GetMapping("/api/assignment/submit-list")
    public Result getSubmittedAssignment(@RequestAttribute User user){
        List<AssignmentResponse> assignmentResponses = assignmentService.getSubmittedAssignments(user.getUserId());
        return new Result<>(SuccessResponse.ASSIGNMENT_SUBMIT_SUCCESS,assignmentResponses);
    }

    @GetMapping("/api/assignment/notsubmit-list")
    public Result getNotSubmittedAssignment(@RequestAttribute User user){
        List<AssignmentResponse> assignmentResponses = assignmentService.getNotSubmittedAssignments(user.getUserId());
        return new Result<>(SuccessResponse.ASSIGNMENT_NOTSUBMIT_SUCCESS,assignmentResponses);
    }



    /*@PostMapping("/api/assignment/submit-status")
    public ResultSuccess updateAssignmentSubmitStatus(
            @CookieValue(name = "MoodleSession") String session,
            @RequestBody SubmitStatusRequest submitStatusRequest) {
        assignmentService.updateSubmitStats(session,submitStatusRequest);
        return new ResultSuccess<>(SuccessResponse.ASSIGNMENT_SUBMIT_SUCCESS,null);
    }*/
}
