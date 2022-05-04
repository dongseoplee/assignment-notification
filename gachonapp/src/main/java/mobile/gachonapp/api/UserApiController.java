package mobile.gachonapp.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.response.ResultSuccess;
import mobile.gachonapp.api.response.SuccessResponse;
import mobile.gachonapp.domain.dto.UserLoginRequest;
import mobile.gachonapp.domain.dto.UserLoginResponse;
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
    public ResultSuccess<UserLoginResponse> login(@RequestBody @Validated UserLoginRequest userLoginRequest) throws IOException {
        String session = userService.loginUser(userLoginRequest);
        UserLoginResponse userLoginResponse = new UserLoginResponse(session);
        return new ResultSuccess<>(SuccessResponse.LOGIN_SUCCESS,userLoginResponse);
    }
}
