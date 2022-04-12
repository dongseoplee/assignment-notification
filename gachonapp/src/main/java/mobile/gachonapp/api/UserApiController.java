package mobile.gachonapp.api;

import lombok.RequiredArgsConstructor;
import mobile.gachonapp.dto.UserLoginDTO;
import mobile.gachonapp.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/login")
    public void login(@RequestBody UserLoginDTO userLoginDTO) {

    }




    /*@GetMapping("/api/member")
    public List<Member> getMember() throws ExecutionException, InterruptedException {
        List<Member> members = memberService.findMembers();
        return members;
    }*/


}
