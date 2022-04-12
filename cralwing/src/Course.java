import java.time.LocalDate;
import java.util.List;

public class Course {

    private String name;
    private String url;
    private String urlId;
    private List<Assignment> assignment;


    public Course(String name, String url) {
        this.name = name;
        this.url = url;
        this.urlId = splitIdOfUrl();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlId() {
        return urlId;
    }




    //url에서 url id 추출
    private String splitIdOfUrl() {
        String url = this.url;
        int idx = url.indexOf("?");
        return url.substring(idx+1);
    }

    @Override
    public String toString() {
        return "     name = " + name + "    url = " + url +"   urlId = " + urlId;
    }
}
