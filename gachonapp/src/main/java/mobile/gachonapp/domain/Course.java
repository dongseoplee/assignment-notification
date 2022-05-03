package mobile.gachonapp.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mobile.gachonapp.domain.dto.CourseStatusRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //private String courseId;
    private String courseName;
    //private String professorName;
    //private String webLink;
    @Enumerated(EnumType.STRING)
    private CourseViewStatus courseViewStatus = CourseViewStatus.TRUE;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user")
    private User user;

    @OneToMany(mappedBy = "course" , cascade = CascadeType.ALL)
    private List<Assignment> assignments = new ArrayList();

    protected Course() {
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public void changeViewStatus(CourseViewStatus courseViewStatus) {
        this.courseViewStatus = courseViewStatus;
    }

    public boolean isSame(CourseStatusRequest courseStatusRequest) {
        return courseViewStatus.equals(courseStatusRequest.getCourseViewStatus());
    }

    public void setUser(User user) {
        this.user = user;
        user.getCourses().add(this);
    }

}
