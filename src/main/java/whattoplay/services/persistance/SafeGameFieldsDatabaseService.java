package whattoplay.services.persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whattoplay.domain.entities.*;
import whattoplay.persistence.GameFieldsDatabaseRepository;

import java.util.Optional;

@Service
public class SafeGameFieldsDatabaseService implements GameFieldsDaoService {
    private final GameFieldsDatabaseRepository gameFieldsDatabaseRepository;
    private static Logger logger = LogManager.getLogger();

    @Autowired
    public SafeGameFieldsDatabaseService(GameFieldsDatabaseRepository gameFieldsDatabaseRepository) {
        this.gameFieldsDatabaseRepository = gameFieldsDatabaseRepository;
    }

    @Override
    public void saveGameDeveloper(GameDeveloper gameDeveloper) {
        gameFieldsDatabaseRepository.persistGameDeveloper(gameDeveloper);
    }

    @Override
    public void saveGameGenres(GameGenres gameGenres) {
        gameFieldsDatabaseRepository.persistGameGenres(gameGenres);
    }

    @Override
    public void saveGameGameModes(GameGameModes gameGameModes) {
        gameFieldsDatabaseRepository.persistGameGameModes(gameGameModes);
    }

    @Override
    public void saveGamePlayerPerspectives(GamePlayerPerspectives gamePlayerPerspectives) {
        gameFieldsDatabaseRepository.persistGamePlayerPerspectives(gamePlayerPerspectives);
    }

    @Override
    public void saveGameWebsite(GameWebsites website) {
        gameFieldsDatabaseRepository.persistGameWebsites(website);
    }

    @Override
    public void saveSetOfDevelopers(Iterable<Developer> developers) {
        developers.forEach(x -> {
            if (x.getName().length() >= 100) {
                x.setName(x.getName().substring(0, 98));
                logger.warn("Name " + x.getName() + " is too long, saving truncated version.");
            }
            Optional.ofNullable(x.getUrl()).ifPresent(y -> {
                if (y.length() >= 100) {
                    x.setUrl(x.getUrl().substring(0, 98));
                    logger.warn("Url " + x.getUrl() + " is too long, saving truncated version.");
                }
            });
            Optional.ofNullable(x.getDeveloperImageCloudinaryId()).ifPresent(y -> {
                if (y.length() >= 100) {
                    x.setDeveloperImageCloudinaryId((x.getDeveloperImageCloudinaryId().substring(0, 98)));
                    logger.warn("Url " + x.getDeveloperImageCloudinaryId() + " is too long, saving truncated version.");
                }
            });
            gameFieldsDatabaseRepository.persistDeveloper(x);
        });
    }

    @Override
    public void saveSetOfFranchises(Iterable<Franchise> franchises) {
        franchises.forEach(x -> {
            if (x.getName().length() >= 150) x.setName(x.getName().substring(0, 148));
            Optional.ofNullable(x.getUrl()).ifPresent(y -> {
                if (y.length() >= 100) x.setUrl(x.getUrl().substring(0, 98));
            });
            logger.info(new StringBuilder().append("Persisting Franchise: ").append(x.getId()).append(" ").append(x.getName()).append(": ").append(x.getUrl()).append(" ").append(x.getCreatedAt()).toString());
            gameFieldsDatabaseRepository.persistFranchise(x);
        });
    }

    @Override
    public void saveSetOfCollections(Iterable<Collection> collections) {
        collections.forEach(x -> {
            if (x.getName().length() >= 150) {
                x.setName(x.getName().substring(0, 148));
                logger.warn(new StringBuilder().append("Name of").append(x.getId()).append(" ")
                        .append(x.getName()).append("is too long! Saving shortened version").append(x.getName()));
            }
            Optional.ofNullable(x.getUrl()).ifPresent(y -> {
                if (y.length() >= 100) {
                    x.setUrl(x.getUrl().substring(0, 98));
                    logger.warn(new StringBuilder().append("URL of").append(x.getId()).append(" ")
                            .append(x.getName()).append("is too long! Saving shortened version").append(x.getUrl()));
                }
            });
            gameFieldsDatabaseRepository.persistCollection(x);
        });
    }

}
