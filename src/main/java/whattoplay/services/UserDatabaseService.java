package whattoplay.services;

import whattoplay.domain.dto.UserDto;
import whattoplay.domain.entities.UserEntity;
import whattoplay.exceptions.NotValidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import whattoplay.persistence.UserDatabaseRepository;
import whattoplay.services.domain.UserDtoToUserEntityConverter;
import whattoplay.services.domain.UserToUserDtoConverter;

/**
 *
 * @author Andrzej
 */
@Service
public class UserDatabaseService {
    private UserDatabaseRepository userDatabaseRepository;
    private UserToUserDtoConverter userToUserDtoConverter;
    private UserDtoToUserEntityConverter userDtoToUserEntityConverter;
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserDatabaseService(UserDatabaseRepository userDatabaseRepository, UserToUserDtoConverter userToUserDtoConverter, 
                               UserDtoToUserEntityConverter userDtoToUserEntityConverter) {
        this.userDatabaseRepository = userDatabaseRepository;
        this.userToUserDtoConverter = userToUserDtoConverter;
        this.userDtoToUserEntityConverter = userDtoToUserEntityConverter;
    }
    
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    public UserEntity getUserByUsername(String username){
        try {
            return userDatabaseRepository.findByUsername(username);
        } catch( EmptyResultDataAccessException exc ){
            throw exc;
        }
    }
    
    public UserEntity saveUser(UserDto user) throws NotValidPasswordException{
        if ( validatePassword(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userDatabaseRepository.save( userDtoToUserEntityConverter.convert(user) );
        } else{
            throw new NotValidPasswordException();
        }
        
    }
    
    private boolean validatePassword(String password){
        if ( password.matches(".*\\d+.*") && password.length() >= 8 && password.length() <= 32 ){
            return true;
        } 
        return false;
        
    }
    
}
