package whattoplay.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import whattoplay.domain.entities.Developer;
import whattoplay.domain.entities.GameMode;
import whattoplay.domain.entities.Genre;

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
}