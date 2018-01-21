package whattoplay.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import whattoplay.domain.dto.GameJson;
import whattoplay.domain.entities.IgdbGame;
import whattoplay.domain.entities.ImageInfo;
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
    GameJsonToNormalFormCacher gameJsonToNormalFormCacher;

    @Test
    void gameJsonToGameConverterShouldNotBeNull() {
        assertNotNull( gameJsonToGameConverter);
    }

    @Test
    void gameFieldsDatabaseRepositoryShouldNotBeNull() {
        assertNotNull( gameFieldsDatabaseRepository);
    }

    @Test
    void gameJsonToNormalFormCacherShouldNotBeNull() {
        assertNotNull( gameJsonToNormalFormCacher);
    }

    @Test
    void shouldPersistBasicNormalFormOfGameJson() {
        // Given
        long numberOfGames
                = (long) entityManager.getEntityManager()
                .createQuery("Select count (x.id) from IgdbGame x").getSingleResult();
        GameJson jsonGame = new GameJson();
        jsonGame.setId(0);
        jsonGame.setName("Test Game");
        jsonGame.setSlug("Test-Game");
        jsonGame.setCover(new ImageInfo("", "", 200, 300));
        System.out.println(numberOfGames);
        // When

        gameJsonToNormalFormCacher.persistNormalFormOfGameJson(jsonGame);

        // Then
        long numberOfGamesAfterAddition
                = (long) entityManager.getEntityManager()
                .createQuery("Select count (x.id) from IgdbGame x").getSingleResult();
        assertEquals(numberOfGames + 1, numberOfGamesAfterAddition );
    }

    private class RandomJsonCreater{
        public GameJson returnRandomGameJson(){
            GameJson jsonGame = new GameJson();
            return jsonGame;
        }
    }

}