package kata.academy.crud.dao;

import kata.academy.crud.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u ORDER BY u.id", User.class).getResultList();
    }

    @Override
    public User getUserById (Long id) { return entityManager.find(User.class,id); }

    @Override
    public Long getIdByUsername(String username){
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.name = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult()
                    .getId();
        } catch (Throwable err) {
            return -1L;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.find(User.class, getIdByUsername(username));
    }

    @Transactional
    public void create(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public void delete(Long id) {
        entityManager.createQuery("DELETE FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
