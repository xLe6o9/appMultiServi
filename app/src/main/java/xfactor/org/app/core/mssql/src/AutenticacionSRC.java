package xfactor.org.app.core.mssql.src;

import xfactor.org.app.core.mssql.dto.AutenticacionDTO;
import xfactor.org.app.models.mssql.Autenticacion;

public interface AutenticacionSRC {

    public Autenticacion CrearAutentica(AutenticacionDTO frmAuhto);
}
