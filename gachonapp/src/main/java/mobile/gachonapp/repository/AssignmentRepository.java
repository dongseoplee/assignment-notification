package mobile.gachonapp.repository;

import mobile.gachonapp.domain.Assignment;
import mobile.gachonapp.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AssignmentRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Assignment> findAssignmentList(User user) {

        // em.createQuery();
        // 아이디로 찾기
        // 필터링 조건(submitStatus 가 N, deadListStatus 가 EARLY인 애들, 과목 볼 여부 Y인애들)
        return null;
    }
}
