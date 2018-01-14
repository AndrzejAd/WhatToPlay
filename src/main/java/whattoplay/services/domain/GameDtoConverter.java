package whattoplay.services.domain;

import whattoplay.domain.dto.GameDto;
import whattoplay.domain.entities.IgdbGame;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author Andrzej
 */

@FunctionalInterface
public interface GameDtoConverter {
    GameDto convert(IgdbGame from);
 
    default Collection<GameDto> convertAll(Collection<IgdbGame> fElements){
        return fElements.stream()
                        .map(this::convert)
                        .collect(Collectors.toList());
    }
    
    
}
