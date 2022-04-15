package mobile.gachonapp.service;


import lombok.RequiredArgsConstructor;
import mobile.gachonapp.crawling.Crawling;
import mobile.gachonapp.domain.User;
import mobile.gachonapp.dto.UserLoginRequest;
import mobile.gachonapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    //TODO 크롤링 -> 크롤링서비스
    //TODO if문 리팩토링
    public String loginUser(UserLoginRequest userLoginRequest) throws IOException {

        //엔티티 생성
        final User user = userLoginRequest.toEntity();

        Crawling crawling = new Crawling();
        String session = crawling.checkLogin(user.getUserId(), user.getPassword());
        user.setSession(session);

        //기존 사용자는 db에 session을 update
        if (userRepository.findByUserId(user.getUserId()).isPresent()) {
            userRepository.updateSession(user);
            return session;
        }

        //로그인한 사용자가 앱에 처음 로그인한 사용자 일시 db에 등록
        userRepository.save(user);

        return session;
    }

    /*private void validateDuplicate(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUserId(userLoginDTO.getUserId());
    }*/

}
