package xfactor.org.app.models.mssql;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tblu_Autenticacion" )
public class Autenticacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Autentica")
    private Long idAutentica;
    @Column(name = "authDescripcion", length = 250, unique = true)
    private String authDescripcion;
    @Column(name = "fechaCreacion", nullable = false)
    private Date fechaCreacion;
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    // CONSTRUCTOR
    public Autenticacion() {
    }

    public Autenticacion(String authDescripcion, Date fechaCreacion, Boolean estado) {
        this.authDescripcion = authDescripcion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }
    public Autenticacion(String authDescripcion) {
        this.authDescripcion = authDescripcion;
    }

    // GET & SET
    public Long getIdAutentica() {
        return idAutentica;
    }

    public void setIdAutentica(Long idAutentica) {
        this.idAutentica = idAutentica;
    }

    public String getAuthDescripcion() {
        return authDescripcion;
    }

    public void setAuthDescripcion(String authDescripcion) {
        this.authDescripcion = authDescripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
