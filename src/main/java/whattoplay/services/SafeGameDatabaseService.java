package whattoplay.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whattoplay.domain.dto.GameDto;
import whattoplay.domain.entities.Game;
import whattoplay.persistence.GamesDatabaseRepository;
import whattoplay.services.domain.GameDtoConverter;
import whattoplay.services.domain.GameDtoToGameEntityConverter;

import java.util.Optional;

/**
 * By truncating data it allows saving games which fields are bigger than expected .
 */
@Service
public class SafeGameDatabaseService extends GameDatabaseService {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    public SafeGameDatabaseService(GamesDatabaseRepository databaseRepository,
                                   GameDtoConverter gameToGameDtoConverter,
                                   GameDtoToGameEntityConverter gameDtoToGameEntityConverter) {
        super(databaseRepository, gameToGameDtoConverter, gameDtoToGameEntityConverter);
    }

    public void persistGame(Game game) {
        logger.info(new StringBuilder().append("Persisting ").append(game.getName()).append(" id: ")
                .append(game.getId()).append(" collection id : ").append(game.getCollectionId()).append(" franchise id : ")
                .append(game.getFranchiseId()).toString() );
        if (game.getName().length() > 100) {
            game.setName(game.getName().substring(0, 99));
            logger.warn("Name " + game.getName() + " is too long, saving truncated version. ID: " + game.getId());
        }
        if (game.getSlug().length() > 150) {
            game.setSlug(game.getSlug().substring(0, 149));
            logger.warn("Slug " + game.getSlug() + " is too long, saving truncated version. ID: " + game.getId());
        }
        Optional.ofNullable(game.getUrl()).ifPresent(x -> {
            if (x.length() > 150){
                game.setUrl(game.getUrl().substring(0, 149));
                logger.warn("Url " + game.getUrl() + " is too long, saving truncated version. ID: " + game.getId());
            }
        });
        Optional.ofNullable(game.getExternal()).ifPresent(x -> {
            if (x.getSteam().length() > 150){
                x.setSteam(x.getSteam().substring(0, 149));
                logger.warn("Steam " + x.getSteam() + " is too long, saving truncated version. ID: "
                        + game.getId());
            }
        });
        Optional.ofNullable(game.getCover()).ifPresent(x -> {
            if (x.getImageUrl().length() > 150) {
                x.setImageUrl(x.getImageUrl().substring(0, 149));
                logger.warn("Image url " + x.getImageUrl() + " is too long, saving truncated version. ID: "
                        + game.getId());
            }
            if (x.getCloudinaryId().length() > 50){
                x.setCloudinaryId(x.getCloudinaryId().substring(0, 49));
                logger.warn("Cloudinary id " + x.getCloudinaryId() + " is too long, saving truncated version. ID: "
                        + game.getId());
            }
        });
        gamesDatabaseRepository.persistGame(game);
    }

    public void saveSetOfGames(Iterable<Game> games) {
        games.forEach(x -> gamesDatabaseRepository.persistGame(x));
    }

}
