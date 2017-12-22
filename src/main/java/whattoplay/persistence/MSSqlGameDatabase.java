/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whattoplay.persistence;

import whattoplay.domain.entities.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Repository
@Transactional
public class MSSqlGameDatabase implements GamesDatabaseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private CriteriaBuilder gameEntityBuilder;
    private CriteriaQuery<GameEntity> gameEntityQuery;
    private Root<GameEntity> gameEntityGame;

    public MSSqlGameDatabase(EntityManager entityManager) {
        this.entityManager = entityManager;
        gameEntityBuilder = entityManager.getCriteriaBuilder();
        gameEntityQuery = gameEntityBuilder.createQuery(GameEntity.class);
        gameEntityGame = gameEntityQuery.from(GameEntity.class);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persistGame(GameEntity game) {
        entityManager.persist(game);
    }

    @Override
    public void persistGenre(Genre genre) {
        entityManager.persist(genre);
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
    public long getNumberOfRows() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        criteria.select(builder.count(criteria.from(GameEntity.class)));
        Long value = entityManager.createQuery(criteria).getSingleResult();
        return value;
    }

    @Override
    public GameEntity deleteGameByGameName(String gameName) {
        gameEntityQuery.select(gameEntityGame).
                where(gameEntityBuilder.equal(gameEntityGame.get("gameName"), gameName));
        TypedQuery<GameEntity> tq = entityManager.createQuery(gameEntityQuery);
        GameEntity searchedGame = tq.getSingleResult();
        Optional.ofNullable( searchedGame ).ifPresent( entityManager::remove );
        return searchedGame;
    }

    @Override
    public GameEntity getGameById(long gameId) {
        gameEntityQuery.select(gameEntityGame).
                where(gameEntityBuilder.equal(gameEntityGame.get("gameId"), gameId));
        TypedQuery<GameEntity> tq = entityManager.createQuery(gameEntityQuery);
        return tq.getSingleResult();
    }

    @Override
    public GameEntity updateGame(GameEntity updatedGameEntity) {
        GameEntity game = entityManager.find(GameEntity.class, updatedGameEntity.getGameId());
        Optional.ofNullable(updatedGameEntity.getGameName()).ifPresent(game::setGameName);
        Optional.ofNullable(updatedGameEntity.getProducer()).ifPresent(game::setProducer);
        Optional.ofNullable(updatedGameEntity.getPublisher()).ifPresent(game::setPublisher);
        Optional.ofNullable(updatedGameEntity.getGenre()).ifPresent(game::setGenre);
        Optional.ofNullable(updatedGameEntity.getDatePublished()).ifPresent(game::setDatePublished);
        Optional.ofNullable(updatedGameEntity.getPrice()).ifPresent(game::setPrice);
        Optional.ofNullable(updatedGameEntity.getImagePath()).ifPresent(game::setImagePath);
        return entityManager.merge(game);
    }

    @Override
    public List<Long> getRandomIdsFromGameTable(int numbOfGames) {
        String column = "gameId";
        List<Long> gameIds;
        String query = "SELECT " + column + " FROM GameEntity ORDER BY NEWID()";
        Query q = entityManager.createQuery(query);
        q.setMaxResults(numbOfGames);
        gameIds = q.getResultList();
        return gameIds;
    }

    @Override
    public List<GameEntity> getRandomGames(int numberOfGames) {
        List<GameEntity> gamesList = new ArrayList<>();
        getRandomIdsFromGameTable(numberOfGames).forEach(id -> {
            gamesList.add(getGameById(id));
        });
        return gamesList;
    }

    @Override
    public List<GameEntity> getGamesByGenre(String genre) {
        gameEntityQuery.select(gameEntityGame).
                where(gameEntityBuilder.equal(gameEntityGame.get("genre"), genre));
        TypedQuery<GameEntity> tq = entityManager.createQuery(gameEntityQuery);
        return tq.getResultList();
    }

    @Override
    public List<GameEntity> searchGamesByName(String gameName) {
        gameEntityQuery.select(gameEntityGame)
                .where( gameEntityBuilder.like(gameEntityGame.get("gameName"), gameName + "%" ));
        TypedQuery<GameEntity> tq = entityManager.createQuery(gameEntityQuery);
        return tq.getResultList();
    }

    @Deprecated
    public List<Long> returnUniqueRandomIntegers(int numberOfIntegers, Long max) {
        long arr[] = ThreadLocalRandom.current().longs(1, max).distinct().limit(numberOfIntegers).toArray();
        List<Long> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        return list;
    }

}
