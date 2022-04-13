package mobile.gachonapp.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class UserCourse {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "userId")
    private User user;

    @ManyToOne @JoinColumn(name = "subjectId")
    private Subject subject;

}
