package mobile.gachonapp.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.response.Result;
import mobile.gachonapp.api.response.SuccessResponse;
import mobile.gachonapp.domain.dto.UserLoginRequest;
import mobile.gachonapp.domain.dto.UserLoginResponse;
import mobile.gachonapp.repository.UserRepository;
import mobile.gachonapp.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/login")
    @CrossOrigin("*")
    public Result<UserLoginResponse> login(@RequestBody @Validated UserLoginRequest userLoginRequest,
                                           HttpServletResponse response) {
        UserLoginResponse userLoginResponse= userService.loginUser(userLoginRequest);

        //쿠키 생성 후 응답헤더에 추가
        Cookie myCookie = new Cookie("MoodleSession", userLoginResponse.getSession());
        response.addCookie(myCookie);

        //TODO: userLoginResponse에 세션을 빼기!
        return new Result<>(SuccessResponse.LOGIN_SUCCESS,userLoginResponse);
    }
}
