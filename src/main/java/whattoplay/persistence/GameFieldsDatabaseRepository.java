package whattoplay.persistence;

import whattoplay.domain.entities.*;

import java.util.List;

public interface GameFieldsDatabaseRepository {
    List<GameMode> getAllGameModes();
    List<Genre> getAllGenres();
    List<Developer> getAllDevelopers();
    void persistField(Object genre);
    void persistGameMode(GameMode gameMode);
    void persistPlayerPerspective(PlayerPerspective playerPerspective);
    void persistDeveloper(Developer developer);
    void persistGenre(Genre genre);
    void persistFranchise(Franchise franchise);
    void persistCollection(Collection collection);
    void persistGameDeveloper(GameDeveloper gameDeveloper);
    void persistGameGameModes(GameGameModes gameGameModes);
    void persistGameGenres(GameGenres gameGenres);
    void persistGamePlayerPerspectives(GamePlayerPerspectives gamePlayerPerspectives);
    void persistGameWebsites(GameWebsites gameWebsites);
    long getFranchiseTableSize();
    long getDevelopersTableSize();
}
