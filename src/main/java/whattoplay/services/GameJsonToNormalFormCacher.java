package whattoplay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import whattoplay.domain.dto.GameJson;
import whattoplay.domain.entities.*;
import whattoplay.persistence.GameFieldsDatabaseRepository;
import whattoplay.services.domain.GameJsonToGameConverter;
import whattoplay.services.persistance.GameDatabaseService;

/**
 * Since GameJson object send by Internet IgdbGame Database API is a json
 * containing lists of ids and objects, it doesn't fit relational database which I prefer to use.
 * This class converts those jsons objects to third normal form GameJson objects
 * and fills {@link whattoplay.domain.entities.GameDeveloper}, {@link whattoplay.domain.entities.GameGameModes},
 * etc. tables with needed data.
 * @see <a href="https://igdb.github.io/api/endpoints/game/">IGDB IgdbGame Endpoint</a>
 */

@Service
public class GameJsonToNormalFormCacher {
    private final GameFieldsDatabaseRepository gameFieldsDatabaseRepository;
    private final GameDatabaseService safeGameDatabaseService;
    private final GameJsonToGameConverter gameJsonToGameConverter;

    @Autowired
    public GameJsonToNormalFormCacher(GameFieldsDatabaseRepository gameFieldsDatabaseRepository,
                                      @Qualifier("safeGameDatabaseService") GameDatabaseService safeGameDatabaseService,
                                      GameJsonToGameConverter gameJsonToGameConverter) {
        this.gameFieldsDatabaseRepository = gameFieldsDatabaseRepository;
        this.safeGameDatabaseService = safeGameDatabaseService;
        this.gameJsonToGameConverter = gameJsonToGameConverter;
    }


    public void persistNormalFormOfGameJson(GameJson gameJson) {
        safeGameDatabaseService.persistGame(gameJsonToGameConverter.convert(gameJson));
        final long id = gameJson.getId();
        gameJson.getDevelopersIds()
                .forEach(developerId -> gameFieldsDatabaseRepository.persistGameDeveloper(new GameDeveloper(id, developerId)));
        gameJson.getGameModesIds()
                .forEach( gameModeId -> gameFieldsDatabaseRepository.persistGameGameModes( new GameGameModes( id, gameModeId) ));
        gameJson.getGenresIds()
                .forEach( genreId -> gameFieldsDatabaseRepository.persistGameGenres( new GameGenres(id, genreId))  );
        gameJson.getPlayerPerspectivesIds()
                .forEach( playerPerspectiveId -> gameFieldsDatabaseRepository.persistGamePlayerPerspectives( new GamePlayerPerspectives(id, playerPerspectiveId))  );
        gameJson.getWebsites()
                .forEach( website ->
                    gameFieldsDatabaseRepository.persistGameWebsites(
                            new GameWebsites (id, new Website(website.getWebsiteCategory(), website.getUrl().length() > 150 ? website.getUrl().substring(0, 149) : website.getUrl()  ) ) ));

    }

    public void persistNormalFormOfSetOFGameJsons(Iterable<GameJson> gameJsons) {
        gameJsons.forEach( this::persistNormalFormOfGameJson);
    }

}
