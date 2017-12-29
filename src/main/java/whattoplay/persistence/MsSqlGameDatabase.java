/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whattoplay.persistence;

import org.hibernate.exception.ConstraintViolationException;
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
    private CriteriaQuery<Game> gameEntityQuery;
    private Root<Game> gameEntityGame;

    public MsSqlGameDatabase(EntityManager entityManager) {
        this.entityManager = entityManager;
        gameEntityBuilder = entityManager.getCriteriaBuilder();
        gameEntityQuery = gameEntityBuilder.createQuery(Game.class);
        gameEntityGame = gameEntityQuery.from(Game.class);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persistGame(Game game) {
            entityManager.persist(game);
    }

    @Override
    public long getNumberOfRows() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        criteria.select(builder.count(criteria.from(Game.class)));
        Long value = entityManager.createQuery(criteria).getSingleResult();
        return 1;
    }

    @Override
    public Game deleteGameByGameName(String gameName) {
        gameEntityQuery.select(gameEntityGame).
                where(gameEntityBuilder.equal(gameEntityGame.get("name"), gameName));
        TypedQuery<Game> tq = entityManager.createQuery(gameEntityQuery);
        Game searchedGame = tq.getSingleResult();
        Optional.ofNullable( searchedGame ).ifPresent( entityManager::remove );
        return searchedGame;
    }

    @Override
    public Game getGameById(long gameId) {
        return entityManager.find(Game.class, gameId);
    }

    /** TODO **/
    @Override
    public Game updateGame(Game updatedGame) {
//        Game game = entityManager.find(Game.class, updatedGame.getGameId());
//        Optional.ofNullable(updatedGame.getName()).ifPresent(game::getName);
//        Optional.ofNullable(updatedGame.getProducer()).ifPresent(game::setProducer);
//        Optional.ofNullable(updatedGame.getPublisher()).ifPresent(game::setPublisher);
//        Optional.ofNullable(updatedGame.getGenre()).ifPresent(game::setGenre);
//        Optional.ofNullable(updatedGame.getDatePublished()).ifPresent(game::setDatePublished);
//        Optional.ofNullable(updatedGame.getPrice()).ifPresent(game::setPrice);
//        Optional.ofNullable(updatedGame.getImagePath()).ifPresent(game::setImagePath);
//        return entityManager.merge(game);
        return null;
    }

    @Override
    public List<Long> getRandomIdsFromGameTable(int numbOfGames) {
        List<Long> gameIds;
        String query = "SELECT g.id FROM Game g ORDER BY id";
        Query q = entityManager.createQuery(query);
        q.setMaxResults(numbOfGames);
        gameIds = q.getResultList();
        return gameIds;
    }

    @Override
    public List<Game> getRandomGames(int numberOfGames) {
        List<Game> gamesList = new ArrayList<>();
        getRandomIdsFromGameTable(numberOfGames).forEach(id -> {
            gamesList.add(getGameById(id));
        });
        return gamesList;
    }

    @Override
    public List<Game> getGamesByGenre(String genre) {
        gameEntityQuery.select(gameEntityGame).
                where(gameEntityBuilder.equal(gameEntityGame.get("genre"), genre));
        TypedQuery<Game> tq = entityManager.createQuery(gameEntityQuery);
        return tq.getResultList();
    }

    @Override
    public List<Game> getGamesByName(String gameName) {
        gameEntityQuery.select(gameEntityGame)
                .where( gameEntityBuilder.like(gameEntityGame.get("gameName"), gameName + "%" ));
        TypedQuery<Game> tq = entityManager.createQuery(gameEntityQuery);
        return tq.getResultList();
    }

    @Deprecated
    public List<Long> returnUniqueRandomIntegers(int numberOfIntegers, Long max) {
        long arr[] = ThreadLocalRandom.current().longs(1, max).distinct().limit(numberOfIntegers).toArray();
        List<Long> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        return list;
    }

}
