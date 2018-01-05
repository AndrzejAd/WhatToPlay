package whattoplay.services.domain;

import whattoplay.domain.dto.BaseGameDto;
import whattoplay.domain.entities.IgdbGame;
import org.springframework.stereotype.Service;

@Service
/** TODO **/
public class GameToGameDtoConverter implements GameDtoConverter {

    @Override
    public BaseGameDto convert(IgdbGame igdbGame) {
        return new BaseGameDto();
    }
    
}
