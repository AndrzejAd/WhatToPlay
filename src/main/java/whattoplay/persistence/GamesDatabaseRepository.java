/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whattoplay.persistence;

import whattoplay.domain.entities.*;

import java.util.List;

public interface GamesDatabaseRepository {
    void persistGame(IgdbGame igdbGame);
    long getNumberOfRows();
    IgdbGame deleteGameByGameName(String gameName);
    IgdbGame getGameById(long gameId);
    IgdbGame updateGame(IgdbGame updatedIgdbGame);
    List getRandomIdsFromGameTable(int numbOfGames);
    List<IgdbGame> getRandomGames(int numberOfGames);
    List<IgdbGame> getGamesByGenre(String genre);
    List<IgdbGame> getGamesByName(String gameName);
}
