package whattoplay.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Andrzej
 */

@Entity
@Table(name = "Roles")
public class RoleEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="[Role Id]")
    private int roleId;

    @Column(name="[Role Name]")
    private String roleName;

    @Column(name="[Role Number]")
    private int roleNumber;

    @Column(name="user_id")
    private long userId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", insertable=false, updatable=false)
    private UserEntity user;


    public RoleEntity() {
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleNumber() {
        return roleNumber;
    }

    public void setRoleNumber(int roleNumber) {
        this.roleNumber = roleNumber;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public RoleEntity(String roleName, int roleNumber, long userId) {
        this.roleName = roleName;
        this.roleNumber = roleNumber;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RoleEntity{" + "roleId=" + roleId + ", roleName=" + roleName + ", roleNumber=" + roleNumber + ", userId=" + userId + '}';
    }
		
    
}