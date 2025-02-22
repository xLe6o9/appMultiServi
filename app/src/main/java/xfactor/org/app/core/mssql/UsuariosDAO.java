package xfactor.org.app.core.mssql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xfactor.org.app.core.mssql.dto.UsuariosDTO;
import xfactor.org.app.core.mssql.src.UsuariosSRC;
import xfactor.org.app.models.mssql.Autenticacion;
import xfactor.org.app.models.mssql.Usuarios;
import xfactor.org.app.repository.iMSSQL.UsuariosJPA;

import java.util.Arrays;
import java.util.List;

@Transactional
@Repository
@Service
public class UsuariosDAO implements UsuariosSRC {


    @Autowired
    private UsuariosJPA jpaUsuarios;

    //

    // C
    @Override
    public Usuarios CrearUsuario(UsuariosDTO objUsuario) {
        Usuarios frmUsuario = new Usuarios(
                objUsuario.getUsuario(),
                objUsuario.getContrasena(),
                objUsuario.getFechaCreacion(),
                objUsuario.getEstado(),
                Arrays.asList(new Autenticacion("ADMIN"))
        );
        return jpaUsuarios.save(frmUsuario);
    }

    // R
    @Override
    public List<Usuarios> ListarTodosUsuarios(){
        return jpaUsuarios.findAll();
    }

    // U
    // D
    // S

}
