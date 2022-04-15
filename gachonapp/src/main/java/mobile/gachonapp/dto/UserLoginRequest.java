package mobile.gachonapp.dto;

import lombok.Getter;
import mobile.gachonapp.domain.User;

import javax.validation.constraints.NotBlank;

@Getter
public class UserLoginRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    public User toEntity() {
        return new User(userId,password);
    }


}
