/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whattoplay.persistence;

import whattoplay.domain.entities.GameEntity;
import whattoplay.domain.entities.GameMode;
import whattoplay.domain.entities.Genre;

import java.util.List;

/**
 *
 * @author Andrzej
 */
public interface GamesDatabaseRepository {
    void persistGame(GameEntity game);
    void persistGenre(Genre genre);
    void persistGameMode(GameMode gameMode);
    long getNumberOfRows();
    GameEntity deleteGameByGameName(String gameName);
    GameEntity getGameById(long gameId);
    GameEntity updateGame(GameEntity updatedGameEntity);
    List<Long> getRandomIdsFromGameTable(int numbOfGames);
    List<GameEntity> getRandomGames(int numberOfGames);
    List<GameEntity> getGamesByGenre(String genre);
    List<GameEntity> searchGamesByName(String gameName);
}
