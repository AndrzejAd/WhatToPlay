package whattoplay.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whattoplay.domain.entities.*;
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
    private static Logger logger = LogManager.getLogger();
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
            logger.info("Persisting genre:" + x.getId() + " " + x.getName() + ": " + x.getUrl() + " " + x.getCreatedAt());
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
            logger.info("Persisting Game Mode" + x.getId() + " " + x.getName() + ": " + x.getUrl() + " " + x.getCreatedAt());
            //gamesDatabaseRepository.persistGameMode(x);
        } );
    }

    public void saveAllPlayerPerspectives() throws UnirestException{
        final String playerPerspectivesFields = "id," +
                "name," +
                "url," +
                "created_at," +
                "updated_at";
        HttpResponse<PlayerPerspective[]> genresJson = Unirest.get("https://api-2445582011268.apicast.io/player_perspectives/")
                .header("accept", "application/json")
                .header("user-key", token)
                .queryString("fields", playerPerspectivesFields)
                .queryString("limit", "50")
                .asObject(PlayerPerspective[].class);

        ArrayList<PlayerPerspective> genres = new ArrayList<>(Arrays.asList(genresJson.getBody()));
        genres.forEach( x -> {
            logger.info("Persisting Player Perspective: " + x.getId() + " " + x.getName() + ": " + x.getUrl() + " " + x.getCreatedAt());
            gamesDatabaseRepository.persistPlayerPerspective(x);
        } );
    }

    public boolean saveDevelopers()  {
        logger.info(" ===================== Persisting developers starts. ===================== ");
        final String urlForScroll = "https://api-2445582011268.apicast.io/companies/";
        final String scrollUrlForDevelopers;
        final long requiredRequestsNumb;
        final HttpResponse<Developer[]> jsonResponse;
        try {
            jsonResponse = getScrollForDevelopers(urlForScroll);
            scrollUrlForDevelopers = jsonResponse.getHeaders().get("X-Next-Page").get(0);
            requiredRequestsNumb = Math.round(Integer.parseInt(jsonResponse.getHeaders().get("X-Count").get(0)) / 50);
            logger.info(new StringBuilder().append(" Scroll url for requests: ").append(scrollUrlForDevelopers).toString());
            logger.info(new StringBuilder().append(" Persisting ").append(requiredRequestsNumb).append(" developers. ").toString());
            for ( int i = 0; i <= requiredRequestsNumb + 1; i++ ){
                saveSetOfDevelopers(scrollUrlForDevelopers);
            }
            return true;
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append(" Couldnt get the scroll for Developers ").append(e.getMessage()).toString() );
        }
        return false;
    }

    protected void saveSetOfDevelopers(String scrollUrlForDevelopers){
        ArrayList<Developer> developers;
        try {
            developers = new ArrayList<>(Arrays.asList(getDevelopers( scrollUrlForDevelopers).getBody()));
            developers.forEach(x -> {
                if ( x.getName().length() >= 100) x.setName(x.getName().substring(0, 98));
                Optional.ofNullable(x.getUrl()).ifPresent( y ->{
                    if ( y.length() >= 100 ) x.setUrl(x.getUrl().substring(0, 98));
                });
                Optional.ofNullable(x.getDeveloperImageCloudinaryId()).ifPresent( y ->{
                    if ( y.length() >= 100 ) x.setDeveloperImageCloudinaryId((x.getDeveloperImageCloudinaryId().substring(0, 98)));
                });
                logger.info(new StringBuilder().append("Persisting Developer: ").append(x.getId()).append(" ").append(x.getName()).append(": ").append(x.getUrl()).append(" ").append(x.getStartDate()).toString());
                gamesDatabaseRepository.persistDeveloper(x);
            });
        } catch (UnirestException e) {
            logger.info(new StringBuilder().append(" Got to the end of the scroll! ").toString());
        }
    }

    protected HttpResponse<Developer[]>  getScrollForDevelopers(String url) throws UnirestException {
        final String developersFields = "id," +
                "logo," +
                "name," +
                "url," +
                "description," +
                "website," +
                "start_date";
        HttpResponse<Developer[]> jsonResponse = Unirest.get(url)
                .header("accept", "application/json")
                .header("user-key", token)
                .queryString("fields", developersFields)
                .queryString("limit", "50")
                .queryString("scroll", 1)
                .asObject(Developer[].class);
        return jsonResponse;
    }

    protected HttpResponse<Developer[]>  getDevelopers(String url) throws UnirestException {
        HttpResponse<Developer[]> jsonResponse = Unirest.get("https://api-2445582011268.apicast.io/" + url)
                .header("accept", "application/json")
                .header("user-key", token)
                .asObject(Developer[].class);
        return jsonResponse;
    }

}
