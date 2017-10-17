package whattoplay.services.domain;

import whattoplay.domain.dto.GameDto;
import whattoplay.domain.entities.GameEntity;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author Andrzej
 */

@FunctionalInterface
public interface GameDtoConverter {
    public GameDto convert(GameEntity from);
 
    default public Collection<GameDto> convertAll(Collection<GameEntity> fElements){
        Collection<GameDto> convertedElement =
                fElements.stream()
                        .map(element -> convert(element))
                        .collect(Collectors.toList());
        return convertedElement;
    }
    
    
}
