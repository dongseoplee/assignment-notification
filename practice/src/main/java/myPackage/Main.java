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
        String homeURL = "https://cyber.gachon.ac.kr/";
        String crawlingURL = "https://cyber.gachon.ac.kr/mod/assign/index.php?id="; //이 주소 String에 + urlId 추가해서 접속

        //로그인 지우기!!
        String id = "";
        String password = "";
        //로그인 url접속후 html 변수 username, password에 아이디 비밀번호 데이터 전송
        Connection.Response login = Jsoup.connect(loginURL)
                .data("username",id,"password",password)
                .method(Connection.Method.POST)
                .timeout(3000)
                .execute();

        Document document = Jsoup.connect(homeURL)
                .cookies(login.cookies())
                .timeout(3000)
                .get();

        List<Course> courseList = new ArrayList<>();
        Elements elements = document.select("a.course_link");
        System.out.println(elements.text());

        //Course 클래스에 강의 이름과 URL insert
        //System.out.println(elements.size()); elements에 총 강의수(8개)

        for (Element element : elements) {

            String courseName = element.select("h3").text();
            String courseURL = element.attr("abs:href");

            Course course = new Course(courseName, courseURL); //강의 이름 url 과제제목 기한 넘겨주기
            String urlID = course.getUrlId();
            //System.out.println(urlID);
            courseList.add(course); //데이터 타입: Course (class)
            System.out.println(course);

            //String connectURL = crawlingURL + urlID;

            // Document 호출시 에러 발생함
            /*
            Document documentAssignment = Jsoup.connect(connectURL)
                    .cookies(login.cookies())
                    .timeout(3000)
                    .get();
            System.out.println(documentAssignment);

            //과제 제목, 기한 담을 리스트 생성
            ArrayList<String> titleList = new ArrayList<>();
            ArrayList<String> timeList = new ArrayList<>();

            Elements elementsNum = documentAssignment.select("td[class=cell c1]");
            System.out.println(elementsNum.size());


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


            course.setAssignmentList(titleList, timeList);
            courseList.add(course); //데이터 타입: Course (class)

            System.out.println(course);


 */
        }

        int num = elements.size();
        for (int i = 0; i < num; i++) {
            String urlIDTemp = courseList.get(i).getUrlId();

            String connectURL = crawlingURL + urlIDTemp;
            Document documentAssignment = Jsoup.connect(connectURL)
                    .cookies(login.cookies())
                    .timeout(3000)
                    .get();
            System.out.println(documentAssignment);
        }






        //과제 크롤링할 url 접속
        /*
        String connectURL = crawlingURL + "77414";
        Document document = Jsoup.connect(connectURL)
                .cookies(login.cookies())
                .timeout(3000)
                .get();

        //과제 제목, 기한 담을 리스트 생성
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> timeList = new ArrayList<>();


        //과제 제목 리스트에 담기
        //i의 범위를 과제 갯수에 맞게 설정하면 좋은데 과제 갯수 파악하는 방법은 고민할거리 - j1 do it-
        for (int i = 0; i < 50; i++) {
            Elements elements = document.select("td[class=cell c1]").eq(i);
            titleList.add(elements.text());
        }
        //과제 기한 리스트에 담기
        for (int i = 0; i < 50; i++) {
            Elements elements = document.select("td[class=cell c2]").eq(i);
            timeList.add(elements.text());
        }

        //리스트 출력해서 크롤링 데이터 잘 담겼는지 확인하기
        System.out.println("title list: " + titleList);
        System.out.println("time list: " + timeList);

         */
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

