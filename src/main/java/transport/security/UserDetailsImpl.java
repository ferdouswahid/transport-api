package transport.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    public Long userId;
    public String username;
    public String password;
    Collection<? extends GrantedAuthority> roleList;

    public UserDetailsImpl(){ }

    public UserDetailsImpl(Long userId,String username,String password,
                           Collection<? extends GrantedAuthority> roleList){
        this.userId=userId;
        this.username=username;
        this.password=password;
        this.roleList=roleList;
    }

    public Long getUserId() {
        return this.userId;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
