package xfactor.org.app.repository.iMSSQL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xfactor.org.app.models.mssql.Autenticacion;

import java.util.List;

@Repository
public interface AutenticacionJPA extends JpaRepository<Autenticacion, Long> {

    List<Autenticacion> findByAuthDescripcion(String autorityList);
}
