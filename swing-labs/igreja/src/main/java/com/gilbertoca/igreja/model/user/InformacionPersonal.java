package com.gilbertoca.igreja.model.user;

import com.gilbertoca.igreja.model.Localidad;
import com.gilbertoca.igreja.model.Region;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Administrador
 * @version 1.0
 * @created 31-dic-2010 03:58:51 p.m.
 */
@Entity
@Table(name="informacion_personal")
public class InformacionPersonal implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="informacion_personal")
    @SequenceGenerator(name="informacion_personal",sequenceName="informacion_personal", allocationSize=1)
    private Long id;

    @Column(length=40)
    private String nombres;

    @Column(length=40)
    private String apellidos;

    @Column(length=60, name="e_mail")
    private String eMail;

    @Column(length=255)
    private String direccion;

    @ManyToOne
    @JoinColumn(name="id_localidad")
    private Localidad localidad;

    @ManyToOne
    @JoinColumn(name="id_region")
    private Region region;

    @Column(length=40)
    private String telefonoFijo;

    @Column(length=50)
    private String telefonoLaboral;

    @Column(length=50)
    private String telefonoMovil;

    @Column(length=20)
    private String peso;

    @Column(length=20)
    private String edad;

    @Column(length=20)
    private String altura;

    private String patologia;

    private String parentezco;

    public InformacionPersonal(){

    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoLaboral() {
        return telefonoLaboral;
    }

    public void setTelefonoLaboral(String telefonoLaboral) {
        this.telefonoLaboral = telefonoLaboral;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }


}