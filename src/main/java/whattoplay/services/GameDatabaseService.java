package whattoplay.services;

import whattoplay.domain.dto.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import whattoplay.domain.entities.GameEntity;
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
    private GamesDatabaseRepository databaseRepository;
    private GameDtoConverter gameToGameDtoConverter;
    private GameDtoToGameEntityConverter gameDtoToGameEntityConverter;

    @Autowired
    public GameDatabaseService(GamesDatabaseRepository databaseRepository, GameDtoConverter gameToGameDtoConverter,
                               GameDtoToGameEntityConverter gameDtoToGameEntityConverter) {
        this.databaseRepository = databaseRepository;
        this.gameToGameDtoConverter = gameToGameDtoConverter;
        this.gameDtoToGameEntityConverter = gameDtoToGameEntityConverter;
    }

    public boolean saveGameToDatabase(final GameDto game) {
        if ( databaseRepository.getGameById(game.getGameId()) != null ){
            return false;
        } else{
            databaseRepository.persistGame(gameDtoToGameEntityConverter.convert(game));
            return true;
        }
    }

    public boolean deleteGameFromDatabase(String gameName){
        return Optional.ofNullable(databaseRepository.deleteGameByGameName(gameName)).map( x -> true ).orElse(false);
    }

    public GameDto getGameById(final long gameId) {
        try {
            return gameToGameDtoConverter.convert(databaseRepository.getGameById(gameId));
        } catch (EmptyResultDataAccessException exc) {
            throw exc;
        }
    }

    public GameEntity updateGame(final GameDto gameDto) {
        return databaseRepository.updateGame(gameDtoToGameEntityConverter.convert(gameDto));
    }

    public List<GameDto> getRandomGames(final int gameId) {
        return gameToGameDtoConverter.
                convertAll(databaseRepository
                        .getRandomGames(gameId))
                .stream()
                .collect(Collectors.toList());
    }

    public List<GameDto> getGamesByGenre(final String gameGenre) {
        return new ArrayList<>(gameToGameDtoConverter
                .convertAll(databaseRepository
                        .getGamesByGenre(gameGenre)));
    }

    public List<GameDto> getGamesByGameName(final String gameName) {
        return (new ArrayList<>(gameToGameDtoConverter
                .convertAll(databaseRepository
                        .getGamesByName(gameName))));
    }

}
