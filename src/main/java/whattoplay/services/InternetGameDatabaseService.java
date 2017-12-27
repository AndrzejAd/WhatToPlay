package whattoplay.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whattoplay.domain.entities.*;
import whattoplay.persistence.GameFieldsDatabaseRepository;
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
    private GameFieldsDatabaseRepository gameFieldsDatabaseRepository;

    @Autowired
    public InternetGameDatabaseService(GamesDatabaseRepository gamesDatabaseRepository, GameFieldsDatabaseRepository gameFieldsDatabaseRepository) {
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
        this.gameFieldsDatabaseRepository = gameFieldsDatabaseRepository;
    }

    protected HttpRequest getScrollFromIGDB(String url, String fields) {
        return Unirest.get(url)
                .header("accept", "application/json")
                .header("user-key", token)
                .queryString("fields", fields)
                .queryString("limit", "50")
                .queryString("scroll", 1);
    }

    protected HttpRequest getSetOfObjectsFromIGDB(String url) {
        return Unirest.get("https://api-2445582011268.apicast.io/" + url)
                .header("accept", "application/json")
                .header("user-key", token);
    }

    public void saveAllGenres() throws UnirestException {
        HttpResponse<Genre[]> genresJson = Unirest.get("https://api-2445582011268.apicast.io/genres/")
                .header("accept", "application/json")
                .header("user-key", token)
                .queryString("fields", getBasicFields())
                .queryString("limit", "50")
                .asObject(Genre[].class);
        Arrays.asList(genresJson.getBody()).forEach(x -> {
            logger.info("Persisting genre:" + x.getId() + " " + x.getName() + ": " + x.getUrl() + " " + x.getCreatedAt());
            gameFieldsDatabaseRepository.persistGenre(x);
        });
    }

    public void saveAllGameModes() throws UnirestException {
        HttpResponse<GameMode[]> genresJson = Unirest.get("https://api-2445582011268.apicast.io/game_modes/")
                .header("accept", "application/json")
                .header("user-key", token)
                .queryString("fields", getBasicFields())
                .queryString("limit", "50")
                .asObject(GameMode[].class);
        Arrays.asList(genresJson.getBody()).forEach(x -> {
            logger.info("Persisting Game Mode" + x.getId() + " " + x.getName() + ": " + x.getUrl() + " " + x.getCreatedAt());
        });
    }

    public void saveAllPlayerPerspectives() throws UnirestException {
        HttpResponse<PlayerPerspective[]> genresJson = Unirest.get("https://api-2445582011268.apicast.io/player_perspectives/")
                .header("accept", "application/json")
                .header("user-key", token)
                .queryString("fields", getBasicFields())
                .queryString("limit", "50")
                .asObject(PlayerPerspective[].class);
        Arrays.asList(genresJson.getBody()).forEach(x -> {
            logger.info("Persisting Player Perspective: " + x.getId() + " " + x.getName() + ": " + x.getUrl() + " " + x.getCreatedAt());
            gameFieldsDatabaseRepository.persistPlayerPerspective(x);
        });
    }

    public void saveAllFranchises() {
        logger.info(" ===================== Persisting Franchises starts. ===================== ");
        final String urlForScroll = "https://api-2445582011268.apicast.io/franchises/";
        final String scrollUrlFranchises;
        final long requiredRequestsNumb;
        final HttpResponse<Franchise[]> jsonResponse;
        try {
            jsonResponse = getScrollFromIGDB(urlForScroll, getBasicFields()).asObject(Franchise[].class);
            scrollUrlFranchises = jsonResponse.getHeaders().get("X-Next-Page").get(0);
            requiredRequestsNumb = Math.round(Integer.parseInt(jsonResponse.getHeaders().get("X-Count").get(0)) / 50);
            saveSetOfFranchises(Arrays.asList(jsonResponse.getBody()));
            logger.info(new StringBuilder().append(" Scroll url for requests: ").append(scrollUrlFranchises).toString());
            logger.info(new StringBuilder().append(" Persisting ").append(jsonResponse.getHeaders().get("X-Count").get(0)).append(" franchsises. ").toString());
            logger.info(new StringBuilder().append(" Doing ").append(requiredRequestsNumb + 1).append(" iterations. ").toString());
            for ( int i = 0; i <= requiredRequestsNumb + 1; i++ ){
                saveSetOfFranchises(scrollUrlFranchises);
            }
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append(" Couldnt get the scroll for Franchises ").append(e.getMessage()).toString() );
        }
    }

    public boolean saveAllDevelopers() {
        logger.info(" ===================== Persisting developers starts. ===================== ");
        final String urlForScroll = "https://api-2445582011268.apicast.io/companies/";
        final String scrollUrlForDevelopers;
        final long requiredRequestsNumb;
        final HttpResponse<Developer[]> jsonResponse;
        try {
            jsonResponse = getScrollFromIGDB(urlForScroll, getDeveloperFields()).asObject(Developer[].class);
            scrollUrlForDevelopers = jsonResponse.getHeaders().get("X-Next-Page").get(0);
            requiredRequestsNumb = Math.round(Integer.parseInt(jsonResponse.getHeaders().get("X-Count").get(0)) / 50);
            logger.info(new StringBuilder().append(" Scroll url for requests: ").append(scrollUrlForDevelopers).toString());
            logger.info(new StringBuilder().append(" Persisting ").append(jsonResponse.getHeaders().get("X-Count").get(0)).append(" developers. ").toString());
            logger.info(new StringBuilder().append(" Doing ").append(requiredRequestsNumb + 1).append(" iterations. ").toString());
            saveSetOfDevelopers(Arrays.asList(jsonResponse.getBody()));
            for (int i = 0; i <= requiredRequestsNumb + 1; i++) {
                saveSetOfDevelopers(scrollUrlForDevelopers);
            }
            return true;
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append(" Couldnt get the scroll for Developers ").append(e.getMessage()).toString());
        }
        return false;
    }

    public void saveAllGames() throws UnirestException {
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
        HttpResponse<GameJson[]> jsonResponse = Unirest.get("https://api-2445582011268.apicast.io/games/")
                .header("accept", "application/json")
                .header("user-key", token)
                .queryString("fields", gamesFields)
                .queryString("limit", "1")
                .asObject(GameJson[].class);

        ArrayList<GameJson> gameJsons = new ArrayList<>(Arrays.asList(jsonResponse.getBody()));

        gameJsons.forEach(System.out::println);
        System.out.println("-----------------");
    }

    protected void saveSetOfDevelopers(Iterable<Developer> developers ){
        developers.forEach( x -> {
            if (x.getName().length() >= 100) x.setName(x.getName().substring(0, 98));
            Optional.ofNullable(x.getUrl()).ifPresent(y -> {
                if (y.length() >= 100) x.setUrl(x.getUrl().substring(0, 98));
            });
            Optional.ofNullable(x.getDeveloperImageCloudinaryId()).ifPresent(y -> {
                if (y.length() >= 100)
                    x.setDeveloperImageCloudinaryId((x.getDeveloperImageCloudinaryId().substring(0, 98)));
            });
            logger.info(new StringBuilder().append("Persisting Developer: ").append(x.getId()).append(" ").append(x.getName()).append(": ").append(x.getUrl()).append(" ").append(x.getStartDate()).toString());
            gameFieldsDatabaseRepository.persistDeveloper(x);
        });
    }

    protected void saveSetOfDevelopers(String scrollUrlForDevelopers) {
        try {
            saveSetOfDevelopers( Arrays.asList(getSetOfObjectsFromIGDB(scrollUrlForDevelopers).asObject(Developer[].class).getBody()) );
        } catch (UnirestException e) {
            logger.info(new StringBuilder().append(" Got to the end of the scroll! ").toString());
        }
    }

    protected void saveSetOfFranchises(Iterable<Franchise> franchises) {
        franchises.forEach(x -> {
            if (x.getName().length() >= 150) x.setName(x.getName().substring(0, 148));
            Optional.ofNullable(x.getUrl()).ifPresent(y -> {
                if (y.length() >= 100) x.setUrl(x.getUrl().substring(0, 98));
            });
            logger.info(new StringBuilder().append("Persisting Franchise: ").append(x.getId()).append(" ").append(x.getName()).append(": ").append(x.getUrl()).append(" ").append(x.getCreatedAt()).toString());
            gameFieldsDatabaseRepository.persistFranchise(x);
        });
    }

    protected void saveSetOfFranchises(String scrollUrlForFranchises) {
        try {
            saveSetOfFranchises(  Arrays.asList(getSetOfObjectsFromIGDB(scrollUrlForFranchises).asObject(Franchise[].class).getBody()));
        } catch (UnirestException e) {
            logger.info(new StringBuilder().append(" Got to the end of the franchises scroll! ").toString());
        }
    }

    protected String getDeveloperFields() {
        return "id," +
                "logo," +
                "name," +
                "url," +
                "description," +
                "website," +
                "start_date";
    }

    protected String getBasicFields() {
        return "id," +
                "name," +
                "url," +
                "created_at," +
                "updated_at";
    }

    public static String getToken() {
        return token;
    }
}
