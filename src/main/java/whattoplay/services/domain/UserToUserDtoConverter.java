package whattoplay.services.domain;

import whattoplay.domain.dto.BaseUserDto;
import whattoplay.domain.dto.UserDto;
import whattoplay.domain.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserToUserDtoConverter implements UserDtoConverter {

    @Override
    public UserDto convert(UserEntity from) {
        return new BaseUserDto( from.getFirstName(), from.getLastName(), 
                                from.getUsername(), from.getPassword(),
                                from.getEmail() );
    }
    
}
