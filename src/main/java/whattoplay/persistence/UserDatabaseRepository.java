package whattoplay.persistence;

import whattoplay.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Andrzej
 */

@Repository("userRepository")
public interface UserDatabaseRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    @Override List<UserEntity> findAll();
    @Override UserEntity save(UserEntity user);
    @Override void delete(UserEntity userEntity);
}
