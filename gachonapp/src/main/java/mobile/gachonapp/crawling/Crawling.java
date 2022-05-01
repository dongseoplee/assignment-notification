package mobile.gachonapp.crawling;

import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.custom_exception.SessionExpiredException;
import mobile.gachonapp.api.custom_exception.WrongLoginUserException;
import mobile.gachonapp.domain.Assignment;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Crawling {

    private CrawlingCourse course;
    private static final String loginURL = "https://cyber.gachon.ac.kr/login/index.php";
    private static final String parsingURL = "https://cyber.gachon.ac.kr/";
    private String crawlingURL = "https://cyber.gachon.ac.kr/mod/assign/index.php?id=";

    private String id = "";
    private String password = "";

    public List<Assignment> getCrawledAssignments(String session) {

        //로그인 후 사이버 캠퍼스로부터 받은 쿠키(아이디, 비밀번호) 사용한다.
        Document document = null;
        try {
            document = Jsoup.connect(parsingURL)
                    .cookie("MoodleSession",session)
                    .timeout(3000)
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //만료된 세션 사용시 가천서버에서 로그인페이지로 강제이동 -> 해당페이지에
        // (class name = "html_login") 존재하므로 true이면 세션만료이다.
        if (document.html().contains("html_login")) {
            throw new SessionExpiredException();
        }

        //수강 강의 리스트 만들어 저장
        List<CrawlingCourse> courses = new ArrayList<>();
        Elements elements = document.select("a.course_link");

        for (Element element : elements) {

            String courseName = element.select("h3").text();
            String courseURL = element.attr("abs:href");

            CrawlingCourse course = new CrawlingCourse(courseName, courseURL); //강의 이름 url 과제제목 기한 넘겨주기
            String urlID = course.getUrlId();

            courses.add(course); //데이터 타입: Course (class)
            String connectURL = crawlingURL + urlID;

            Document documentAssignment = null;
            try {
                documentAssignment = Jsoup.connect(connectURL)
                        .cookie("MoodleSession",session)
                        .timeout(3000)
                        .get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //과제 제목, 기한 담을 리스트 생성
            ArrayList<String> titleList = new ArrayList<>();
            ArrayList<String> timeList = new ArrayList<>();
            ArrayList<String> submittedList = new ArrayList<>();

            Elements elementsNum = documentAssignment.select("td[class=cell c1]");
            //System.out.println(elementsNum.size());

            int numOfAssignment = elementsNum.size();
            for (int i = 0; i < numOfAssignment; i++) {
                Elements elementsTitle = documentAssignment.select("td[class=cell c1]").eq(i);
                titleList.add(elementsTitle.text());
            }
            //과제 기한 리스트에 담기
            for (int i = 0; i < numOfAssignment; i++) {
                Elements elementsTime = documentAssignment.select("td[class=cell c2]").eq(i);
                timeList.add(elementsTime.text());
            }

            for (int i = 0; i < numOfAssignment; i++) {
                Elements elementsSubmitted = documentAssignment.select("td[class=cell c3]").eq(i);
                submittedList.add(elementsSubmitted.text());
            }

            course.setAssignmentList(titleList, timeList, submittedList);
            courses.add(course);
            log.info(String.valueOf(course));
        }
        return new ArrayList<>();

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