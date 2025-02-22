package xfactor.org.app.core.mssql.dto;

import java.util.Date;

public class AutenticacionDTO{

    private Long idAutentica;
    private String authDescripcion;
    private Date fechaCreacion;
    private Boolean estado;

//    CONSTRUCTOR
    public AutenticacionDTO() {
    }

//    GET & SET
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
