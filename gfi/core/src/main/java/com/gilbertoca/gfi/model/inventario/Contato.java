package com.gilbertoca.gfi.model.inventario;

import java.io.Serializable;


/**
 * @author Gilberto
 * Represents a contact.
 * 
 * @hibernate.class table="construtor.estoque_contato" 
 */
public class Contato implements Serializable {
    /** identifier field */
    private ContatoPK comp_id = new ContatoPK();
    private Fornecedor fornecedor = new Fornecedor();
    private int version = -1; //version int4 not null, --Hibernate-especifico:Usado para determinar se instancia é transient or detached 
    
    private String representante;//Nome do representante deste fornecedor;pode ter mais de um
    private String nome;
    private String celular;
    private String telefone;
    private String fax;
    private String email;
    

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *         
     */
    public ContatoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(ContatoPK comp_id) {
        this.comp_id = comp_id;
    }

    /**
     * @return Returns the updated timestamp.
     * @hibernate.version unsaved-value = "negative"
     */
    public int getVersion() {
        return version;
    }
    /**
     * @param updated The updated version to set.
     */
    public void setVersion(int version) {
        this.version = version;
    }    
    /**
     * @return Returns the celular.
     * @hibernate.property column="celular" length = "16"
     */
    public String getCelular() {
        return celular;
    }
    /**
     * @param celular The celular to set.
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @param string
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @hibernate.property column="email" length="80"
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param string
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @hibernate.property column="nome" length="100"
     */
    public String getNome() {
        return nome;
    }


    /**
     * @hibernate.many-to-one insert="false" update="false" outer-join="true"
     * column = "cd_fornecedor"
     * @return a populated Fornecedor object (based on the cdFornecedor)
     */
   public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor
     */
  public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @hibernate.property column="fax" length="30"
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     *            The fax to set.
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @hibernate.property column="representante" length="100"
     */
    public String getRepresentante() {
        return representante;
    }

    /**
     * @param representante
     *            The representante to set.
     */
    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    /**
     * @hibernate.property column="telefone" length="30"
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone
     *            The telefone to set.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
