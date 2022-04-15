
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

    @OneToMany(mappedBy = "assignment")
    private List<UserAssignment> userAssignments = new ArrayList<>();

    @ManyToOne @JoinColumn(name = "subjectId")
    private Subject subject;

    @Enumerated(EnumType.STRING)
    private  AssignmentDeadLineStatus assignmentDeadLineStatus = AssignmentDeadLineStatus.EARLY;

    @Enumerated(EnumType.STRING)
    private AssignmentSubmitStatus assignmentSubmitStatus = AssignmentSubmitStatus.N;
}


