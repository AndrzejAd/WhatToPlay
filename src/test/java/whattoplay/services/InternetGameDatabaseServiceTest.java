package whattoplay.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import whattoplay.persistence.GamesDatabaseRepository;
import whattoplay.persistence.MSSqlGameDatabase;
import whattoplay.services.domain.GameDtoToGameEntityConverter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Andrzej on 2017-12-19.
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
class InternetGameDatabaseServiceTest {

    @Autowired
    private InternetGameDatabaseService internetGameDatabaseService;

    @Test
    void testMessage() {
    }


}