package whattoplay.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whattoplay.domain.entities.GameJsonDto;
import whattoplay.domain.entities.GameMode;
import whattoplay.domain.entities.Genre;
import whattoplay.domain.entities.Pegi;
import whattoplay.persistence.GamesDatabaseRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Andrzej on 2017-11-21.
 */
@Service
public class InternetGameDatabaseService {
    private static final String token = "8dcd2a959fef891fbac266d5046e0414";
    private GamesDatabaseRepository gamesDatabaseRepository;

    @Autowired
    public InternetGameDatabaseService(GamesDatabaseRepository gamesDatabaseRepository) {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        this.gamesDatabaseRepository = gamesDatabaseRepository;
    }

    public void getAllGames() throws UnirestException {
        final String gamesFields = "id," +
                "name," +
                "slug," +
                "url," +
                "created_at," +
                "updated_at," +
                "summary," +
                "storyline," +
                "first_release_date," +
                "hypes," +
                "popularity," +
                "rating," +
                "rating_count," +
                "aggregated_rating," +
                "aggregated_rating_count," +
                "total_rating," +
                "total_rating_count," +
                "collection," +
                "franchise," +
                "time_to_beat," +
                "developers," +
                "status," +
                "esrb," +
                "pegi," +
                "websites," +
                "external," +
                "cover," +
                "screenshots";
        for ( int i = 0; i < 2; i++ ) {
            HttpResponse<GameJsonDto[]> jsonResponse = Unirest.get("https://api-2445582011268.apicast.io/games/")
                    .header("accept", "application/json")
                    .header("user-key", token)
                    .queryString("fields", gamesFields)
                    .queryString("limit", "50")
                    .asObject(GameJsonDto[].class);

            ArrayList<GameJsonDto> gameJsonDtos = new ArrayList<>(Arrays.asList(jsonResponse.getBody()));

            gameJsonDtos.forEach(System.out::println);
            System.out.println("-----------------");
        }

    }

    public void saveAllGenres() throws UnirestException{
        final String genresFields = "id," +
                "name," +
                "url," +
                "created_at," +
                "updated_at";
        HttpResponse<Genre[]> genresJson = Unirest.get("https://api-2445582011268.apicast.io/genres/")
                .header("accept", "application/json")
                .header("user-key", token)
                .queryString("fields", genresFields)
                .queryString("limit", "50")
                .asObject(Genre[].class);

        ArrayList<Genre> genres = new ArrayList<>(Arrays.asList(genresJson.getBody()));
        genres.forEach( x -> {
            System.out.println("Persisting " + x.getName() + ": " + x.getUrl());
            gamesDatabaseRepository.persistGenre(x);
        } );
    }

    public void saveAllGameModes() throws UnirestException{
        final String gameModesFields = "id," +
                "name," +
                "url," +
                "created_at," +
                "updated_at";
        HttpResponse<GameMode[]> genresJson = Unirest.get("https://api-2445582011268.apicast.io/game_modes/")
                .header("accept", "application/json")
                .header("user-key", token)
                .queryString("fields", gameModesFields)
                .queryString("limit", "50")
                .asObject(GameMode[].class);

        ArrayList<GameMode> genres = new ArrayList<>(Arrays.asList(genresJson.getBody()));
        genres.forEach( x -> {
            System.out.println("Persisting " + x.getName() + ": " + x.getUrl() + " " + x.getCreatedAt());
            gamesDatabaseRepository.persistGameMode(x);
        } );
    }

}
