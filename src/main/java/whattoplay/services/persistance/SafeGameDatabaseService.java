package whattoplay.services.persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whattoplay.domain.entities.Developer;
import whattoplay.domain.entities.IgdbGame;
import whattoplay.persistence.GamesDatabaseRepository;
import whattoplay.services.domain.GameDtoToIgdbGameConverter;
import whattoplay.services.domain.IgdbGameToGameDtoConverter;

import java.util.Optional;

/**
 * By truncating data it allows saving games which fields are bigger than expected .
 */
@Service
public class SafeGameDatabaseService extends GameDatabaseService {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    public SafeGameDatabaseService(GamesDatabaseRepository databaseRepository,
                                   IgdbGameToGameDtoConverter gameToGameDtoConverter,
                                   GameDtoToIgdbGameConverter gameDtoToIgdbGameConverter) {
        super(databaseRepository, gameToGameDtoConverter, gameDtoToIgdbGameConverter);
    }

    public void persistGame(IgdbGame igdbGame) {
        logger.info(new StringBuilder().append("Persisting ").append(igdbGame.getName()).append(" id: ")
                .append(igdbGame.getId()).append(" collection id : ").append(igdbGame.getCollectionId()).append(" franchise id : ")
                .append(igdbGame.getFranchiseId()).toString() );
        if (igdbGame.getName().length() > 100) {
            igdbGame.setName(igdbGame.getName().substring(0, 99));
            logger.warn("Name " + igdbGame.getName() + " is too long, saving truncated version. ID: " + igdbGame.getId());
        }
        if (igdbGame.getSlug().length() > 150) {
            igdbGame.setSlug(igdbGame.getSlug().substring(0, 149));
            logger.warn("Slug " + igdbGame.getSlug() + " is too long, saving truncated version. ID: " + igdbGame.getId());
        }
        Optional.ofNullable(igdbGame.getUrl()).ifPresent(x -> {
            if (x.length() > 150){
                igdbGame.setUrl(igdbGame.getUrl().substring(0, 149));
                logger.warn("Url " + igdbGame.getUrl() + " is too long, saving truncated version. ID: " + igdbGame.getId());
            }
        });
        Optional.ofNullable(igdbGame.getExternal()).ifPresent(x -> {
            if (x.getSteam().length() > 150){
                x.setSteam(x.getSteam().substring(0, 149));
                logger.warn("Steam " + x.getSteam() + " is too long, saving truncated version. ID: "
                        + igdbGame.getId());
            }
        });
        Optional.ofNullable(igdbGame.getCover()).ifPresent(x -> {
            if (x.getImageUrl().length() > 150) {
                x.setImageUrl(x.getImageUrl().substring(0, 149));
                logger.warn("Image url " + x.getImageUrl() + " is too long, saving truncated version. ID: "
                        + igdbGame.getId());
            }
            if (x.getCloudinaryId().length() > 50){
                x.setCloudinaryId(x.getCloudinaryId().substring(0, 49));
                logger.warn("Cloudinary id " + x.getCloudinaryId() + " is too long, saving truncated version. ID: "
                        + igdbGame.getId());
            }
        });
        gamesDatabaseRepository.persistGame(igdbGame);
    }

    public void saveSetOfGames(Iterable<IgdbGame> games) {
        games.forEach(gamesDatabaseRepository::persistGame);
    }

    public void saveDeveloper(Developer developer){

    }

}
