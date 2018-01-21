package whattoplay.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import whattoplay.domain.entities.RoleEntity;

import java.util.Collection;

@JsonDeserialize( as = BaseUserDto.class)
public interface UserDto {
    String getFirstName() ;
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
    String getUsername();
    void setUsername(String username);
    String getPassword();
    void setPassword(String password);
    String getEmail();
    void setEmail(String email);
    long getUserId();
    @Override String toString();

}
