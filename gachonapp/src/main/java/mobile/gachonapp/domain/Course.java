package mobile.gachonapp.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mobile.gachonapp.domain.dto.CourseStatusRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //학수번호
    private String courseId;
    private String courseName;
    private String professorName;
    private String webLink;
    private CourseViewStatus courseViewStatus = CourseViewStatus.TRUE;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user")
    private User user;

    @OneToMany(mappedBy = "course")
    private List<Assignment> assignments = new ArrayList();

    public Course() {
    }

    public void setUser(User user) {
        this.user = user;
        user.getCourses().add(this);
    }

    public void changeViewStatus(CourseViewStatus courseViewStatus) {
        this.courseViewStatus = courseViewStatus;
    }

    public boolean isSame(CourseStatusRequest courseStatusRequest) {
        return courseViewStatus.equals(courseStatusRequest.getCourseViewStatus());
    }
}
