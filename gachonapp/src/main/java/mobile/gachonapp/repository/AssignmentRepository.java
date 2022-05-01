package mobile.gachonapp.repository;

import mobile.gachonapp.domain.Assignment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AssignmentRepository {

    @PersistenceContext
    private EntityManager em;


    public Optional<Assignment> findByUserIdAndAssignmentName(String findUserId, String courseName) {
        List<Assignment> assignments = (List<Assignment>) em.createQuery("select");
        return assignments.stream().findAny();
    }

    //유저 아이디로 찾기
    public List<Assignment> findAssignmentsByUserId(String userId) {

        // 필터링 조건(submitStatus 가 N, deadListStatus 가 EARLY인 애들, 과목 볼 여부 Y인애들)
        //List<Assignment> assignments = (List<Assignment>) em.createQuery("select");
        List<Assignment> assignments = new ArrayList<>();
        return assignments;
    }
}
