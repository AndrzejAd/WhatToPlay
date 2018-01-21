package whattoplay.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import whattoplay.domain.entities.IgdbGame;
import whattoplay.domain.entities.ImageInfo;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MsSqlGameDatabaseTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    MsSqlGameDatabase msSqlGameDatabase;

    @Test
    void persistGame() {
        IgdbGame igdbGame = new IgdbGame();
        igdbGame.setId(1);
        igdbGame.setName("test");
        igdbGame.setCover(new ImageInfo("a", "b", 100, 100));
        msSqlGameDatabase.persistGame(igdbGame);
        assertNotNull( entityManager.getEntityManager().find(IgdbGame.class, (long) 1) );
    }

    @Test
    void getNumberOfRows() {
        IgdbGame igdbGame = new IgdbGame();
        igdbGame.setId(1);
        igdbGame.setName("test");
        igdbGame.setCover(new ImageInfo("a", "b", 100, 100));
        IgdbGame igdbGame1 = new IgdbGame();
        igdbGame1.setId(2);
        igdbGame1.setName("test");
        igdbGame1.setCover(new ImageInfo("a", "b", 100, 100));
        entityManager.getEntityManager().persist(igdbGame);
        entityManager.getEntityManager().persist(igdbGame1);
        assertEquals(2,msSqlGameDatabase.getNumberOfRows() );
    }
}