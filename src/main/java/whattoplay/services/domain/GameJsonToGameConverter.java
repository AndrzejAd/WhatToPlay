package whattoplay.services.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whattoplay.domain.entities.IgdbGame;
import whattoplay.domain.dto.GameJson;
import whattoplay.persistence.GameFieldsDatabaseRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class GameJsonToGameConverter {

    private GameFieldsDatabaseRepository gameFieldsDatabaseRepository;

    @Autowired
    public GameJsonToGameConverter(GameFieldsDatabaseRepository gameFieldsDatabaseRepository) {
        this.gameFieldsDatabaseRepository = gameFieldsDatabaseRepository;
    }

    public IgdbGame convert(GameJson from){
        return new IgdbGame(from.getId(), from.getName(), from.getSlug(), from.getUrl(), from.getSummary(), from.getStoryline(),
                from.getHypes(), from.getPopularity(), from.getRating(), from.getRatingCount(), from.getAggregatedRating(),
                from.getAggregatedRatingCount(), from.getTotalRating(), from.getTotalRatingCount(),
                from.getCollectionId(),
                from.getFranchiseId(),
                from.getCreatedAt(), from.getUpdatedAt(), from.getFirstReleaseDate(), from.getStatus(),
                from.getTimeToBeat(), from.getEsrb(), from.getPegi(), from.getExternal(), from.getCover());
    }

    public Collection<IgdbGame> convertAll(Collection<GameJson> fElements){
        return fElements.stream()
                .map(element -> convert(element))
                .collect(Collectors.toList());
    }
}
