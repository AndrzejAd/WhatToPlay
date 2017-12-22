package whattoplay.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Andrzej
 */
@Entity
@Table(name = "Users")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="[User Id]",  nullable = false)
    private long userId;
    
    @Column(name ="[First Name]", nullable = true)
    @Size(min = 3, max = 64 )
    private String firstName;
    
    @Column(name ="[Last Name]", nullable = true)
    @Size(min = 3, max = 64 )
    private String lastName;
    
    @NotNull
    @Column(name ="Username", nullable = false, unique = true)
    @Size(min = 2, max = 32 )
    private String username;
    
    @NotNull
    @Column(name ="Password", nullable = false)
    @Size(min = 6, max = 64 )
    private String password;
    
    @NotNull
    @Column(name ="Email", nullable = false)
    @Size(min = 3, max = 32 )
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId")
    private Collection<RoleEntity> roles;

    @NotNull
    @Column(name ="Enabled", nullable = false)
    private boolean enabled;
    
    private UserEntity(){}

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
    
    public UserEntity(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = true;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password=" + password + ", email=" + email + '}';
    }
    
    
    
    
}
