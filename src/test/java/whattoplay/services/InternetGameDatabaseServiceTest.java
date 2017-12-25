package whattoplay.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import whattoplay.domain.entities.Developer;
import whattoplay.persistence.GamesDatabaseRepository;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Andrzej on 2017-12-19.
 */


class InternetGameDatabaseServiceTest {
    static InternetGameDatabaseService internetGameDatabaseService;
    @Mock
    static GamesDatabaseRepository databaseMock;

    @BeforeAll
    static void setInternetGameDatabaseService(){
        internetGameDatabaseService = new InternetGameDatabaseService(databaseMock);
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
            assertEquals( internetGameDatabaseService.getScroll( "https://api-2445582011268.apicast.io/companies/", developersFields  ).asObject(Developer[].class).getStatus(), 200);
        } catch (UnirestException e) {
            assertTrue(false);
        }
    }

    @Test
    void shouldBeAbleToCastDevelopers(){
        ArrayList<Developer> developers;
        try {
            developers = new ArrayList<>( Arrays.asList( (internetGameDatabaseService
                    .getScroll( "https://api-2445582011268.apicast.io/companies/", internetGameDatabaseService.getDeveloperFields()  )
                    .asObject(Developer[].class).getBody())));
            assertTrue( developers.size() != 0  );
        } catch (UnirestException e) {
            assertTrue(false);
        }
    }

}