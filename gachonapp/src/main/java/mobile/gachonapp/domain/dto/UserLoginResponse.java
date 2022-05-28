package mobile.gachonapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserLoginResponse {
    String studentId;
    String major;

    @JsonIgnore
    String session;
}
