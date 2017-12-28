package whattoplay.persistence;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import whattoplay.domain.entities.*;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public class MsSqlGameFieldsDatabaseRepository implements GameFieldsDatabaseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public MsSqlGameFieldsDatabaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persistField(Object gameField) throws HibernateException {
        if ( Arrays.asList( gameField.getClass().getDeclaredAnnotations() ).contains(Entity.class) ) {
            entityManager.persist(gameField);
        } else{
            throw new HibernateException("Not an entity.");
        }
    }

    @Override
    public void persistGameMode(GameMode gameMode) {
        entityManager.persist(gameMode);
    }

    @Override
    public void persistPlayerPerspective(PlayerPerspective playerPerspective) {
        entityManager.persist(playerPerspective);
    }

    @Override
    public void persistDeveloper(Developer developer) {
        entityManager.persist(developer);
    }

    @Override
    public void persistGenre(Genre genre) {
        entityManager.persist(genre);
    }

    @Override
    public void persistFranchise(Franchise franchise) {
        entityManager.persist(franchise);
    }

    @Override
    public void persistCollection(Collection collection) {
        entityManager.persist(collection);
    }

    @Override
    public void persistGameDeveloper(GameDeveloper gameDeveloper) {
        entityManager.persist(gameDeveloper);
    }

    @Override
    public void persistGameGameModes(GameGameModes gameGameModes) {
        entityManager.persist(gameGameModes);
    }

    @Override
    public void persistGamePlayerPerspectives(GamePlayerPerspectives gamePlayerPerspectives) {
        entityManager.persist(gamePlayerPerspectives);
    }

    @Override
    public void persistGameGenres(GameGenres gameGenres) {
        entityManager.persist(gameGenres);
    }

    @Override
    public void persistGameWebsites(GameWebsites gameWebsites) {
        entityManager.persist(gameWebsites);
    }

    @Override
    public List<GameMode> getAllGameModes() {
        return (ArrayList<GameMode>) entityManager
                .createQuery("SELECT a FROM GameMode a")
                .getResultList();
    }

    @Override
    public List<Genre> getAllGenres() {
        return (ArrayList<Genre>) entityManager
                .createQuery("SELECT a FROM Genre a")
                .getResultList();
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return (ArrayList<Developer>) entityManager
                .createQuery("SELECT a FROM Developer a")
                .getResultList();
    }

    @Override
    public long getFranchiseTableSize() {
        return (Long) entityManager
                .createQuery("SELECT count(1) FROM Franchise a")
                .getSingleResult();
    }

    @Override
    public long getDevelopersTableSize() {
        return (Long) entityManager
                .createQuery("SELECT count(1) FROM Developer a")
                .getSingleResult();
    }
}
