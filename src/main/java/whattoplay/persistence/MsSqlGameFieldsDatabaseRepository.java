package whattoplay.persistence;

import org.springframework.stereotype.Repository;
import whattoplay.domain.entities.GameEntity;
import whattoplay.domain.entities.GameMode;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class MsSqlGameFieldsDatabaseRepository implements GameFieldsDatabaseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public MsSqlGameFieldsDatabaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<GameMode> getAllGameModes() {
        Query query = entityManager.createQuery("SELECT a FROM GameMode a");
        return (ArrayList<GameMode>) query.getResultList();
    }
}
