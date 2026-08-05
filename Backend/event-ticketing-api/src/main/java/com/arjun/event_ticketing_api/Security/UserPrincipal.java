package com.arjun.event_ticketing_api.Security;

import com.arjun.event_ticketing_api.model.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private UsersEntity user;

    public UserPrincipal(UsersEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(
                        "ROLE_" + user.getRole()
                )
        );
    }
    @Override
    public String getUsername() {
        return user.getUsername();
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

}
