package whattoplay.domain.dto;

import whattoplay.domain.entities.RoleEntity;

import java.util.Collection;

public class BaseUserDto implements UserDto{
    private long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    public BaseUserDto() {
    }
   
    public BaseUserDto(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }
  
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "BaseUserDto{" + "userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password=" + password + ", email=" + email + '}';
    }
    
    
    
}
