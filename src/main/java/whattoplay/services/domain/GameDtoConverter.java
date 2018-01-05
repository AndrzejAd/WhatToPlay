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
    public GameDto convert(IgdbGame from);
 
    default public Collection<GameDto> convertAll(Collection<IgdbGame> fElements){
        Collection<GameDto> convertedElement =
                fElements.stream()
                        .map(element -> convert(element))
                        .collect(Collectors.toList());
        return convertedElement;
    }
    
    
}
