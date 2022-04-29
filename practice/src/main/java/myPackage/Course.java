package myPackage;

import org.jsoup.select.Elements;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private String URL;
    private String urlId;
    //public String assignmentName;

    List<Assignment> assignmentList = new ArrayList<>();


    public Course(String courseName, String courseURL) throws IOException {
        //this.name = getOnlyCourseName(); //this.name = getOnlyCourseName(); 변경해야함
        this.name = courseName;
        String modifiedName = this.name;
        int idx = modifiedName.indexOf("(");
        this.name = modifiedName.substring(0, idx - 1); //idx - 1 이유는 '(' 전에 공백 지우기위함
        this.URL = courseURL;
        this.urlId = splitIdOfUrl();


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

    //강의이름 학수번호 빼고 only 강의이름만 추출하기
    //ex) "모바일프로그래밍 (14454_001)" -> "모바일프로그래밍"
    /* NPE 에러남
    private String getOnlyCourseName() {
        String modifiedName = this.name;
        int idx = modifiedName.indexOf("(");
        //System.out.println(idx);
        return modifiedName.substring(0, idx - 1); //idx - 1 이유는 '(' 전에 있는 공백 지우기위함
    }*/

    //url에서 url id 추출
    private String splitIdOfUrl() {
        String url = this.URL;
        int idx = url.indexOf("?");
        return url.substring(idx + 1);
    }


    @Override
    public String toString() {
        return "     name = " + name + "    url = " + URL + "   urlId = " + urlId + "   Assignment = " + assignmentList;

    }
}

