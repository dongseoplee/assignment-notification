
package mobile.gachonapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
public class Assignment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String assignmentName;
    private LocalDateTime deadLine;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "course")
    private Course course;

    @Enumerated(EnumType.STRING)
    private AssignmentDeadLineStatus assignmentDeadLineStatus = AssignmentDeadLineStatus.EARLY;

    @Enumerated(EnumType.STRING)
    private AssignmentSubmitStatus assignmentSubmitStatus = AssignmentSubmitStatus.N;

   /* @LastModifiedDate
    private LocalDateTime modifiedDate;*/

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user")
    private User user;

    public Assignment() {

    }

    public void changeSubmitStatus(AssignmentSubmitStatus assignmentSubmitStatus) {
        this.assignmentSubmitStatus = assignmentSubmitStatus;
    }

    public void setCourse(Course course) {
        this.course = course;
        course.getAssignments().add(this);
    }
}


