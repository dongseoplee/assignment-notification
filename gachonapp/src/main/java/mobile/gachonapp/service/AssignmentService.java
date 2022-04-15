package mobile.gachonapp.service;

import lombok.RequiredArgsConstructor;
import mobile.gachonapp.domain.Assignment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    public List<Assignment> getAssignmentList() {
        //세션으로 db에서 사용자 아이디 찾기
        //다르면 에러 던지기(세션 익셉션)
        //같으면 로직진행

        //사용자 아이디로(해당하는 과목 리스트 가져오기 )
        // 필터링 조건(submitStatus 가 N, deadListStatus 가 EARLY인 애들, 과목 볼 여부 Y인애들)
        // retrun list

        return new ArrayList<>();
    }

    //
    /*public List<Assignment> getAssignmentList() {
        //세션으로 db에서 사용자 아이디 찾기
        //다르면 에러 던지기(세션 익셉션)
        //같으면 로직진행

        //사용자 아이디로(해당하는 과목 리스트 가져오기 )
        // 필터링 조건(submitStatus 가 N, deadListStatus 가 EARLY인 애들, 과목 볼 여부 Y인애들)
        // retrun list

        return new ArrayList<>();
    }*/
}
