package whattoplay.persistence;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import whattoplay.domain.entities.Developer;
import whattoplay.domain.entities.Franchise;
import whattoplay.domain.entities.GameMode;
import whattoplay.domain.entities.Genre;
import whattoplay.services.InternetGameDatabaseCacher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MsSqlGameFieldsDatabaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    MsSqlGameFieldsDatabaseRepository msSqlGameFieldsDatabaseRepository;

    @Test
    void getAllGameModesShouldReturnGameModeEntity() {
        Optional.ofNullable( msSqlGameFieldsDatabaseRepository.getAllGameModes().get(0) )
                .ifPresent( x -> assertTrue( x instanceof GameMode, " Object is instance of GameMode." ));
    }

    @Test
    void getAllGenresShouldReturnGenreEntity() {
        Optional.ofNullable( msSqlGameFieldsDatabaseRepository.getAllGenres().get(0) )
                .ifPresent( x -> assertTrue( x instanceof Genre, " Object is instance of Genre." ));
    }

    @Test
    void getAllDevelopersShouldReturnDeveloperEntity() {
        Optional.ofNullable( msSqlGameFieldsDatabaseRepository.getAllDevelopers().get(0) )
                .ifPresent( x -> assertTrue( x instanceof Developer, " Object is instance of Developer." ));
    }

    @Test
    void rowNumbOfFranchisesTableShouldBeEqualToThatOfIGDB(){
        final HttpResponse<Franchise[]> jsonResponse;
        try {
            jsonResponse = Unirest.get("https://api-2445582011268.apicast.io/franchises/")
                    .header("accept", "application/json")
                    .header("user-key", InternetGameDatabaseCacher.getToken())
                    .queryString("fields", "id")
                    .queryString("limit", "50")
                    .queryString("scroll", 1)
                    .asObject(Franchise[].class);
            assertEquals( msSqlGameFieldsDatabaseRepository.getFranchiseTableSize(), Long.parseLong(jsonResponse.getHeaders().get("X-Count").get(0) ) );
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Disabled
    void rowNumbOfDeveloperTableShouldBeEqualToThatOfIGDB(){
        final HttpResponse<Developer[]> jsonResponse;
        try {
            jsonResponse = Unirest.get("https://api-2445582011268.apicast.io/companies/")
                    .header("accept", "application/json")
                    .header("user-key", "8dcd2a959fef891fbac266d5046e0414")
                    .queryString("fields", "id")
                    .queryString("limit", "50")
                    .queryString("scroll", 1)
                    .asObject(Developer[].class);
            System.out.println( msSqlGameFieldsDatabaseRepository.getDevelopersTableSize() + " " + Long.parseLong(jsonResponse.getHeaders().get("X-Count").get(0)));
            assertEquals( msSqlGameFieldsDatabaseRepository.getDevelopersTableSize(), Long.parseLong(jsonResponse.getHeaders().get("X-Count").get(0) ) );
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

}