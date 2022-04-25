
package mobile.gachonapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Assignment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String assignmentName;
    private LocalDateTime time;

    @ManyToOne @JoinColumn(name = "course")
    private Course course;

    @Enumerated(EnumType.STRING)
    private  AssignmentDeadLineStatus assignmentDeadLineStatus = AssignmentDeadLineStatus.EARLY;

    @Enumerated(EnumType.STRING)
    private AssignmentSubmitStatus assignmentSubmitStatus = AssignmentSubmitStatus.N;

    public void changeSubmitStatus(AssignmentSubmitStatus assignmentSubmitStatus) {
        this.assignmentSubmitStatus = assignmentSubmitStatus;
    }
}


