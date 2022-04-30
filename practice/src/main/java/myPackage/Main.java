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
        String crawlingURL = "https://cyber.gachon.ac.kr/mod/assign/index.php?id=";

        String id = "";
        String password = "";

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

        for (Element element : elements) {

            String courseName = element.select("h3").text();
            String courseURL = element.attr("abs:href");

            Course course = new Course(courseName, courseURL); //강의 이름 url 과제제목 기한 넘겨주기
            String urlID = course.getUrlId();

            courseList.add(course); //데이터 타입: Course (class)


            String connectURL = crawlingURL + urlID;


            Document documentAssignment = Jsoup.connect(connectURL)
                    .cookies(login.cookies())
                    .timeout(3000)
                    .get();


            //과제 제목, 기한 담을 리스트 생성
            ArrayList<String> titleList = new ArrayList<>();
            ArrayList<String> timeList = new ArrayList<>();

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


            course.setAssignmentList(titleList, timeList);
            courseList.add(course); //데이터 타입: Course (class)

            System.out.println(course);



        }


        System.out.println();
        System.out.println("\t" + "!!!!!!!!!!!!!!!!!!!!!Warning!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("\t" + "!!!!!!!                                      !!!!!!!");
        System.out.println("\t" + "!!!!!!!Delete id, password before push to git!!!!!!!");
        System.out.println("\t" + "!!!!!!!                                      !!!!!!!");
        System.out.println("\t" + "!!!!!!!!!!!!!!!!!!!!!Warning!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}

