package xfactor.org.app.core.mssql.dto;

import xfactor.org.app.models.mssql.Autenticacion;

import java.util.Collection;
import java.util.Date;

public class UsuariosDTO {

    private Long idUsuario;
    private String usuario;
    private String contrasena;
    private Date fechaCreacion;
    private Boolean estado;

    private Collection<Autenticacion> cargos;

//  CONSTRUCTORES
    public UsuariosDTO() {
    }

//  SET & GET
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public Collection<Autenticacion> getCargos() {
        return cargos;
    }

    public void setCargos(Collection<Autenticacion> cargos) {
        this.cargos = cargos;
    }
}
