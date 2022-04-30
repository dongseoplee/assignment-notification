package myPackage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Assignment {
    private String assignmentName;
    private LocalDateTime dueDate;
    private  String assignmentTime;
    private boolean submitted;


    public Assignment(String assignmentName, String assignmentTime, String assignmentSubmitted) throws IOException {

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
        /*
        System.out.println("Assignment Title: " +  this.assignmentName);
        System.out.println("년: " + this.dueDate.getYear() +"    월: " + this.dueDate.getMonthValue() + "     일: " +
                this.dueDate.getDayOfMonth() + "    시: " +this.dueDate.getHour() + "    분:" + this.dueDate.getMinute() +
                "     data type: " + this.dueDate.getClass().getName());
         */
        //System.out.println("Local Date Time: " + this.dueDate);
    }


    @Override
    public String toString() {
        return "    assignmentName = " + assignmentName + "     assignmentTime = " + assignmentTime
                + "     Submitted = " + submitted;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentTime() {
        return assignmentTime;
    }

    public void setAssignmentTime(String assignmentTime) {
        this.assignmentTime = assignmentTime;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}