package mobile.gachonapp.dto;

import lombok.Getter;
import lombok.Setter;
import mobile.gachonapp.domain.User;

@Getter
public class UserLoginDTO {

    private String userId;
    private String password;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .build();
    }


}
