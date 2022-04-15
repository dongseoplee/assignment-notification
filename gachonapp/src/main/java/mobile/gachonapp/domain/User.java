package mobile.gachonapp.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @Transient
    private String password;

    //세션값 저장 -- 테이블을 분리해야하나??
    private String session;

    @OneToMany(mappedBy = "user")
    private List<UserAssignment> userAssignments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private AutoLoginStatus autoLoginStatus = AutoLoginStatus.N;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
