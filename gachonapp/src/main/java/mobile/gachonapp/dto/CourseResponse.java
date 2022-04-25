package mobile.gachonapp.dto;

import lombok.Data;
import mobile.gachonapp.domain.Course;
import mobile.gachonapp.domain.CourseViewStatus;

@Data
public class CourseResponse {

    public String courseName;
    public CourseViewStatus courseViewStatus;

    private CourseResponse(Course course) {
        this.courseName = course.getCourseName();
        this.courseViewStatus = course.getCourseViewStatus();
    }


    public static CourseResponse createResponse(Course course) {
        return new CourseResponse(course);
    }
}
