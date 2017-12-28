package whattoplay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whattoplay.domain.entities.*;
import whattoplay.persistence.GameFieldsDatabaseRepository;

@Service
public class GameJsonRationializer {
    private GameFieldsDatabaseRepository gameFieldsDatabaseRepository;


    @Autowired
    public GameJsonRationializer(GameFieldsDatabaseRepository gameFieldsDatabaseRepository) {
        this.gameFieldsDatabaseRepository = gameFieldsDatabaseRepository;
    }

    public void rationalizeGameJson(GameJson gameJson) {
        gameJson.getDevelopersIds()
                .forEach(developerId -> gameFieldsDatabaseRepository.persistGameDeveloper(new GameDeveloper(gameJson.getId(), developerId)));
        gameJson.getGameModesIds()
                .forEach( gameModeId -> gameFieldsDatabaseRepository.persistGameGameModes( new GameGameModes( gameJson.getId(), gameModeId) ));
        gameJson.getGenresIds()
                .forEach( genreId -> gameFieldsDatabaseRepository.persistGameGenres( new GameGenres(gameJson.getId(), genreId))  );
        gameJson.getPlayerPerspectivesIds()
                .forEach( playerPerspectiveId -> gameFieldsDatabaseRepository.persistGamePlayerPerspectives( new GamePlayerPerspectives(gameJson.getId(), playerPerspectiveId))  );
        gameJson.getWebsites()
                .forEach( website ->
                    gameFieldsDatabaseRepository.persistGameWebsites(
                            new GameWebsites (gameJson.getId(), new Website(website.getWebsiteCategory(), website.getUrl()))));
    }


}
