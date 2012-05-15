package com.gilbertoca.igreja.model.user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Administrador
 * @version 1.0
 * @created 31-dic-2010 03:58:52 p.m.
 */
@Entity
@Table(name = "vendedor")
public class Vendedor implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="ortopedista_gen")
    @SequenceGenerator(name="ortopedista_seq",sequenceName="ortopedista_seq", allocationSize=1)
    private Long id;

    @OneToOne
    @JoinColumn(name="id_informacion_personal")
    private InformacionPersonal informacionPersonal;

    @Column(name="nro_vendedor")
    private int nroVendedor;

    public Vendedor(){
        setInformacionPersonal(new InformacionPersonal());
    }

    public Vendedor(Integer nroVendedor){
        setNroVendedor(nroVendedor);
        setInformacionPersonal(new InformacionPersonal());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InformacionPersonal getInformacionPersonal() {
        return informacionPersonal;
    }

    public void setInformacionPersonal(InformacionPersonal informacionPersonal) {
        this.informacionPersonal = informacionPersonal;
    }

    public int getNroVendedor() {
        return nroVendedor;
    }

    public void setNroVendedor(int nroVendedor) {
        this.nroVendedor = nroVendedor;
    }

    @Override
    public String toString() {
        return getInformacionPersonal().getApellidos()+", "+getInformacionPersonal().getNombres();
    }



}