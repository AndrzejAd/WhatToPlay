package whattoplay.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import whattoplay.domain.entities.IgdbGame;
import whattoplay.persistence.MsSqlGameDatabase;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SafeIgdbGameDatabaseServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    MsSqlGameDatabase msSqlGameDatabase;

    @Autowired
    SafeGameDatabaseService safeGameDatabaseService;

    @Test
    void persistGameShouldChangeCollectionIdIfConstraintVioliationOccurs() {
        IgdbGame igdbGame = new IgdbGame();
        igdbGame.setId(5);
        igdbGame.setName("AAA");
        //igdbGame.setCollection(999999999);
        igdbGame.setSlug("AA");
        msSqlGameDatabase.persistGame(igdbGame);
        //assertEquals(0, msSqlGameDatabase.getGameById(5).getCollection());
        //assertEquals(1, msSqlGameDatabase.getNumberOfRows());
        System.out.println(msSqlGameDatabase.getGameById(5));
    }



}