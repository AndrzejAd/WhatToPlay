/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whattoplay.persistence;

import whattoplay.domain.entities.*;

import java.util.List;

/**
 *
 * @author Andrzej
 */
public interface GamesDatabaseRepository {
    void persistGame(Game game);
    long getNumberOfRows();
    Game deleteGameByGameName(String gameName);
    Game getGameById(long gameId);
    Game updateGame(Game updatedGame);
    List<Long> getRandomIdsFromGameTable(int numbOfGames);
    List<Game> getRandomGames(int numberOfGames);
    List<Game> getGamesByGenre(String genre);
    List<Game> getGamesByName(String gameName);
}
