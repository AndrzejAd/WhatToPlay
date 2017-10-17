package whattoplay.services.domain;


import whattoplay.domain.dto.GameDto;
import whattoplay.domain.entities.GameEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author Andrzej
 */
@Service
public class GameDtoToGameEntityConverter {
    public GameEntity convert(GameDto game){
        return new GameEntity(game.getGameName(), game.getProducer(),
                              game.getPublisher(), game.getDatePublished(), 
                              game.getPrice(), game.getGenre(), game.getImagePath());
    }
    
    public Collection<GameEntity> convertAll(Collection<GameDto> fElements){
        Collection<GameEntity> convertedElement =
                fElements.stream()
                        .map(element -> convert(element))
                        .collect(Collectors.toList());
        return convertedElement;
    }
}
