package mobile.gachonapp.service;

import mobile.gachonapp.crawling.CrawlUser;
import mobile.gachonapp.crawling.Crawling;
import mobile.gachonapp.domain.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlingService {

    public List<Course> crawlAssignments(String session) {
        Crawling crawling = new Crawling();
        return crawling.getCrawledAssignments(session);
    }

    public String checkLogin(String id, String password) {
        Crawling crawling = new Crawling();
        return crawling.checkLogin(id,password);
    }

    public CrawlUser getUserInfo(String session) {
        Crawling crawling = new Crawling();
        return crawling.getUserInfo(session);
    }
}
