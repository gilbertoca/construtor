/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cd_person")
    private Integer cdPerson;
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "p_type")
    private char pType;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private NaturalPerson naturalPerson;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cdPerson")
    private Customer customer;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private LegalEntity legalEntity;

    public Person() {
    }

    public Person(Integer cdPerson) {
        this.cdPerson = cdPerson;
    }

    public Person(Integer cdPerson, String name, char pType) {
        this.cdPerson = cdPerson;
        this.name = name;
        this.pType = pType;
    }

    public Integer getCdPerson() {
        return cdPerson;
    }

    public void setCdPerson(Integer cdPerson) {
        this.cdPerson = cdPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getPType() {
        return pType;
    }

    public void setPType(char pType) {
        this.pType = pType;
    }

    public NaturalPerson getNaturalPerson() {
        return naturalPerson;
    }

    public void setNaturalPerson(NaturalPerson naturalPerson) {
        this.naturalPerson = naturalPerson;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdPerson != null ? cdPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.cdPerson == null && other.cdPerson != null) || (this.cdPerson != null && !this.cdPerson.equals(other.cdPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Person[cdPerson=" + cdPerson + "]";
    }

}
