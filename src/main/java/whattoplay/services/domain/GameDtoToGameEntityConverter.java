package whattoplay.services.domain;


import whattoplay.domain.dto.GameDto;
import whattoplay.domain.entities.IgdbGame;
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
    public IgdbGame convert(GameDto game){
        return new IgdbGame();
    }
    
    public Collection<IgdbGame> convertAll(Collection<GameDto> fElements){
        Collection<IgdbGame> convertedElement =
                fElements.stream()
                        .map(element -> convert(element))
                        .collect(Collectors.toList());
        return convertedElement;
    }
}
