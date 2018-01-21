/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whattoplay.persistence;

import whattoplay.domain.entities.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Repository
@Transactional
public class MsSqlGameDatabase implements GamesDatabaseRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private CriteriaBuilder gameEntityBuilder;
    private CriteriaQuery<IgdbGame> gameEntityQuery;
    private Root<IgdbGame> gameEntityGame;

    public MsSqlGameDatabase(EntityManager entityManager) {
        this.entityManager = entityManager;
        gameEntityBuilder = entityManager.getCriteriaBuilder();
        gameEntityQuery = gameEntityBuilder.createQuery(IgdbGame.class);
        gameEntityGame = gameEntityQuery.from(IgdbGame.class);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persistGame(IgdbGame igdbGame) {
        entityManager.persist(igdbGame);
    }

    @Override
    public long getNumberOfRows() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        criteria.select(builder.count(criteria.from(IgdbGame.class)));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    @Override
    public IgdbGame deleteGameByGameName(String gameName) {
        gameEntityQuery.select(gameEntityGame).
                where(gameEntityBuilder.equal(gameEntityGame.get("name"), gameName));
        TypedQuery<IgdbGame> tq = entityManager.createQuery(gameEntityQuery);
        IgdbGame searchedIgdbGame = tq.getSingleResult();
        Optional.ofNullable(searchedIgdbGame).ifPresent( entityManager::remove );
        return searchedIgdbGame;
    }

    @Override
    public IgdbGame getGameById(long gameId) {
        return entityManager.find(IgdbGame.class, gameId);
    }

    /** TODO **/
    @Override
    public IgdbGame updateGame(IgdbGame updatedIgdbGame) {
//        IgdbGame game = entityManager.find(IgdbGame.class, updatedIgdbGame.getGameId());
//        Optional.ofNullable(updatedIgdbGame.getName()).ifPresent(game::getName);
//        Optional.ofNullable(updatedIgdbGame.getProducer()).ifPresent(game::setProducer);
//        Optional.ofNullable(updatedIgdbGame.getPublisher()).ifPresent(game::setPublisher);
//        Optional.ofNullable(updatedIgdbGame.getGenre()).ifPresent(game::setGenre);
//        Optional.ofNullable(updatedIgdbGame.getDatePublished()).ifPresent(game::setDatePublished);
//        Optional.ofNullable(updatedIgdbGame.getPrice()).ifPresent(game::setPrice);
//        Optional.ofNullable(updatedIgdbGame.getImagePath()).ifPresent(game::setImagePath);
//        return entityManager.merge(game);
        return null;
    }

    @Override
    public List<Long> getRandomIdsFromGameTable(int numbOfGames) {
        String query = "SELECT g.id FROM Game g ORDER BY id";
        Query q = entityManager.createQuery(query);
        q.setMaxResults(numbOfGames);
        return q.getResultList();
    }

    @Override
    public List<IgdbGame> getRandomGames(int numberOfGames) {
        List<IgdbGame> gamesList = new ArrayList<>();
        getRandomIdsFromGameTable(numberOfGames).forEach(id -> {
            gamesList.add(getGameById(id));
        });
        return gamesList;
    }

    @Override
    public List<IgdbGame> getGamesByGenre(String genre) {
        gameEntityQuery.select(gameEntityGame).
                where(gameEntityBuilder.equal(gameEntityGame.get("genre"), genre));
        TypedQuery<IgdbGame> tq = entityManager.createQuery(gameEntityQuery);
        return tq.getResultList();
    }

    @Override
    public List<IgdbGame> getGamesByName(String gameName) {
        gameEntityQuery.select(gameEntityGame)
                .where( gameEntityBuilder.like(gameEntityGame.get("gameName"), gameName + "%" ));
        TypedQuery<IgdbGame> tq = entityManager.createQuery(gameEntityQuery);
        return tq.getResultList();
    }

    @Deprecated
    public List<Long> returnUniqueRandomIntegers(int numberOfIntegers, Long max) {
        long arr[] = ThreadLocalRandom.current().longs(1, max).distinct().limit(numberOfIntegers).toArray();
        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }

}
