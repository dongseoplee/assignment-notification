package mobile.gachonapp.api;

import lombok.RequiredArgsConstructor;
import mobile.gachonapp.api.response.Result;
import mobile.gachonapp.service.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubjectApiController {

    private SubjectService subjectService;


    @PostMapping("/api/subject/view-status")
    public Result updateSubjectStatus() {

        //json에서 subjectname 과 상태를 받는다.
        //해당 session으로 사용자 찾고 해당 사용자의 subjectname을 찾아 상태를 바꾼다
        //리턴값은 data 없이 상태만 반환
        return null;
    }

    @GetMapping("/api/subject/webex")
    public Result getWebLink() {
        //모든 과목 웹엑스 링크 준다 (링크가 없는 과목 어떻게 해결?? -> 우리가 db에 직접 넣어라...)
        //webLink 반환
        return null;
    }






}
