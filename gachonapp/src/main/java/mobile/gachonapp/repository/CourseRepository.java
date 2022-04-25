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


    //view 상태 걸러서 가져와라
    public List<Course> findByUserId(String userId) {
        List<Course> courses = (List<Course>) em.createQuery("select m from Course m join");
        return courses;
    }

    public List<Course> findByUserIdAndCourseName(String userId, String courseName) {
        List<Course> courses = (List<Course>) em.createQuery("select m from Course m join");
        return courses;
    }
}
