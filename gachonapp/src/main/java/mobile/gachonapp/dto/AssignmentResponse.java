package mobile.gachonapp.dto;

import lombok.Data;
import mobile.gachonapp.domain.Assignment;

import java.time.LocalDateTime;

@Data
public class AssignmentResponse {
    String courseName;
    String assignmentName;
    String professorName;
    LocalDateTime time;

    private AssignmentResponse(String courseName, String assignmentName, String professorName, LocalDateTime time) {
        this.courseName = courseName;
        this.assignmentName = assignmentName;
        this.professorName = professorName;
        this.time = time;
    }

    public static AssignmentResponse createResponse(Assignment assignment) {
        return new AssignmentResponse(
                assignment.getCourse().getCourseName(),
                assignment.getAssignmentName(),
                assignment.getCourse().getProfessorName(),
                assignment.getTime());
    }

}
