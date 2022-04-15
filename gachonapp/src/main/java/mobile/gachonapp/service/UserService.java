package mobile.gachonapp.service;


import lombok.RequiredArgsConstructor;
import mobile.gachonapp.crawling.Crawling;
import mobile.gachonapp.domain.User;
import mobile.gachonapp.dto.UserLoginRequest;
import mobile.gachonapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public String loginUser(UserLoginRequest userLoginRequest) throws IOException {

        User user = userLoginRequest.toEntity();

        Crawling crawling = new Crawling();
        String session = crawling.checkLogin(user.getUserId(), user.getPassword());
        user.setSession(session);

        //새로운 사용자 일시
        if(userRepository.findByUserId(user.getUserId()).isEmpty()){
            userRepository.save(user);
            return session;
        }
        //기존 사용자는 session update
        userRepository.updateSession(user);
        return session;
    }

    /*private void validateDuplicate(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUserId(userLoginDTO.getUserId());
    }*/

}
