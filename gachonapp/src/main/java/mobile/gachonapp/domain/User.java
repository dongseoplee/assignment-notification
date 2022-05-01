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
    private String session;

    @Enumerated(EnumType.STRING)
    private AutoLoginStatus autoLoginStatus = AutoLoginStatus.N;

    @OneToMany(mappedBy = "user")
    private List<Course> courses = new ArrayList<>();

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public void updateSession(String session) {
        this.session = session;
    }

}
