package mobile.gachonapp.repository;


import mobile.gachonapp.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    //null 일수도 있으니까 optional 반환!
    public Optional<User> findByUserId(String userId) {
        List<User> users = em.createQuery("select m from User m where m.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();
        return users.stream().findAny();
    }

    public void save(User user) {
        em.persist(user);
    }

    public void updateSession(User user) {
        Optional<User> findUser = findByUserId(user.getUserId());
        findUser.get().setSession(user.getSession());
    }
}
