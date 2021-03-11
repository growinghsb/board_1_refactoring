package winfly.board_1.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import winfly.board_1.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private final EntityManager em;

    @Override
    public Long save(User user) {
        em.persist(user);
        return user.getId();
    }

    @Override
    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public void delete(User user) {
        em.remove(user);

    }
}
