package mobile.gachonapp.crawling;

import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.domain.Course;
import mobile.gachonapp.exception.ConnectionTimeoutException;
import mobile.gachonapp.exception.SessionExpiredException;
import mobile.gachonapp.exception.WrongLoginUserException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Crawling {

    private static final String loginURL = "https://cyber.gachon.ac.kr/login/index.php";
    private static final String parsingURL = "https://cyber.gachon.ac.kr/";
    private String crawlingURL = "https://cyber.gachon.ac.kr/mod/assign/index.php?id=";

    public List<Course> getCrawledAssignments(String session) {

        //사이버캠퍼스 로그인 후 첫 페이지 document
        Document document = getDocument(session,parsingURL);

        //만료된 세션 사용시 가천서버에서 로그인페이지로 강제이동 -> 해당페이지에
        // (class name = "html_login") 존재하므로 true이면 세션만료이다.
        sessionCheck(document);

        List<CrawlingCourse> courses = new ArrayList<>();

        Elements elements = document.select("a.course_link");

        for (Element element : elements) {

            String courseName = element.select("h3").text();
            String courseURL = element.attr("abs:href");

            CrawlingCourse course = new CrawlingCourse(courseName, courseURL); //강의 이름 url 과제제목 기한 넘겨주기
            courses.add(course);

            //과목 url
            String connectURL = crawlingURL + course.getUrlId();

            //과목 document
            Document documentAssignment = getDocument(session, connectURL);
            Elements CourseElements = documentAssignment.select("td[class=cell c1]");

            int coursesSize = CourseElements.size();
            List<CrawlingAssignment> crawlingAssignments = new ArrayList<>(coursesSize);

            for (int i = 0; i<coursesSize; i++) {
                String name = documentAssignment.select("td[class=cell c1]").eq(i).text();
                String time = documentAssignment.select("td[class=cell c2]").eq(i).text();
                String submitStatus = documentAssignment.select("td[class=cell c3]").eq(i).text();
                CrawlingAssignment assignment = CrawlingAssignment.createAssignment(name, time, submitStatus);
                crawlingAssignments.add(assignment);
            }

            course.setAssignments(crawlingAssignments);

        }
        return courses.stream()
                .map(CrawlingCourse::toEntity)
                .collect(Collectors.toList());
    }

    private void sessionCheck(Document document) {
        if (document.html().contains("html_login")) {
            throw new SessionExpiredException();
        }
    }

    private Document getDocument(String session, String parsingURL) {
        Document document = null;
        try {
            document = Jsoup.connect(parsingURL)
                    .cookie("MoodleSession", session)
                    .timeout(3000)
                    .get();
        } catch (IOException e) {
            throw new ConnectionTimeoutException();
        }
        return document;
    }

    //세션반환  (validateLogin)
    public String checkLogin(String id, String password) {

        Connection.Response loginConnection = null;
        try {
            loginConnection = Jsoup.connect(loginURL)
                    .data("username", id, "password", password)
                    .method(Connection.Method.POST)
                    .timeout(3000)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (isWrongUser(loginConnection)) {
            throw new WrongLoginUserException();
        }

        return loginConnection.cookies().get("MoodleSession");
    }

    private boolean isWrongUser(Connection.Response login) {
        return login.body().contains("error_message text-danger");
    }
}


//로그인 성공후 세션발급
//세션 30분 지나면 소멸인데 db에는 그대로 남아있다.?
//TODO db에 남아이쓰는 세션(잘못된세션을 보냈을때)으로 가천서버에 api보냈을때 에러처리
//지난 세션으로 서버에 요청했을때 리턴갑스 ??
//다시 로그인 해서세션을 새거로 db에 저장해야한다.