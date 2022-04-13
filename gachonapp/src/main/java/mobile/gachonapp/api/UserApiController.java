package mobile.gachonapp.api;

import lombok.RequiredArgsConstructor;
import mobile.gachonapp.dto.UserLoginDTO;
import mobile.gachonapp.dto.UserLoginResponse;
import mobile.gachonapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/login")
    public Result< Map<String, String> > login(@RequestBody UserLoginDTO userLoginDTO) throws Exception {

        Map<String, String> session = userService.loginUser(userLoginDTO);
        UserLoginResponse userLoginResponse = new UserLoginResponse();

        return new Result< Map<String, String> >(200,ResponseMessage.LOGIN_SUCCESS,session);
    }




    /*@GetMapping("/api/member")
    public List<Member> getMember() throws ExecutionException, InterruptedException {
        List<Member> members = memberService.findMembers();
        return members;
    }*/


}
