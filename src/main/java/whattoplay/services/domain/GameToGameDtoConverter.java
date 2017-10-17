package whattoplay.services.domain;

import whattoplay.domain.dto.BaseGameDto;
import whattoplay.domain.entities.GameEntity;
import org.springframework.stereotype.Service;

@Service
public class GameToGameDtoConverter implements GameDtoConverter {

    @Override
    public BaseGameDto convert(GameEntity game) {
        return new BaseGameDto( game.getGameId(), game.getGameName(), game.getProducer(),
                            game.getPublisher(), game.getDatePublished(), game.getPrice(), 
                            game.getGenre(), game.getImagePath() );
    }
    
}
