package com.gilbertoca.igreja.model.security;

import com.gilbertoca.igreja.model.user.InformacionPersonal;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/**
 * Reresenta el usuario del sistema.
 * @author Mono
 */
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="usuario_gen")
    @SequenceGenerator(name="usuario_seq",sequenceName="usuario_seq", allocationSize=1)
    private Long id;

    /*Acciones*/
    public static final String  COD_CREAR = "CRMO_USR" ;
    public static final String  COD_MODIFICAR = "CRMO_USR" ;
    public static final String  COD_ELIMINAR = "ELIM_USR" ;

    @Version
    private Integer version;

    @Column(length=20)
    private String username;
    
    @Column(length=15)
    private String password;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_informacion_personal")
    private InformacionPersonal informacionPersonal;

    private Boolean borrado = false;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_rol")
    private Rol rol;

    public Usuario(){
        this.setInformacionPersonal(new InformacionPersonal());
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return this.getInformacionPersonal().getApellidos() + ", " + this.getInformacionPersonal().getNombres();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass().equals(Usuario.class)) {
            return ((Usuario)obj).getId().equals(this.getId());
        }
        return false;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean tieneAccion(Accion accion){
        return this.getRol().tieneAccion(accion.getClave());
    }

    public Boolean tieneAccion(String codigoAccion){
        return this.getRol().tieneAccion(codigoAccion);
    }

    public Iterable<Accion> getAcciones() {
        return this.getRol().getAcciones();
    }

    public void agregarAccion(Accion a) {
        this.getRol().getAcciones().add(a);
    }

    public InformacionPersonal getInformacionPersonal() {
        return informacionPersonal;
    }

    public void setInformacionPersonal(InformacionPersonal informacionPersonal) {
        this.informacionPersonal = informacionPersonal;
    }
}
