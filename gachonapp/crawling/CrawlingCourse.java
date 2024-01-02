package mobile.gachonapp.crawling;

import lombok.Data;
import lombok.NoArgsConstructor;
import mobile.gachonapp.domain.Assignment;
import mobile.gachonapp.domain.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class CrawlingCourse {
    private String name;
    private String url;
    private String urlId;
    private String courseId;

    private List<CrawlingAssignment> assignments = new ArrayList<>();


    public CrawlingCourse(String courseName, String courseURL)  {
        this.name = courseName;
        this.courseId = getCourseId(courseName);

        getOnlyCourseName();
        this.url = courseURL;
        this.urlId = splitIdOfUrl();

    }

    public void getOnlyCourseName() {
        String modifiedName = this.name;
        int idx = modifiedName.indexOf("(");
        this.name = modifiedName.substring(0, idx - 1); //idx - 1 이유는 '(' 전에 공백 지우기위함
    }

    public String getCourseId(String courseName) {

        int idx = name.indexOf("(");
        int lastIdx = name.indexOf(")");
        String courseId = name.substring(idx+1, lastIdx);

        if (courseId.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))
            return null;

        return courseId;
    }

    /*public void setAssignments(ArrayList<String> tempName, ArrayList<String> tempTime, ArrayList<String> tempSubmitted)  {
        int size = tempName.size();
        for (int i = 0; i < size; i++) {
            CrawlingAssignment assignment = new CrawlingAssignment(tempName.get(i), tempTime.get(i), tempSubmitted.get(i));
            assignments.add(assignment);
        }
    }*/

    //url에서 url id 추출
    private String splitIdOfUrl() {
        String url = this.url; //this.URL을 가지고 id만 뽑아내기
        int idx = url.indexOf("?");
        return url.substring(idx + 4);
    }

    //name, assignmentList
    public Course toEntity() {
        //Course entity변환
        Course course = new Course(courseId,name);
        //AssignmentList entity변환
        List<Assignment> EntityAssignments = assignments.stream()
                .map(CrawlingAssignment::toEntity)
                .collect(Collectors.toList());

        for (Assignment entityAssignment : EntityAssignments) {
            entityAssignment.setCourse(course);
        }

        return course;
    }
}
