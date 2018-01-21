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
import whattoplay.domain.dto.GameJson;
import whattoplay.domain.entities.*;
import whattoplay.persistence.GameFieldsDatabaseRepository;
import whattoplay.services.persistance.GameFieldsDaoService;

import java.io.IOException;
import java.util.Arrays;

/**
 * Through REST requests this class caches Internet IgdbGame Database on local rational database.
 *
 * @see <a href="https://igdb.github.io/api">Internet IgdbGame Database</a>
 */

@Service
public class IgdbRequesterService {
    private static final String token = "8dcd2a959fef891fbac266d5046e0414";
    private static Logger logger = LogManager.getLogger();
    private final GameFieldsDatabaseRepository gameFieldsDatabaseRepository;
    private final GameJsonToNormalFormCacher gameJsonToNormalFormCacher;
    private final GameFieldsDaoService gameFieldsDaoService;



    @Autowired
    public IgdbRequesterService(GameFieldsDatabaseRepository gameFieldsDatabaseRepository,
                                GameJsonToNormalFormCacher gameJsonToNormalFormCacher,
                                GameFieldsDaoService gameFieldsDatabaseService) {
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
        this.gameFieldsDatabaseRepository = gameFieldsDatabaseRepository;
        this.gameJsonToNormalFormCacher = gameJsonToNormalFormCacher;
        this.gameFieldsDaoService = gameFieldsDatabaseService;
    }

    /**
     * This method returns scroll url to allow pagination of IGDB responses.
     * Without it, its not possible to cache all reponses.
     *
     * @param url    and url to the endpoint
     * @param fields fields of the needed object
     * @return HttpRequest containing set of non-casted objects
     * @see <a href="https://igdb.github.io/api/references/pagination/">IGDB Pagination Page</a>
     */
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

    /**
     * This method sends request to cache all of the available data.
     *
     * @return number of persisted genres. -1 if caching was unsuccessful.
     */
    public long saveAllGenres()  {
        HttpResponse<Genre[]> genresJson;
        try {
            genresJson = Unirest.get("https://api-2445582011268.apicast.io/genres/")
                    .header("accept", "application/json")
                    .header("user-key", token)
                    .queryString("fields", getBasicFields())
                    .queryString("limit", "50")
                    .asObject(Genre[].class);
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append("Couldn't parse Genres. \n").append(e.getMessage()).toString());
            return -1;
        }
        Arrays.asList(genresJson.getBody()).forEach(x -> {
            logger.info("Persisting genre:" + x.getId() + " " + x.getName() + ": " + x.getUrl() + " " + x.getCreatedAt());
            gameFieldsDatabaseRepository.persistGenre(x);
        });
        return genresJson.getBody().length;
    }

    public long saveAllGameModes() {
        HttpResponse<GameMode[]> genresJson;
        try {
            genresJson = Unirest.get("https://api-2445582011268.apicast.io/game_modes/")
                    .header("accept", "application/json")
                    .header("user-key", token)
                    .queryString("fields", getBasicFields())
                    .queryString("limit", "50")
                    .asObject(GameMode[].class);
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append("Couldn't parse Game Modes. \n").append(e.getMessage()).toString());
            return -1;
        }
        Arrays.asList(genresJson.getBody()).forEach(x ->{
            logger.info("Persisting IgdbGame Mode" + x.getId() + " " + x.getName() + ": " + x.getUrl() + " " + x.getCreatedAt());
            gameFieldsDatabaseRepository.persistGameMode(x);
        });
        return genresJson.getBody().length;
    }

    public long saveAllPlayerPerspectives()  {
        HttpResponse<PlayerPerspective[]> genresJson;
        try {
            genresJson = Unirest.get("https://api-2445582011268.apicast.io/player_perspectives/")
                    .header("accept", "application/json")
                    .header("user-key", token)
                    .queryString("fields", getBasicFields())
                    .queryString("limit", "50")
                    .asObject(PlayerPerspective[].class);
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append("Couldn't parse Player Perspectives.\n ").append(e.getMessage()).toString());
            return -1;
        }
        Arrays.asList(genresJson.getBody()).forEach(x -> {
            logger.info("Persisting Player Perspective: " + x.getId() + " " + x.getName() + ": " + x.getUrl() + " " + x.getCreatedAt());
            gameFieldsDatabaseRepository.persistPlayerPerspective(x);
        });
        return genresJson.getBody().length;
    }

    public long saveAllFranchises() {
        logger.info(" ===================== Persisting Franchises starts. ===================== ");
        final String urlForScroll = "https://api-2445582011268.apicast.io/franchises/";
        final String scrollUrlFranchises;
        final long requiredRequestsNumb;
        final HttpResponse<Franchise[]> jsonResponse;
        try {
            jsonResponse = getScrollFromIGDB(urlForScroll, getBasicFields()).asObject(Franchise[].class);
            scrollUrlFranchises = jsonResponse.getHeaders().get("X-Next-Page").get(0);
            requiredRequestsNumb = Math.round(Integer.parseInt(jsonResponse.getHeaders().get("X-Count").get(0)) / 50);
            gameFieldsDaoService.saveSetOfFranchises(Arrays.asList(jsonResponse.getBody()));
            logger.info(new StringBuilder().append(" Scroll url for requests: ").append(scrollUrlFranchises).toString());
            logger.info(new StringBuilder().append(" Persisting ").append(jsonResponse.getHeaders().get("X-Count").get(0)).append(" franchsises. ").toString());
            logger.info(new StringBuilder().append(" Doing ").append(requiredRequestsNumb + 1).append(" iterations. ").toString());
            for (int i = 0; i <= requiredRequestsNumb + 1; i++) {
                gameFieldsDaoService.saveSetOfFranchises(Arrays.asList(getSetOfObjectsFromIGDB(scrollUrlFranchises).asObject(Franchise[].class).getBody()));
            }
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append(" Couldnt get the scroll for Franchises ").append(e.getMessage()).toString());
        }
        return 0;
    }

    public long saveAllDevelopers() {
        logger.info(" ===================== Persisting developers starts. ===================== ");
        final String urlForScroll = "https://api-2445582011268.apicast.io/companies/";
        final String scrollUrlForDevelopers;
        final long requiredRequestsNumb;
        final HttpResponse<Developer[]> jsonResponse;
        try {
            jsonResponse = getScrollFromIGDB(urlForScroll, getDeveloperFields()).asObject(Developer[].class);
            scrollUrlForDevelopers = jsonResponse.getHeaders().get("X-Next-Page").get(0);
            requiredRequestsNumb = Math.round(4696 / 50);
            logger.info(new StringBuilder().append(" Scroll url for requests: ").append(scrollUrlForDevelopers).toString());
            logger.info(new StringBuilder().append(" Persisting ").append(jsonResponse.getHeaders().get("X-Count").get(0)).append(" developers. ").toString());
            logger.info(new StringBuilder().append(" Doing ").append(requiredRequestsNumb + 1).append(" iterations. ").toString());
            gameFieldsDaoService.saveSetOfDevelopers(Arrays.asList(jsonResponse.getBody()));
            for (int i = 0; i <= requiredRequestsNumb + 1; i++) {
                gameFieldsDaoService.saveSetOfDevelopers(Arrays.asList(getSetOfObjectsFromIGDB(scrollUrlForDevelopers).asObject(Developer[].class).getBody()));
            }
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append(" Couldnt get the scroll for Developers ").append(e.getMessage()).toString());
        }
        return 0;
    }

    public long saveAllCollections() {
        logger.info(" ===================== Persisting collections starts. ===================== ");
        final String urlForScroll = "https://api-2445582011268.apicast.io/collections/";
        final String scrollUrlForCollections;
        final long requiredRequestsNumb;
        final HttpResponse<Collection[]> jsonResponse;
        try {
            jsonResponse = getScrollFromIGDB(urlForScroll, getBasicFields()).asObject(Collection[].class);
            scrollUrlForCollections = jsonResponse.getHeaders().get("X-Next-Page").get(0);
            requiredRequestsNumb = Math.round(Integer.parseInt(jsonResponse.getHeaders().get("X-Count").get(0)) / 50);
            logger.info(new StringBuilder().append(" Scroll url for requests: ").append(scrollUrlForCollections).toString());
            logger.info(new StringBuilder().append(" Persisting ").append(jsonResponse.getHeaders().get("X-Count").get(0)).append(" collections. ").toString());
            logger.info(new StringBuilder().append(" Doing ").append(requiredRequestsNumb + 1).append(" iterations. ").toString());
            gameFieldsDaoService
                    .saveSetOfCollections(
                            Arrays.asList(
                                    jsonResponse.getBody()));
            for (int i = 0; i <= requiredRequestsNumb + 1; i++) {
                gameFieldsDaoService.saveSetOfCollections(Arrays.asList(getSetOfObjectsFromIGDB(scrollUrlForCollections).asObject(Collection[].class).getBody()));
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            logger.error(new StringBuilder().append(" Couldnt get the scroll for collections \n").append(e.getMessage()).toString());
        }
        return 0;

    }

    public long saveAllGames(){
        logger.info(" ===================== Persisting games starts. ===================== ");
        final String urlForScroll = "https://api-2445582011268.apicast.io/games/";
        final String scrollUrlForGames;
        final long requiredRequestsNumb;
        final HttpResponse<GameJson[]> jsonResponse;
        try {
            jsonResponse = getScrollFromIGDB(urlForScroll, getGameFields()).asObject(GameJson[].class);
            scrollUrlForGames = jsonResponse.getHeaders().get("X-Next-Page").get(0);
            requiredRequestsNumb = Math.round(Integer.parseInt(jsonResponse.getHeaders().get("X-Count").get(0)) / 50);
            logger.info(new StringBuilder().append(" Scroll url for requests: ").append(scrollUrlForGames).toString());
            logger.info(new StringBuilder().append(" Persisting ").append(jsonResponse.getHeaders().get("X-Count").get(0)).append(" collections. ").toString());
            logger.info(new StringBuilder().append(" Doing ").append(requiredRequestsNumb + 1).append(" iterations. ").toString());
            gameJsonToNormalFormCacher.persistNormalFormOfSetOFGameJsons(Arrays.asList(jsonResponse.getBody()));
            for (int i = 0; i <= requiredRequestsNumb + 1; i++) {
                try {
                    gameJsonToNormalFormCacher.persistNormalFormOfSetOFGameJsons(Arrays.asList(getSetOfObjectsFromIGDB(scrollUrlForGames).asObject(GameJson[].class).getBody()));
                } catch (UnirestException e) {
                    logger.info(new StringBuilder().append(" Got to the end of the games scroll! ").toString());
                }
            }
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append(" Couldnt get the scroll for games ").append(e.getMessage()).toString());
        }
        return 0;
    }

    private String getDeveloperFields() {
        return "id," +
                "logo," +
                "name," +
                "url," +
                "description," +
                "website," +
                "start_date";
    }

    private String getBasicFields() {
        return "id," +
                "name," +
                "url," +
                "created_at," +
                "updated_at";
    }

    private String getGameFields() {
        return "id," +
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
                "game_modes," +
                "genres," +
                "player_perspectives," +
                "websites," +
                "status," +
                "esrb," +
                "pegi," +
                "websites," +
                "external," +
                "cover," +
                "screenshots";
    }

    public static String getToken() {
        return token;
    }
}
