package mobile.gachonapp.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.crawling.Crawling;
import mobile.gachonapp.domain.User;
import mobile.gachonapp.dto.UserLoginDTO;
import mobile.gachonapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Map<String, String> loginUser(UserLoginDTO userLoginDTO) throws IOException {

        Crawling crawling = new Crawling();

        //가천 서버로부터 받은 세션 저장
        Map<String, String> session = crawling.checkLogin(userLoginDTO.getUserId(), userLoginDTO.getPassword());

        //새로운 사용자면 데이터베이스에 등록
        /*if(userRepository.findByUserId(userLoginDTO.getUserId()) == null){
            userRepository.save(userLoginDTO.toEntity());
        }*/

        return session;
    }

    /*private void validateDuplicate(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUserId(userLoginDTO.getUserId());
    }*/

}
