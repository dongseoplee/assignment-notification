package mobile.gachonapp.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class Subject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectId;
    private String subjectName;
    private String webLink;


    @OneToMany(mappedBy = "subject")
    private List<Assignment> assignments = new ArrayList<>();
}
