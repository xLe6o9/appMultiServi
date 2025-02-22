package xfactor.org.app.aop.security;

import org.springframework.security.core.GrantedAuthority;
import xfactor.org.app.models.mssql.Autenticacion;

public class SecurityAuthority implements GrantedAuthority {

    private final Autenticacion authority;
    public SecurityAuthority(Autenticacion authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.getAuthDescripcion().toString();
    }

}
