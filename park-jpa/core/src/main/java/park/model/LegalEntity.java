package park.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class LegalEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idPerson;
    private String taxpayersId;
    private Date dtFoundation;
    private Collection<Parking> parkingCollection;
    private Person person;

    public LegalEntity() {
    }

    public LegalEntity(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public LegalEntity(Integer idPerson, String taxpayersId, Date dtFoundation) {
        this.idPerson = idPerson;
        this.taxpayersId = taxpayersId;
        this.dtFoundation = dtFoundation;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
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
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LegalEntity)) {
            return false;
        }
        LegalEntity other = (LegalEntity) object;
        if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.LegalEntity[idPerson=" + idPerson + "]";
    }

}
