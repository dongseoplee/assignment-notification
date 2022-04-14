package mobile.gachonapp.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Builder
@Getter @Setter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    //세션값 저장 -- 테이블을 분리해야하나??
    private String session;

    @OneToMany(mappedBy = "user")
    private List<UserAssignment> userAssignments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private AutoLoginStatus autoLoginStatus = AutoLoginStatus.N;


}
