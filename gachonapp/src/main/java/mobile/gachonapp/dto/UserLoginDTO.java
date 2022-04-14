package mobile.gachonapp.dto;

import lombok.Getter;
import lombok.Setter;
import mobile.gachonapp.domain.User;

import javax.validation.constraints.NotBlank;

@Getter
public class UserLoginDTO {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .build();
    }


}
