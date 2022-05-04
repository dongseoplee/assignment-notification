package mobile.gachonapp.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.response.ResultSuccess;
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
    public ResultSuccess getAssignments(@CookieValue(name = "MoodleSession") String session){
        List<AssignmentResponse> assignmentResponses = assignmentService.getAssignments(session);
        return new ResultSuccess<>(SuccessResponse.ASSIGNMENT_LIST_SUCCESS,assignmentResponses);
    }

    @GetMapping("/api/assignment/submit-list")
    public ResultSuccess getSubmittedAssignment(@CookieValue(name = "MoodleSession") String session){
        List<AssignmentResponse> assignmentResponses = assignmentService.getSubmittedAssignments(session);
        return new ResultSuccess<>(SuccessResponse.ASSIGNMENT_SUBMIT_SUCCESS,assignmentResponses);
    }

    @GetMapping("/api/assignment/notsubmit-list")
    public ResultSuccess getNotSubmittedAssignment(@CookieValue(name = "MoodleSession") String session){
        List<AssignmentResponse> assignmentResponses = assignmentService.getNotSubmittedAssignments(session);
        return new ResultSuccess<>(SuccessResponse.ASSIGNMENT_NOTSUBMIT_SUCCESS,assignmentResponses);
    }



    @PostMapping("/api/assignment/submit-status")
    public ResultSuccess updateAssignmentSubmitStatus(
            @CookieValue(name = "MoodleSession") String session,
            @RequestBody SubmitStatusRequest submitStatusRequest) {
        assignmentService.updateSubmitStats(session,submitStatusRequest);
        return new ResultSuccess<>(SuccessResponse.ASSIGNMENT_SUBMIT_SUCCESS,null);
    }
}
