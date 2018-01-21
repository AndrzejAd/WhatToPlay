package whattoplay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import whattoplay.domain.dto.GameJson;
import whattoplay.domain.entities.*;
import whattoplay.persistence.GameFieldsDatabaseRepository;
import whattoplay.services.domain.GameJsonToGameConverter;
import whattoplay.services.persistance.GameDatabaseService;
import whattoplay.services.persistance.GameFieldsDaoService;

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
    private final GameDatabaseService safeGameDatabaseService;
    private final GameJsonToGameConverter gameJsonToGameConverter;
    private final GameFieldsDaoService gameFieldsDaoService;

    @Autowired
    public GameJsonToNormalFormCacher( @Qualifier("safeGameFieldsDatabaseService") GameFieldsDaoService gameFieldsDaoService,
                                      @Qualifier("safeGameDatabaseService") GameDatabaseService safeGameDatabaseService,
                                      GameJsonToGameConverter gameJsonToGameConverter) {
        this.safeGameDatabaseService = safeGameDatabaseService;
        this.gameJsonToGameConverter = gameJsonToGameConverter;
        this.gameFieldsDaoService = gameFieldsDaoService;
    }


    public void persistNormalFormOfGameJson(GameJson gameJson) {
        safeGameDatabaseService.persistGame(gameJsonToGameConverter.convert(gameJson));
        final long id = gameJson.getId();
        gameJson.getDevelopersIds()
                .forEach(developerId -> gameFieldsDaoService.saveGameDeveloper(new GameDeveloper(id, developerId)));
        gameJson.getGameModesIds()
                .forEach( gameModeId -> gameFieldsDaoService.saveGameGameModes( new GameGameModes( id, gameModeId) ));
        gameJson.getGenresIds()
                .forEach( genreId -> gameFieldsDaoService.saveGameGenres( new GameGenres(id, genreId))  );
        gameJson.getPlayerPerspectivesIds()
                .forEach( playerPerspectiveId -> gameFieldsDaoService.saveGamePlayerPerspectives( new GamePlayerPerspectives(id, playerPerspectiveId))  );
        gameJson.getWebsites()
                .forEach( website ->
                        gameFieldsDaoService.saveGameWebsite(
                            new GameWebsites (id, new Website(website.getWebsiteCategory(), website.getUrl().length() > 150 ? website.getUrl().substring(0, 149) : website.getUrl()  ) ) ));

    }

    public void persistNormalFormOfSetOFGameJsons(Iterable<GameJson> gameJsons) {
        gameJsons.forEach( this::persistNormalFormOfGameJson);
    }

}
