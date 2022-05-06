package mobile.gachonapp.repository;

import mobile.gachonapp.domain.Assignment;
import mobile.gachonapp.domain.AssignmentDeadLineStatus;
import mobile.gachonapp.domain.AssignmentSubmitStatus;
import mobile.gachonapp.domain.CourseViewStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AssignmentRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Assignment> findByUserId(String userId) {
        return em.createQuery("select a from Assignment a where a.user.userId =: userId")
                .setParameter("userId",userId)
                .getResultList();
    }

    //제출하지 않은 과목
    public List<Assignment> findToBeSubmitByUserId(String userId) {

        //assignment dead line : EARLY (제출기한이 지나지 않은 과목)
        //assignment submit Staus: N (제출하지 않은 과목)
        //과목 볼 여부 Y인애들
        List<Assignment> assignments =
                em.createQuery("select a from Assignment a " +
                                "left join User u on u = a.user " +
                                "where a.assignmentDeadLineStatus =: deadLine " +
                                "and a.assignmentSubmitStatus =: submit " +
                                "and a.course.courseViewStatus =: view " +
                                "order by a.deadLine asc ")
                        .setParameter("deadLine", AssignmentDeadLineStatus.EARLY)
                        .setParameter("submit", AssignmentSubmitStatus.N)
                        .setParameter("view", CourseViewStatus.TRUE)
                        .getResultList();
        return assignments;
    }

    //제출한과목
    public List<Assignment> findSubmitByUserId(String userId) {
        //assignment submit Status: Y (제출한 과목)
        //과목 볼 여부 Y인애들
        List<Assignment> assignments =
                em.createQuery("select a from Assignment a " +
                                "left join User u on u = a.user " +
                                "where a.assignmentSubmitStatus =: submit " +
                                "and a.course.courseViewStatus =: view " +
                                "order by a.deadLine desc ")
                        .setParameter("submit", AssignmentSubmitStatus.Y)
                        .setParameter("view", CourseViewStatus.TRUE)
                        .getResultList();
        return assignments;
    }

    public List<Assignment> findNotSubmitByUserId(String userId) {

        //assignment dead line : OVERDUE (제출기한이 지난 과목)
        //assignment submit Status: N (제출하지 않은 과목)
        //과목 볼 여부 Y인애들
        List<Assignment> assignments =
                em.createQuery("select a from Assignment a " +
                                "left join User u on u = a.user " +
                                "where a.assignmentDeadLineStatus =: deadLine " +
                                "and a.assignmentSubmitStatus =: submit " +
                                "and a.course.courseViewStatus =: view " +
                                "order by a.deadLine desc ")
                        .setParameter("deadLine", AssignmentDeadLineStatus.OVERDUE)
                        .setParameter("submit", AssignmentSubmitStatus.N)
                        .setParameter("view", CourseViewStatus.TRUE)
                        .getResultList();
        return assignments;
    }
}
