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
        //로그인 지우기!!
        String id = "";
        String password = "";

        //로그인 url접속후 html 변수 username, password에 아이디 비밀번호 데이터 전송
        Connection.Response login= Jsoup.connect(loginURL)
                .data("username",id,"password",password)
                .method(Connection.Method.POST)
                .timeout(3000)
                .execute();



        //로그인 후 사이버 캠퍼스로부터 받은 쿠키(아이디, 비밀번호) 사용한다.
        //document에 저장
        Document document = Jsoup.connect(parsingURL)
                .cookies(login.cookies())
                .timeout(3000)
                .get();
        //System.out.println(document);


        //수강 강의 리스트 만들어 저장
        List<Course> courseList = new ArrayList<>();

        Elements elements = document.select("a.course_link");

        System.out.println("elements = " + elements);
        //Course 클래스에 강의 이름과 URL insert
        for (Element element : elements) {

            String courseName = element.select("h3").text();
            String courseURL = element.attr("abs:href");

            Course course = new Course(courseName, courseURL);
            courseList.add(course);
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

    }
}