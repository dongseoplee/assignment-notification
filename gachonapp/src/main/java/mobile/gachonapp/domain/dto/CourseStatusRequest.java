package mobile.gachonapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import mobile.gachonapp.domain.CourseViewStatus;
import org.apache.logging.log4j.util.Strings;

@Data
@NoArgsConstructor
public class CourseStatusRequest {
    private String courseName;
    private CourseViewStatus courseViewStatus;
}
