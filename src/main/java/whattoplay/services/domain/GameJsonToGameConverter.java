package whattoplay.services.domain;

import whattoplay.domain.entities.Game;
import whattoplay.domain.entities.GameJson;

import java.util.Collection;
import java.util.stream.Collectors;

public class GameJsonToGameConverter {
    public Game convert(GameJson from){
        return new Game(from.getId(), from.getName(), from.getSlug(), from.getUrl(), from.getSummary(), from.getStoryline(),
                from.getHypes(), from.getPopularity(), from.getRating(), from.getRatingCount(), from.getAggregatedRating(),
                from.getAggregatedRatingCount(), from.getTotalRating(), from.getTotalRatingCount(), from.getCollectionId(),
                from.getFranchiseId(), from.getCreatedAt(), from.getUpdatedAt(), from.getFirstReleaseDate() );
    }

    public Collection<Game> convertAll(Collection<GameJson> fElements){
        return fElements.stream()
                .map(element -> convert(element))
                .collect(Collectors.toList());
    }
}
