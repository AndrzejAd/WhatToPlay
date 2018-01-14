package whattoplay.services.persistance;

import whattoplay.domain.dto.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import whattoplay.domain.entities.IgdbGame;
import whattoplay.persistence.GamesDatabaseRepository;
import whattoplay.services.domain.GameDtoConverter;
import whattoplay.services.domain.GameDtoToGameEntityConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Andrzej
 */

@Service
public class GameDatabaseService {
    protected final GamesDatabaseRepository gamesDatabaseRepository;
    protected final GameDtoConverter gameToGameDtoConverter;
    protected final GameDtoToGameEntityConverter gameDtoToGameEntityConverter;

    @Autowired
    public GameDatabaseService(GamesDatabaseRepository gamesDatabaseRepository, GameDtoConverter gameToGameDtoConverter,
                               GameDtoToGameEntityConverter gameDtoToGameEntityConverter) {
        this.gamesDatabaseRepository = gamesDatabaseRepository;
        this.gameToGameDtoConverter = gameToGameDtoConverter;
        this.gameDtoToGameEntityConverter = gameDtoToGameEntityConverter;
    }

    public boolean persistGame(final GameDto game) {
        if ( gamesDatabaseRepository.getGameById(game.getGameId()) != null ){
            return false;
        } else{
            gamesDatabaseRepository.persistGame(gameDtoToGameEntityConverter.convert(game));
            return true;
        }
    }

    public void persistGame(IgdbGame igdbGame) {
        gamesDatabaseRepository.persistGame(igdbGame);
    }

    public void persistSetOfGames(Iterable<IgdbGame> games){
        games.forEach( gamesDatabaseRepository::persistGame);
    }

    public boolean deleteGameFromDatabase(String gameName){
        return Optional.ofNullable(gamesDatabaseRepository.deleteGameByGameName(gameName)).isPresent();
    }

    public GameDto getGameById(final long gameId) {
        return gameToGameDtoConverter.convert(gamesDatabaseRepository.getGameById(gameId));
    }

    public IgdbGame updateGame(final GameDto gameDto) {
        return gamesDatabaseRepository.updateGame(gameDtoToGameEntityConverter.convert(gameDto));
    }

    public List<GameDto> getRandomGames(final int gameId) {
        return new ArrayList<>(gameToGameDtoConverter.
                convertAll(gamesDatabaseRepository
                        .getRandomGames(gameId)));
    }

    public List<GameDto> getGamesByGenre(final String gameGenre) {
        return new ArrayList<>(gameToGameDtoConverter
                .convertAll(gamesDatabaseRepository
                        .getGamesByGenre(gameGenre)));
    }

    public List<GameDto> getGamesByGameName(final String gameName) {
        return (new ArrayList<>(gameToGameDtoConverter
                .convertAll(gamesDatabaseRepository
                        .getGamesByName(gameName))));
    }

}
