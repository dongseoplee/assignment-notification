package mobile.gachonapp.repository;

import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.domain.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Slf4j
public class CourseRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Course> findByUserId(String userId) {
        return em.createQuery("select c from Course c join User u on u.userId =: userId")
                .setParameter("userId",userId)
                .getResultList();
    }

    public void saveAll(List<Course> crawledCourses) {
        for (Course crawledCourse : crawledCourses) {
            em.persist(crawledCourse);
        }
    }
}
