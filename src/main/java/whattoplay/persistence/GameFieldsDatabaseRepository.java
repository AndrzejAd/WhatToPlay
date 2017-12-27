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
    long getFranchiseTableSize();
    long getDevelopersTableSize();
}
