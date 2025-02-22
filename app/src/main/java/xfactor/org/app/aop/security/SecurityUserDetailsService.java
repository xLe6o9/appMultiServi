package xfactor.org.app.aop.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xfactor.org.app.repository.iMSSQL.UsuariosJPA;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UsuariosJPA usuariosJPA;
    public SecurityUserDetailsService(UsuariosJPA usuariosJPA) {
        this.usuariosJPA = usuariosJPA;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var opUser = this.usuariosJPA.findByUsuario(username);
        if (opUser.isPresent()) {
            return new SecurityUser(opUser.get());
        }
        throw new UsernameNotFoundException("El siguiente Usuario no existe, " + username);
    }

}
