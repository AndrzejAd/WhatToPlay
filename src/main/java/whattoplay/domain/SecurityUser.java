package whattoplay.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import whattoplay.domain.entities.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Andrzej
 */
public class SecurityUser implements UserDetails {
    private final UserEntity user;
    private static final Logger LOG = Logger.getLogger(SecurityUser.class.getName());
    
    public SecurityUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        user.getRoles().forEach( role ->  authorityList.add( () -> ("ROLE_" + role.getRoleName().replace(" ", "")) ));
        return authorityList; 
    }

    @Override
    public String getPassword() {
        return user.getPassword().replaceAll(" " , "");
    }

    @Override
    public String getUsername() {
        return user.getUsername().replaceAll(" " , "");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    
    
}
