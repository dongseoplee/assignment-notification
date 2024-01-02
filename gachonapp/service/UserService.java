package mobile.gachonapp.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import mobile.gachonapp.crawling.CrawlUser;
import mobile.gachonapp.domain.User;
import mobile.gachonapp.domain.dto.UserLoginRequest;
import mobile.gachonapp.domain.dto.UserLoginResponse;
import mobile.gachonapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final CrawlingService crawlingService;
    private final AssignmentService assignmentService;

    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {

        User user = userLoginRequest.toEntity();

        //가천서버 로그인 성공시 세션발급
        String session = crawlingService.checkLogin(user.getUserId(), user.getPassword());
        user.setSession(session);

        Optional<User> findUser = userRepository.findByUserId(user.getUserId());

        //기존 사용자는 db에 session을 update
        //로그인한 사용자가 앱에 처음 로그인한 사용자 일시 사용자를 db에 등록
        findUser.ifPresentOrElse(m -> m.updateSession(session),
                () -> {
                    CrawlUser crawlUser = crawlingService.getUserInfo(session);
                    user.setMajor(crawlUser.getMajor());
                    user.setStudentId(crawlUser.getStudentId());
                    userRepository.save(user);
                });

        User newFindUser = userRepository.findByUserId(user.getUserId())
                .orElseThrow(NoSuchElementException::new);

        //로그인시 새로 크롤링 한다
        assignmentService.refreshAssignments(user.getUserId());
        UserLoginResponse userLoginResponse = new UserLoginResponse(newFindUser.getStudentId(), newFindUser.getMajor(),session);
        return userLoginResponse;
    }

    /*
     * */

    /*
    * //데이터 크롤링해서 가져오기
        crawling.getCrawledData(session);
        //if(is empty){user set }
        //if(ispresent){updateData}
        //크롤링데이터란 찾은 user에서 데이터 데이터 비교 로직
        //비교할 데이터가 없으면 바로 엔티티에 데이터 연결
    *
    * */
    /*private void validateDuplicate(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUserId(userLoginDTO.getUserId());
    }*/

}
