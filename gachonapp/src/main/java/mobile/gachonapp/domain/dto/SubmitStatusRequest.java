package mobile.gachonapp.domain.dto;

import lombok.Getter;
import lombok.Setter;
import mobile.gachonapp.domain.AssignmentSubmitStatus;

@Getter @Setter
public class SubmitStatusRequest {
    private String assignmentName;
    private AssignmentSubmitStatus assignmentSubmitStatus;
}
