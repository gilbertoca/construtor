/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "legal_entity")
@NamedQueries({
    @NamedQuery(name = "LegalEntity.findAll", query = "SELECT l FROM LegalEntity l")})
public class LegalEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cd_person")
    private Integer cdPerson;
    @Basic(optional = false)
    @Column(name = "taxpayers_id")
    private String taxpayersId;
    @Basic(optional = false)
    @Column(name = "dt_foundation")
    @Temporal(TemporalType.DATE)
    private Date dtFoundation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdPerson")
    private Collection<Parking> parkingCollection;
    @JoinColumn(name = "cd_person", referencedColumnName = "cd_person", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public LegalEntity() {
    }

    public LegalEntity(Integer cdPerson) {
        this.cdPerson = cdPerson;
    }

    public LegalEntity(Integer cdPerson, String taxpayersId, Date dtFoundation) {
        this.cdPerson = cdPerson;
        this.taxpayersId = taxpayersId;
        this.dtFoundation = dtFoundation;
    }

    public Integer getCdPerson() {
        return cdPerson;
    }

    public void setCdPerson(Integer cdPerson) {
        this.cdPerson = cdPerson;
    }

    public String getTaxpayersId() {
        return taxpayersId;
    }

    public void setTaxpayersId(String taxpayersId) {
        this.taxpayersId = taxpayersId;
    }

    public Date getDtFoundation() {
        return dtFoundation;
    }

    public void setDtFoundation(Date dtFoundation) {
        this.dtFoundation = dtFoundation;
    }

    public Collection<Parking> getParkingCollection() {
        return parkingCollection;
    }

    public void setParkingCollection(Collection<Parking> parkingCollection) {
        this.parkingCollection = parkingCollection;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        if (!(object instanceof LegalEntity)) {
            return false;
        }
        LegalEntity other = (LegalEntity) object;
        if ((this.cdPerson == null && other.cdPerson != null) || (this.cdPerson != null && !this.cdPerson.equals(other.cdPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.LegalEntity[cdPerson=" + cdPerson + "]";
    }

}
