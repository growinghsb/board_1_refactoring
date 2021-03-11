package winfly.board_1.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import winfly.board_1.entity.Content;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContentJpaRepository implements ContentRepository {

    private final EntityManager em;

    @Override
    public Long save(Content content) {
        em.persist(content);
        return content.getId();
    }

    @Override
    public Content findOne(Long id) {
        return em.find(Content.class, id);
    }

    @Override
    public List<Content> findAll() {
        return em.createQuery("select u from Content u", Content.class)
                .getResultList();
    }

    @Override
    public void delete(Content content) {
        em.remove(content);

    }
}
