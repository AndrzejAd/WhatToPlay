package whattoplay.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import whattoplay.domain.entities.Developer;
import whattoplay.persistence.GameFieldsDatabaseRepository;
import whattoplay.persistence.GamesDatabaseRepository;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Andrzej on 2017-12-19.
 */


class InternetGameDatabaseCacherTest {
    static InternetGameDatabaseCacher internetGameDatabaseCacher;
    @Mock
    static GamesDatabaseRepository databaseMock;

    @Mock
    static GameFieldsDatabaseRepository gameFieldsDatabaseRepository;

    @Mock
    static GameJsonToNormalFormCacher gameJsonToNormalFormCacher;

    @BeforeAll
    static void setInternetGameDatabaseService(){
        //internetGameDatabaseCacher = new InternetGameDatabaseCacher(databaseMock, gameFieldsDatabaseRepository, gameJsonToNormalFormCacher);
    }

    @Test
    void responseStatusOfGenericScrollShouldBe200(){
        String developersFields = "id," +
                "logo," +
                "name," +
                "url," +
                "description," +
                "website," +
                "start_date";
        try {
            assertEquals( internetGameDatabaseCacher.getScrollFromIGDB( "https://api-2445582011268.apicast.io/companies/", developersFields  ).asObject(Developer[].class).getStatus(), 200);
        } catch (UnirestException e) {
            assertTrue(false);
        }
    }

    @Test
    void shouldBeAbleToCastDevelopers(){
        ArrayList<Developer> developers;
        try {
            developers = new ArrayList<>( Arrays.asList( (internetGameDatabaseCacher
                    .getScrollFromIGDB( "https://api-2445582011268.apicast.io/companies/", "id," +
                            "logo," +
                            "name," +
                            "url," +
                            "description," +
                            "website," +
                            "start_date"  )
                    .asObject(Developer[].class).getBody())));
            assertTrue( developers.size() != 0  );
        } catch (UnirestException e) {
            assertTrue(false);
        }
    }

}