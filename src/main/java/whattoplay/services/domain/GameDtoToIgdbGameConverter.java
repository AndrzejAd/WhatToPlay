package whattoplay.services.domain;


import whattoplay.domain.dto.BaseGameDto;
import whattoplay.domain.entities.IgdbGame;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class GameDtoToIgdbGameConverter {
    public IgdbGame convert(BaseGameDto game){
        return new IgdbGame();
    }
    
    public Collection<IgdbGame> convertAll(Collection<BaseGameDto> fElements){
        Collection<IgdbGame> convertedElement =
                fElements.stream()
                        .map(this::convert)
                        .collect(Collectors.toList());
        return convertedElement;
    }
}
