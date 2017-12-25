package whattoplay.persistence;

import whattoplay.domain.entities.Developer;
import whattoplay.domain.entities.GameMode;
import whattoplay.domain.entities.Genre;
import whattoplay.domain.entities.Pegi;

import java.util.List;

public interface GameFieldsDatabaseRepository {
    List<GameMode> getAllGameModes();
    List<Genre> getAllGenres();
    List<Developer> getAllDevelopers();
}
