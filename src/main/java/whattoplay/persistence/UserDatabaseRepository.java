package whattoplay.persistence;

import whattoplay.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andrzej
 */

@Repository("userRepository")
public interface UserDatabaseRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    @Override
    UserEntity save(UserEntity user);
}
