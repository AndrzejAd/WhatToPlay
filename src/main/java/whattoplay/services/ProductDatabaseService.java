package whattoplay.services;

import whattoplay.domain.dto.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import whattoplay.persistence.GamesDatabaseRepository;
import whattoplay.services.domain.GameDtoConverter;
import whattoplay.services.domain.GameDtoToGameEntityConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Andrzej
 */

@Service
public class ProductDatabaseService {
    private GamesDatabaseRepository databaseRepository;
    private GameDtoConverter gameToGameDtoConverter;
    private GameDtoToGameEntityConverter gameDtoToGameEntityConverter;
    
    @Autowired
    public ProductDatabaseService(GamesDatabaseRepository databaseRepository, GameDtoConverter gameToGameDtoConverter, 
                                GameDtoToGameEntityConverter gameDtoToGameEntityConverter) {
        this.databaseRepository = databaseRepository;
        this.gameToGameDtoConverter = gameToGameDtoConverter;
        this.gameDtoToGameEntityConverter = gameDtoToGameEntityConverter;
    }

    public void saveGameToDatabase(GameDto game){
        databaseRepository.insertGame(gameDtoToGameEntityConverter.convert(game));
    }
    
    public GameDto getGameById(long gameId){
        try {
            return gameToGameDtoConverter.convert( databaseRepository.getGameById( gameId ) );
        } catch( EmptyResultDataAccessException exc ){
            throw exc;
        }
    }
    
    public List<GameDto> getRandomGames(int gameId){
        return gameToGameDtoConverter.
                        convertAll( databaseRepository
                                    .getRandomGames(gameId) )
                                    .stream()
                                    .collect(Collectors.toList());
    }
    
    public List<GameDto> getGamesByGenre(String gameGenre){
        return gameToGameDtoConverter.
                        convertAll( databaseRepository
                                    .getGamesByGenre(gameGenre) )
                                    .stream()
                                    .collect(Collectors.toList());
    }
    
    
}
