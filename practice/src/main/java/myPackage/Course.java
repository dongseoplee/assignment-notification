package myPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private String URL;
    private String urlId;


    List<Assignment> assignmentList = new ArrayList<>();


    public Course(String courseName, String courseURL) throws IOException {

        this.name = courseName;
        getOnlyCourseName();

        this.URL = courseURL;
        this.urlId = splitIdOfUrl();


    }

    public void getOnlyCourseName() {
        String modifiedName = this.name;
        int idx = modifiedName.indexOf("(");
        this.name = modifiedName.substring(0, idx - 1); //idx - 1 이유는 '(' 전에 공백 지우기위함
    }

    public void setAssignmentList(ArrayList<String> tempName, ArrayList<String> tempTime) throws IOException {

        for (int i = 0; i < tempName.size(); i++) {
            Assignment assignment = new Assignment(tempName.get(i), tempTime.get(i));
            assignmentList.add(assignment);
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setUrl(String url) {
        this.URL = url;
    }

    public String getUrlId() {
        return urlId;
    }


    //url에서 url id 추출
    private String splitIdOfUrl() {
        String url = this.URL; //this.URL을 가지고 id만 뽑아내기
        int idx = url.indexOf("?");
        return url.substring(idx + 4);
    }


    @Override
    public String toString() {
        return "   Assignment = " + assignmentList + "     name = " + name + "    url = " + URL + "   urlId = " + urlId;

    }
}

