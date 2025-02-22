package xfactor.org.app.core.mssql.src;


import xfactor.org.app.core.mssql.dto.UsuariosDTO;
import xfactor.org.app.models.mssql.Usuarios;

import java.util.List;

public interface UsuariosSRC {

    public Usuarios CrearUsuario(UsuariosDTO dtoUsuario);
    public List<Usuarios> ListarTodosUsuarios();

}
