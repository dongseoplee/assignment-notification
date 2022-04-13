package mobile.gachonapp.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@Entity
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    /*//db에 저장하지 않는 컬럼
    @Transient
    private String password;*/

    @OneToMany(mappedBy = "user")
    private List<UserCourse> userSubjects = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private AutoLoginStatus autoLoginStatus = AutoLoginStatus.N;


}
