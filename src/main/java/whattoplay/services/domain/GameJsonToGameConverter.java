package whattoplay.services.domain;

import org.springframework.stereotype.Service;
import whattoplay.domain.entities.IgdbGame;
import whattoplay.domain.dto.GameJson;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class GameJsonToGameConverter {

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
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
