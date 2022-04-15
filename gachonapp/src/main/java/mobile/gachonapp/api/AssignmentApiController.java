package mobile.gachonapp.api;


import lombok.RequiredArgsConstructor;
import mobile.gachonapp.api.response.Result;
import mobile.gachonapp.api.response.SuccessResponse;
import mobile.gachonapp.service.AssignmentService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AssignmentApiController {

    private final AssignmentService assignmentService;

    @GetMapping("/api/assignment/list")
    public Result getAssignment(@CookieValue(name = "MoodleSession") String Session){

        assignmentService.getAssignmentList();

        //세션이 db 와 다르면 에러 던지고 로그인 창으로 (세션만료)
        // -> 로그인 세션 db update해주고 세션 사용자에게 주고
        // 세션 다시 사용

        List list = new ArrayList(Arrays.asList(1,Session));
        return new Result<>(SuccessResponse.ASSIGNMENT_LIST_SUCCESS,list);
    }

    @GetMapping("/api/assignment/submit-status")
    public Result updateAssignmentViewStatus(@CookieValue(name = "MoodleSession") String Session){
        // 제출여부 업데이트 , 리턴값은 상태만


        return null;
    }
}
