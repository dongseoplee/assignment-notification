package myPackage;

import java.io.IOException;
import java.time.LocalDateTime;

public class Assignment {
    private String assignmentName;
    private LocalDateTime dueDate;
    private  String assignmentTime;




    public Assignment(String assignmentName, String assignmentTime) throws IOException {

        this.assignmentName = assignmentName;
        this.assignmentTime = assignmentTime;
    }


    @Override
    public String toString() {
        return "    assignmentName = " + assignmentName + "     assignmentTime = " + assignmentTime;
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