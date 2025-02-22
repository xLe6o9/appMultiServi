package xfactor.org.app.aop.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xfactor.org.app.models.mssql.Usuarios;

import java.util.Collection;


public class SecurityUser implements UserDetails {

    private final Usuarios usuarios;

    public SecurityUser(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String getUsername() {
        return usuarios.getUsuario();
    }

    @Override
    public String getPassword() {
        return usuarios.getContrasena();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuarios.getAuthDescripcion().stream().map(SecurityAuthority::new).toList();
    }

//    ==========================================================================================================
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
