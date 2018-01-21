package whattoplay.services.persistance;

import whattoplay.domain.SecurityUser;
import whattoplay.domain.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whattoplay.services.persistance.UserDatabaseService;

@Service
public class UserAuthService implements UserDetailsService {
    private  UserDatabaseService userDatabaseService;
    
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
        return new SecurityUser(user);
    }
    
    
     
    
}
