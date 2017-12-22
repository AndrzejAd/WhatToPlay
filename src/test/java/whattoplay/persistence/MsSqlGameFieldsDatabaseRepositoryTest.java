package whattoplay.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MsSqlGameFieldsDatabaseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    MsSqlGameFieldsDatabaseRepository msSqlGameFieldsDatabaseRepository;

    @Test
    public void lengthShouldBe5() {
    }
}