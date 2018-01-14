package whattoplay.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import whattoplay.domain.entities.IgdbGame;
import whattoplay.persistence.MsSqlGameFieldsDatabaseRepository;
import whattoplay.services.domain.GameJsonToGameConverter;
import whattoplay.services.persistance.SafeGameDatabaseService;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class GameJsonToNormalFormCacherTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    GameJsonToGameConverter gameJsonToGameConverter;

    @Autowired
    MsSqlGameFieldsDatabaseRepository gameFieldsDatabaseRepository;

    @Autowired
    SafeGameDatabaseService safeGameDatabaseService;

    @Test
    void gameJsonToGameConverterShouldNotBeNull() {
        assertNotNull( gameJsonToGameConverter);
    }

    @Test
    void gameFieldsDatabaseRepositoryShouldNotBeNull() {
        assertNotNull( gameFieldsDatabaseRepository);
    }

    @Test
    void safeGameDatabaseServiceShouldNotBeNull() {
        assertNotNull( safeGameDatabaseService);
    }

    @Test
    void ShouldPersistNormalFormOfGameJson() {
        IgdbGame igdbGame = new IgdbGame();
    }
}