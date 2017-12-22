package whattoplay.persistence;

import whattoplay.domain.entities.GameMode;

import java.util.List;

public interface GameFieldsDatabaseRepository {
    List<GameMode> getAllGameModes();
}
