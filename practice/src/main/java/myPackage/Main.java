package myPackage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) throws IOException {
        String loginURL = "https://cyber.gachon.ac.kr/login/index.php";
        String parsingURL = "https://cyber.gachon.ac.kr/";
        String courseViewURL = "https://cyber.gachon.ac.kr/course/view.php?id="; //이 주소 String에 + urlId 추가해서 접속
        String courseTableViewURL = "https://cyber.gachon.ac.kr/mod/assign/index.php?id="; //이 주소 String에 + urlId 추가해서 접속
        //과제 전용 주소
        //로그인 지우기!!
        String id = "";
        String password = "";

        //로그인 url접속후 html 변수 username, password에 아이디 비밀번호 데이터 전송
        Connection.Response login= Jsoup.connect(loginURL)
                .data("username",id,"password",password)
                .method(Connection.Method.POST)
                .timeout(3000)
                .execute();

        String connectURL = courseTableViewURL + "74409";
        Document document = Jsoup.connect(connectURL)
                .cookies(login.cookies())
                .timeout(3000)
                .get();

        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> timeList = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            Elements elements = document.select("td[class=cell c1]").eq(i);
            titleList.add(elements.text());
        }
        for (int i = 0; i < 10; i++) {
            Elements elements = document.select("td[class=cell c2]").eq(i);
            timeList.add(elements.text());
        }

        System.out.println("title list: " + titleList);
        System.out.println("time list: " + timeList);

/*
        //로그인 후 사이버 캠퍼스로부터 받은 쿠키(아이디, 비밀번호) 사용한다.
        //document에 저장
        Document document = Jsoup.connect(parsingURL)
                .cookies(login.cookies())
                .timeout(3000)
                .get();



        //수강 강의 리스트 만들어 저장
        List<Course> courseList = new ArrayList<>();

        //elements에 강의 마다 링크 들어있음
        Elements elements = document.select("a.course_link");

        System.out.println("elements = " + elements);
        //Course 클래스에 강의 이름과 URL insert
        //elements에 총 강의수(8개)
        for (Element element : elements) {

            String courseName = element.select("h3").text();
            String courseURL = element.attr("abs:href");

            Course course = new Course(courseName, courseURL);
            courseList.add(course); //데이터 타입: Course (class)
        }


        for (Course course : courseList) {
            System.out.println(course.toString());

        }

        for (Course course : courseList) {
            Document document1 = Jsoup.connect(course.getURL())
                    .cookies(login.cookies())
                    .timeout(1000)
                    .get();
        }

        List<Assignment> assignmentList = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        ArrayList<String> time = new ArrayList<>();

        //urlId를 Assginment에 넘겨주는 for문
        for (Course course : courseList) {

            System.out.println("course: " + course.getURL());
            Assignment assignment = new Assignment(course.getURL(), id, password);
            assignmentList.add(assignment);
        }



        System.out.println(assignmentList);
        System.out.println(courseList.size());


*/

        System.out.println();
        System.out.println("\t" + "!!!!!!!!!!!!!!!!!!!!!Warning!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("\t" + "!!!!!!!                                      !!!!!!!");
        System.out.println("\t" + "!!!!!!!Delete id, password before push to git!!!!!!!");
        System.out.println("\t" + "!!!!!!!                                      !!!!!!!");
        System.out.println("\t" + "!!!!!!!!!!!!!!!!!!!!!Warning!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}

