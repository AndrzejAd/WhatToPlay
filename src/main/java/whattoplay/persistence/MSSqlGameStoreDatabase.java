/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whattoplay.persistence;

import whattoplay.domain.entities.GameEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Repository
@Transactional
public class MSSqlGameStoreDatabase implements GamesDatabaseRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    public MSSqlGameStoreDatabase(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
  
    @Override
    public void insertGame(GameEntity game) {
        entityManager.persist(game);
    }

    @Override
    public List<GameEntity> getRandomGames(int numberOfGames) {
        List<GameEntity> gamesList = new ArrayList<>();
        getRandomIdsFromDatabase(numberOfGames).forEach( id -> {
            gamesList.add( getGameById(id) );
        });
        return gamesList;
    }

    @Override
    public List<Long> getRandomIdsFromDatabase(int numbOfGames) {
        String column = "gameId";
        List<Long> gameIds = new ArrayList<>();
        String query = "SELECT " + column + " FROM GameEntity ORDER BY NEWID()";
        Query q = entityManager.createQuery(query);
        q.setMaxResults( numbOfGames );
        gameIds = q.getResultList();
        return gameIds;
    }

    @Override
    public long getNumberOfRows(){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery( Long.class );
        criteria.select(builder.count(criteria.from(GameEntity.class ) ) );
        Long value = entityManager.createQuery(criteria).getSingleResult();
        return value;
    }

    @Override
    public GameEntity getGameById(long gameId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GameEntity> query = builder.createQuery(GameEntity.class );
        Root<GameEntity> game = query.from(GameEntity.class);
        query.select(game).
            where( builder.equal( game.get("gameId"), gameId ) );
        TypedQuery<GameEntity> tq = entityManager.createQuery(query);
        return tq.getSingleResult();
    }

    @Override
    public List<GameEntity> getGamesByGenre(String genre) {
        List<GameEntity> gamesByGenre;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GameEntity> query = builder.createQuery(GameEntity.class );
        Root<GameEntity> game = query.from(GameEntity.class);
        query.select(game).
            where( builder.equal( game.get("genre"), genre ) );
        TypedQuery<GameEntity> tq = entityManager.createQuery(query);
        gamesByGenre = tq.getResultList();
        gamesByGenre.forEach( (x) -> System.out.println( x.getGameName() ) );
        return gamesByGenre;
    }
    
    @Deprecated
    public List<Long> returnUniqueRandomIntegers(int numberOfIntegers, Long max){
        long arr[] = ThreadLocalRandom.current().longs(1, max ).distinct().limit(numberOfIntegers).toArray();
        List<Long> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        return list;
    }
    
    
}
