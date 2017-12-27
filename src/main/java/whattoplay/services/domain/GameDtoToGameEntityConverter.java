package whattoplay.services.domain;


import whattoplay.domain.dto.GameDto;
import whattoplay.domain.entities.Game;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author Andrzej
 */
@Service
/** TODO **/
public class GameDtoToGameEntityConverter {
    public Game convert(GameDto game){
        return new Game();
    }
    
    public Collection<Game> convertAll(Collection<GameDto> fElements){
        Collection<Game> convertedElement =
                fElements.stream()
                        .map(element -> convert(element))
                        .collect(Collectors.toList());
        return convertedElement;
    }
}
