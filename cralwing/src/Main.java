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
        String loginUrl = "https://cyber.gachon.ac.kr/login/index.php";
        String mainParseUrl = "https://cyber.gachon.ac.kr/";
        String loginId = "jmk7117";
        String password = "als968574@";

        Connection.Response login= Jsoup.connect(loginUrl)
                .data("username",loginId,"password",password)
                .method(Connection.Method.POST)
                .timeout(1000)
                .execute();

        //로그인 후 가천 서버로 부터 받은 쿠키(로그인 정보)를 사용한다.
        Document doc=Jsoup.connect(mainParseUrl)
                .cookies(login.cookies())
                .timeout(1000)
                .get();

        List<Course> courseList = new ArrayList<>();

        //html course_link 클래스 추출
        Elements elements= doc.select("a.course_link");

        //과목 이름  url 저장
        for (Element element : elements) {

            //TODO : 제목 뒤에 필요없는 내용 자르기
            String courseName =element.select("h3").text();

            String courseUrl = element.attr("abs:href");
            Course course = new Course(courseName,courseUrl);
            courseList.add(course);
        }

        for (Course course : courseList) {
            System.out.println(course.toString());
        }


        //과목 url에 접속하여 과목시간 추출
        for (Course course : courseList) {
            Document document = Jsoup.connect(course.getUrl())
                    .cookies(login.cookies())
                    .timeout(1000)
                    .get();

            //크롤링
        }


    }
}
