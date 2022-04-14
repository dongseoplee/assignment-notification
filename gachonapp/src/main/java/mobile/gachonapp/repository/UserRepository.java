package mobile.gachonapp.repository;


import mobile.gachonapp.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

   /* public User findByUserId(String userId) {
       return em.createQuery("select m from User m where m.userId :userId", User.class)
                .setParameter("userId",userId)
                .getSingleResult();
    }*/

    public void save(User user) {

    }
}
