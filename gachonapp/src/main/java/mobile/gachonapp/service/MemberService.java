package mobile.gachonapp.service;


import lombok.RequiredArgsConstructor;
import mobile.gachonapp.domain.Member;
import mobile.gachonapp.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findMembers() throws ExecutionException, InterruptedException {
        return memberRepository.findAll();
    }

    /*public String join(Member member) {
        String id = memberRepository.save(member);
        return id;
    }*/
}
