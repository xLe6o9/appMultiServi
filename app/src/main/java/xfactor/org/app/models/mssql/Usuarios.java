package xfactor.org.app.models.mssql;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tblu_Usuarios")
public class Usuarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Usuario")
    private Long idUsuario;
    @Column(name = "usuario", length = 15, nullable = false, unique = true)
    private String usuario;
    @Column(name = "contrasena", length = 150, nullable = false)
    private String contrasena;
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @ManyToMany(targetEntity = Autenticacion.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tblu_Usuarios_Autenticacion",
            joinColumns = @JoinColumn(name = "idUsuario", referencedColumnName = "id_Usuario"),
            inverseJoinColumns = @JoinColumn(name = "idAutentica", referencedColumnName = "id_Autentica")
    )
    private List<Autenticacion> authDescripcion;

//    CONSTRUCTOR
    public Usuarios() {
    }

    public Usuarios(String usuario, String contrasena, Date fechaCreacion, Boolean estado, List<Autenticacion> authDescripcion) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.authDescripcion = authDescripcion;
    }

//    GET & SET
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

    public List<Autenticacion> getAuthDescripcion() {
        return authDescripcion;
    }

    public void setAuthDescripcion(List<Autenticacion> authdescripcion) {
        this.authDescripcion = authDescripcion;
    }
}
