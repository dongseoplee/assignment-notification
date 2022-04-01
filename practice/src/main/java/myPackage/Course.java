package myPackage;

import java.util.List;

public class Course {
    private String name;
    private String URL;
    private String urlId;
    private List<Assignment> assignment;


    public Course(String name, String url) {


        //this.name = getOnlyCourseName(); //this.name = getOnlyCourseName(); 변경해야함
        this.name = name;
        String modifiedName = this.name;

        int idx = modifiedName.indexOf("(");

        this.name = modifiedName.substring(0, idx - 1); //idx - 1 이유는 '(' 전에 공백 지우기위함

        this.URL = url;
        this.urlId = splitIdOfUrl();
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
        return "     name = " + name + "    url = " + URL + "   urlId = " + urlId;

    }
}