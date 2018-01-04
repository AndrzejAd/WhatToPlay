package whattoplay.services.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import whattoplay.domain.entities.GameJson;
import whattoplay.persistence.GameFieldsDatabaseRepository;
import whattoplay.persistence.MsSqlGameFieldsDatabaseRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GameJsonToGameConverterTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    GameJsonToGameConverter gameJsonToGameConverter;

    @Autowired
    MsSqlGameFieldsDatabaseRepository gameFieldsDatabaseRepository;

    @Test
    void gameJsonToGameConverterShouldNotBeNull() {
        assertNotNull( gameJsonToGameConverter);
    }

    @Test
    void gameFieldsDatabaseRepositoryShouldNotBeNull() {
        assertNotNull( gameFieldsDatabaseRepository);
    }

    @Test
    void shouldConvertCollection() {
        GameJson gameJson = new GameJson();
        gameJson.setCollectionId(1);
        assertEquals( "Bioshock" , gameJsonToGameConverter.convert(gameJson).getCollectionId().getName().trim());
    }

    @Test
    void shouldConvertFranchise() {
        GameJson gameJson = new GameJson();
        gameJson.setFranchiseId(1);
        assertEquals( "Star Wars" , gameJsonToGameConverter.convert(gameJson).getFranchiseId().getName().trim());
    }

}