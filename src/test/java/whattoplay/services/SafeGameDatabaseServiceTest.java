package whattoplay.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import whattoplay.domain.entities.Game;
import whattoplay.persistence.MsSqlGameDatabase;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SafeGameDatabaseServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    MsSqlGameDatabase msSqlGameDatabase;

    @Autowired
    SafeGameDatabaseService safeGameDatabaseService;

    @Test
    void persistGameShouldChangeCollectionIdIfConstraintVioliationOccurs() {
        Game game = new Game();
        game.setId(5);
        game.setName("AAA");
        //game.setCollectionId(999999999);
        game.setSlug("AA");
        msSqlGameDatabase.persistGame(game);
        //assertEquals(0, msSqlGameDatabase.getGameById(5).getCollectionId());
        //assertEquals(1, msSqlGameDatabase.getNumberOfRows());
        System.out.println(msSqlGameDatabase.getGameById(5));
    }

}