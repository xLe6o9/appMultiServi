package xfactor.org.app.repository.iMSSQL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xfactor.org.app.models.mssql.Usuarios;

import java.util.Optional;

@Repository
public interface UsuariosJPA extends JpaRepository<Usuarios, Long> {


    // Solo para el Login
    Optional<Usuarios> findByUsuario(String username);
}
