package mobile.gachonapp.api;

import lombok.RequiredArgsConstructor;
import mobile.gachonapp.domain.Member;
import mobile.gachonapp.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/member")
    public List<Member> getMember() throws ExecutionException, InterruptedException {
        List<Member> members = memberService.findMembers();
        return members;
    }

    /*@PostMapping("/api/member")
    public void saveMember(@RequestBody Member member) {
        String id = memberService.join(member);

    }*/
}
