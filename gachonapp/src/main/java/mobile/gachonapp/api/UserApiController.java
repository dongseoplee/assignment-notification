package mobile.gachonapp.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.response.Result;
import mobile.gachonapp.api.response.SuccessResponse;
import mobile.gachonapp.dto.UserLoginRequest;
import mobile.gachonapp.dto.UserLoginResponse;
import mobile.gachonapp.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/login")
    public Result<UserLoginResponse> login(@RequestBody @Validated UserLoginRequest userLoginRequest) throws IOException {

        String session = userService.loginUser(userLoginRequest);

        //dto 생성 (생성자를 사용했지만 빌더 패턴 혹은 정적팩토리 고민해볼만하다)
        UserLoginResponse userLoginResponse = new UserLoginResponse(session);
        return new Result<>(SuccessResponse.LOGIN_SUCCESS,userLoginResponse);
    }

}
