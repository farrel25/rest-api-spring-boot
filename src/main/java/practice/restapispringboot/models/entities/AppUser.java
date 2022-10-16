package practice.restapispringboot.models.entities;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * this User Entity must implement UserDetails interface from spring security core
 */
@Entity
@Table(name = "users")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String fullName;
    
    @Column(length = 150, nullable = false, unique = true)
    private String email;

    @Column(length = 200, nullable = false)
    private String password;

    /**
     * because the appUserRole property is an Enum, 
     * we need to add the @Enumerated() annotation
     * with the enum type, which is a string.
     */
    // @Enumerated((EnumType.STRING))
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    /**
     * used for user authorities (roles), namely the user have some
     * authorities as a super admin / admin / user etc.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // // TODO Auto-generated method stub
        // return null;

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * isEnabled() method is usually used when a user registers
     * and needs email confirmation. If you have confirmed the
     * email, then the account enable = true, else enable = false
     */
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }
}
