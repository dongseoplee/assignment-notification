package myPackage;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assignment {
    private String name;
    private String time;
    private String url;
    public String loginURL = "https://cyber.gachon.ac.kr/login/index.php";
    public String courseTableViewURL = "https://cyber.gachon.ac.kr/mod/assign/index.php?id="; //이 주소 String에 + urlId 추가해서 접속
    //과제 전용 주소
    public ArrayList<String> titleFinal = new ArrayList<>();
    public ArrayList<String> timeFinal = new ArrayList<>();


    public Assignment(String urlId, String id, String password) throws IOException {
        this.url = urlId;
        //url접속 후 과제 제목, 기한 크롤링해와서 리스트에 넣기
        /*ArrayList<String> title = new ArrayList<>();
        ArrayList<String> time = new ArrayList<>();

        String urlAddress = courseTableViewURL + urlId;

        Connection.Response login = Jsoup.connect(loginURL)
                .data("username", id, "password", password)
                .method(Connection.Method.POST)
                .timeout(3000)
                .execute();

        Document doc = Jsoup.connect(urlAddress)
                .cookies(login.cookies())
                .timeout(3000)
                .get();

        Elements elementsTitle = doc.select(".cell c1 a");
        for (Element element : elementsTitle) {
            String titleTemp = element.text();
            title.add(titleTemp);

        }

        Elements elementsTime = doc.select(".cell c2 a");
        for (Element element : elementsTime) {
            String timeTemp = elementsTime.text();
            time.add(timeTemp);
        }

        titleFinal = title;
        timeFinal = time;*/

    }


    @Override
    public String toString() {
        return "    url: " + url + "    title: " + titleFinal + "   time: " + timeFinal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    //Course에서 id만 가져오기
}