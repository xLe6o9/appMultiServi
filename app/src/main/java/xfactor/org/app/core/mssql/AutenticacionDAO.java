package xfactor.org.app.core.mssql;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xfactor.org.app.core.mssql.dto.AutenticacionDTO;
import xfactor.org.app.core.mssql.src.AutenticacionSRC;
import xfactor.org.app.models.mssql.Autenticacion;
import xfactor.org.app.repository.iMSSQL.AutenticacionJPA;

@Transactional
@Repository
@Service
public class AutenticacionDAO implements AutenticacionSRC {

    private final AutenticacionJPA jpaAuthority;

    public AutenticacionDAO(AutenticacionJPA authorityJPA) {
        this.jpaAuthority = authorityJPA;
    }

    //

    // C
    @Override
    public Autenticacion CrearAutentica(AutenticacionDTO autho) {

        Autenticacion frmAuthority = new Autenticacion(
                autho.getAuthDescripcion(),
                autho.getFechaCreacion(),
                autho.getEstado()
        );

        return jpaAuthority.save(frmAuthority);
    }

    // R
    // U
    // D
    // S

}
