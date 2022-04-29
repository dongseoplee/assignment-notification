package myPackage;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Assignment {
    private String assignmentName;
    private LocalDateTime deadLinetime;
    private  String assignmentTime;




    public Assignment(String assignmentName, String assignmentTime) throws IOException {

        this.assignmentName = assignmentName;
        this.assignmentTime = assignmentTime;
    }


    @Override
    public String toString() {
        return "";
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

    public LocalDateTime getDeadLinetime() {
        return deadLinetime;
    }

    public void setDeadLinetime(LocalDateTime deadLinetime) {
        this.deadLinetime = deadLinetime;
    }
}