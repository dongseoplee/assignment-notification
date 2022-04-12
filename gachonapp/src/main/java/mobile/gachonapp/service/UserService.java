package mobile.gachonapp.service;


import lombok.RequiredArgsConstructor;
import mobile.gachonapp.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository memberRepository;

    /*public List<Member> findMembers() throws ExecutionException, InterruptedException {
        return memberRepository.findAll();
    }

    public String join(Member member) throws ExecutionException, InterruptedException {
        String id = memberRepository.save(member);
        return id;
    }

    public Member findAssigns() throws ExecutionException, InterruptedException {
        return memberRepository.findAssigns();
    }*/
}
