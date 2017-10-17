/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whattoplay.persistence;

import whattoplay.domain.entities.GameEntity;

import java.util.List;

/**
 *
 * @author Andrzej
 */
public interface GamesDatabaseRepository {
    void insertGame(GameEntity game);
    List<GameEntity> getRandomGames(int numberOfGames);
    long getNumberOfRows();
    GameEntity getGameById(long gameId);
    List<GameEntity> getGamesByGenre(String genre);
    List<Long> getRandomIdsFromDatabase(int numbOfGames);
}
