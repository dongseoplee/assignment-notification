package mobile.gachonapp.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.response.Result;
import mobile.gachonapp.api.response.SuccessResponse;
import mobile.gachonapp.dto.UserLoginDTO;
import mobile.gachonapp.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/login")
    public Result< Map<String, String> > login(@RequestBody @Validated UserLoginDTO userLoginDTO) throws Exception {

        log.info(userLoginDTO.toString());
        Map<String, String> session = userService.loginUser(userLoginDTO);

        return new Result< Map<String, String> >(SuccessResponse.LOGIN_SUCCESS,session);
    }

    /*@GetMapping("/api/member")
    public List<Member> getMember() throws ExecutionException, InterruptedException {
        List<Member> members = memberService.findMembers();
        return members;
    }*/


}
