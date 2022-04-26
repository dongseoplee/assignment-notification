package myPackage;

public class Assignment(String urlId) {
    private String name;
    private String time;
    public String courseViewURL = "https://cyber.gachon.ac.kr/course/view.php?id="; //이 주소 String에 + urlId 추가해서 접속

    public Assignment(String urlId) {

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

    //Course에서 id만 가져오기
}