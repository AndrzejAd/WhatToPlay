package whattoplay.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;
import whattoplay.domain.entities.GameJsonDto;
import whattoplay.domain.entities.Pegi;

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

    public InternetGameDatabaseService() {
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
    }

    public void getAllGames() throws UnirestException {
        String params = "id," +
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
        HttpResponse<GameJsonDto[]> jsonResponse = Unirest.get("https://api-2445582011268.apicast.io/games/")
                .header("accept", "application/json")
                .header("user-key", token)
                .queryString("fields", params)
                .queryString("limit", "50")
                .asObject(GameJsonDto[].class);

        ArrayList<GameJsonDto> gameJsonDtos = new ArrayList<>(Arrays.asList(jsonResponse.getBody()));

        /*gameJsonDtos.stream()
                .map(GameJsonDto::getTimeToBeat)
                .forEach( x -> Optional.ofNullable(x).ifPresent(System.out::println));*/

        /*gameJsonDtos.stream()
                .map(GameJsonDto::getPlayerPerspectivesIds)
                .forEach( x ->
                {
                    System.out.println("-----------");
                    x.forEach( System.out::println );
                });*/

        /*gameJsonDtos.stream()
                .map(GameJsonDto::getStatus)
                .forEach( x -> {
                    Optional.ofNullable(x).ifPresent(y -> {
                        System.out.println("-----------");
                        System.out.println(y);
                    });
                });*/

        /*
        gameJsonDtos.stream()
                .map(GameJsonDto::getEsrb)
                .forEach( x -> {
                    Optional.ofNullable(x).ifPresent(y -> {
                        System.out.println("-----------");
                        System.out.println(y);
                    });
                });
        */

        /*gameJsonDtos.stream()
                .forEach( x ->{
                    System.out.println(x.getName() + " " + Optional.ofNullable(x.getPegi()).map( Pegi::getRating ) + " " + Optional.ofNullable(x.getPegi()).map( Pegi::getSynopsis ) );
                } );*/

        /*gameJsonDtos.stream()
                .forEach( x ->{
                    x.getWebsites().forEach( y ->{
                        System.out.println(y.getCategory() + " " + y.getUrl()) ;
                    } );
                } );*/

        gameJsonDtos.stream()
                .forEach( x ->{
                    x.getScreenshots().forEach( y ->{
                        System.out.println(y.getUrl() + " "  + y.getCloudinaryId());
                    } );
                } );

    }




}
