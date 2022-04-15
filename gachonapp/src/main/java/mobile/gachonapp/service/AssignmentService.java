package mobile.gachonapp.service;

import lombok.RequiredArgsConstructor;
import mobile.gachonapp.domain.User;
import mobile.gachonapp.dto.SubmitStatusRequest;
import mobile.gachonapp.repository.AssignmentRepository;
import mobile.gachonapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final UserRepository userRepository;
    private final AssignmentRepository assignementRepository;

    //db에 저장된 asssigment를 가져오는 경우( 크롤링을 새로하지 않는다 )
    public List<mobile.gachonapp.domain.Assignment> getAssignmentList(String session) {

        //세션으로 db에서 사용자 아이디 찾기
        Optional<User> findUser = userRepository.findByUserId(session);

        //TODO optinal 처리 로직 리팩토링 (flatmap 고민)
        if(findUser.isPresent()) {
            assignementRepository.findAssignmentList(findUser.get());
        }

        return new ArrayList<>();
    }

    // 크롤링을 새로해서 가져오는 경우 - 새로고침 - (세션이 만료되었을때 처리 로직을 고민해야한다.)
    // 새로고침시 세션을 서버에 주었을때 세션만료이면 로그인 진행 후 크롤링
    // 세션만료가 아니면 기존 세션으로 크롤링
    /*public List<Assignment> getAssignmentList() {



    }*/


    //도메인을 반환하고 컨트롤러에서 변환
    //과제 제출여부는 영속상태에서 변경감지를 통해 변환(changeStatus?)
    //TODO 과제제출여부 api(update)
    public void updateSubmitStats(SubmitStatusRequest submitStatusRequest) {


    }



}
