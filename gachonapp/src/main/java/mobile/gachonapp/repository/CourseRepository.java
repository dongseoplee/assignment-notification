package mobile.gachonapp.repository;

import mobile.gachonapp.domain.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CourseRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Course> findCoursesByUserId(String userId) {
        return em.createQuery("select m from Course m " +
                "where m.user.userId = :userId", Course.class)
                .setParameter("userId",userId)
                .getResultList();
    }

    public List<Course> findByUserIdAndCourseName(String userId, String courseName) {
        List<Course> courses = (List<Course>) em.createQuery("select m from Course m join");
        return courses;
    }
}
