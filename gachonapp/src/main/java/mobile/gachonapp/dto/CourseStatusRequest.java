package mobile.gachonapp.dto;

import lombok.Data;
import mobile.gachonapp.domain.CourseViewStatus;
import org.apache.logging.log4j.util.Strings;

@Data
public class CourseStatusRequest {
    private String courseName;
    private CourseViewStatus courseViewStatus;


}
