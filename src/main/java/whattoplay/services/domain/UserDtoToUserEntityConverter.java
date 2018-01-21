package whattoplay.services.domain;

import whattoplay.domain.dto.UserDto;
import whattoplay.domain.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author Andrzej
 */
@Service
public class UserDtoToUserEntityConverter {
    public UserEntity convert(UserDto user){
        return new UserEntity(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword() , user.getEmail() );
    }
    
    public Collection<UserEntity> convertAll(Collection<UserDto> fElements){
        return fElements.stream()
                .map(element -> convert(element))
                .collect(Collectors.toList());
    }
    
}
