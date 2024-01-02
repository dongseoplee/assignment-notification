package mobile.gachonapp.crawling;


import lombok.Data;
import lombok.NoArgsConstructor;
import mobile.gachonapp.domain.Assignment;
import mobile.gachonapp.domain.AssignmentDeadLineStatus;
import mobile.gachonapp.domain.AssignmentSubmitStatus;
import mobile.gachonapp.domain.Course;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class CrawlingAssignment {
    private String assignmentName;
    private LocalDateTime deadLine;
    private AssignmentSubmitStatus assignmentSubmitStatus;
    private AssignmentDeadLineStatus assignmentDeadLineStatus;

    public static CrawlingAssignment createAssignment(String assignmentName, String assignmentTime, String assignmentSubmitted) {

        return new CrawlingAssignment(assignmentName,assignmentTime,assignmentSubmitted);
    }

    private CrawlingAssignment(String assignmentName, String assignmentTime, String assignmentSubmitted) {
        this.assignmentName = assignmentName;
        deadLine = transferToLocalDateTime(assignmentTime);
        changeOnTimeDeadLine();
        assignmentSubmitStatus = transferToSubmittedStatus(assignmentSubmitted);
        setDeadLineStatus();
    }

    private void changeOnTimeDeadLine() {
        if(deadLine.getMinute() == 0 & deadLine.getHour() == 0) {
            this.deadLine = deadLine.minusMinutes(1);
        }
    }


    private void setDeadLineStatus() {
        //과제 제출시간이 지났다
        if (deadLine.isAfter(LocalDateTime.now())) {
            assignmentDeadLineStatus = AssignmentDeadLineStatus.EARLY;
        }
        //제출 시간이 지나지 않았을때
        else {
            assignmentDeadLineStatus = AssignmentDeadLineStatus.OVERDUE;
        }
    }

    public AssignmentSubmitStatus transferToSubmittedStatus(String submitted) {
        if (submitted.equals("제출 완료") || submitted.equals("Submitted for grading")) {
            return AssignmentSubmitStatus.Y;
        } else {
            return AssignmentSubmitStatus.N;
        }
    }

    public LocalDateTime transferToLocalDateTime(String assignmentTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(assignmentTime, formatter);
    }

    public Assignment toEntity() {
        return Assignment.builder()
                .assignmentName(assignmentName)
                .deadLine(deadLine)
                .assignmentSubmitStatus(assignmentSubmitStatus)
                .assignmentDeadLineStatus(assignmentDeadLineStatus)
                .build();
    }

}
