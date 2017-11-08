package whattoplay.services;

import whattoplay.domain.dto.SecurityUser;
import whattoplay.domain.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrzej
 */
@Service
public class UserAuthService implements UserDetailsService {
    private UserDatabaseService userDatabaseService;
    
    public UserAuthService(){
        super();
    }
    
    @Autowired
    public void setUserDatabaseService(UserDatabaseService userDatabaseService) {
        this.userDatabaseService = userDatabaseService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDatabaseService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        SecurityUser u = new SecurityUser(user);
        return u;
    }
    
    
     
    
}
