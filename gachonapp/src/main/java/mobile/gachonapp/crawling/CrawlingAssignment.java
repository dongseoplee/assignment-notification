package mobile.gachonapp.crawling;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class CrawlingAssignment {
    private String assignmentName;
    private LocalDateTime dueDate;
    private String assignmentTime;
    private boolean submitted;


    public CrawlingAssignment(String assignmentName, String assignmentTime, String assignmentSubmitted)  {

        this.assignmentName = assignmentName;
        this.assignmentTime = assignmentTime;

        transferToLocalDateTime();
        transferToSubmittedBoolean(assignmentSubmitted);
    }

    public void transferToSubmittedBoolean(String submitted) {
        if (submitted.equals("제출 완료") || submitted.equals("Submitted for grading")) {
            this.submitted = true;
        }
        else {
            this.submitted = false;
        }
    }

    public void transferToLocalDateTime() {
        String temp = this.assignmentTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.dueDate = LocalDateTime.parse(temp, formatter);
    }

    @Override
    public String toString() {
        return "    assignmentName = " + assignmentName + "     assignmentTime = " + assignmentTime
                + "     Submitted = " + submitted;
    }

}
