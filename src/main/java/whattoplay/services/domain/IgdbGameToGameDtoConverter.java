package whattoplay.services.domain;

import whattoplay.domain.dto.BaseGameDto;
import whattoplay.domain.dto.GameJson;
import whattoplay.domain.entities.IgdbGame;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class IgdbGameToGameDtoConverter {

    public BaseGameDto convert(IgdbGame igdbGame) {
        return new BaseGameDto(igdbGame.getId(), igdbGame.getName(), igdbGame.getSlug(), igdbGame.getUrl(),
                igdbGame.getSummary(), igdbGame.getStoryline(), igdbGame.getHypes(), igdbGame.getPopularity(),
                igdbGame.getRating(), igdbGame.getRatingCount(), igdbGame.getAggregatedRating(), igdbGame.getAggregatedRatingCount(),
                igdbGame.getTotalRating(), igdbGame.getTotalRatingCount(), igdbGame.getCollectionId(), igdbGame.getFranchiseId(),
                igdbGame.getCreatedAt(), igdbGame.getUpdatedAt(), igdbGame.getFirstReleaseDate(),igdbGame.getStatus(),
                igdbGame.getTimeToBeat(), igdbGame.getEsrb(), igdbGame.getPegi(), igdbGame.getExternal(), igdbGame.getCover());
    }

    public Collection<BaseGameDto> convertAll(Collection<IgdbGame> fElements){
        return fElements.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
