package mobile.gachonapp.service;

import lombok.RequiredArgsConstructor;
import mobile.gachonapp.domain.Assignment;
import mobile.gachonapp.domain.User;
import mobile.gachonapp.dto.AssignmentResponse;
import mobile.gachonapp.dto.SubmitStatusRequest;
import mobile.gachonapp.repository.AssignmentRepository;
import mobile.gachonapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final UserRepository userRepository;
    private final AssignmentRepository assignmentRepository;

    //db에 저장된 asssigment를 가져오는 경우( 크롤링을 새로하지 않는다 )
    public List<AssignmentResponse> getAssignments(String session) {

        //세션으로 db에서 사용자 찾기
        User findUser = userRepository.findBySession(session).get();
        List<Assignment> findAssignments = assignmentRepository.findAssignmentsByUserId(findUser.getUserId());

        return findAssignments.stream()
                .map(AssignmentResponse::createResponse)
                .collect(Collectors.toList());

    }

    // 크롤링을 새로해서 가져오는 경우 - 새로고침 - (세션이 만료되었을때 처리 로직을 고민해야한다.)
    // 새로고침시 세션을 서버에 주었을때 세션만료이면 로그인 진행 후 크롤링
    // 세션만료가 아니면 기존 세션으로 크롤링
    /*public List<Assignment> getAssignmentList() {



    }*/

    public void updateSubmitStats(String session,SubmitStatusRequest submitStatusRequest) {

        User findUser = userRepository.findBySession(session).get();
        Assignment findAssignment = assignmentRepository.findByUserIdAndAssignmentName(findUser.getUserId(),
                submitStatusRequest.getAssignmentName()).get();
        findAssignment.changeSubmitStatus(submitStatusRequest.getAssignmentSubmitStatus());

    }



}
