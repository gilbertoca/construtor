package park.model.annotation;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="legal_entity")
@DiscriminatorValue("LE")
@PrimaryKeyJoinColumn(name="person_id",referencedColumnName="id")
@NamedQueries({
    @NamedQuery(name="LegalEntity.findAll",query="SELECT l FROM LegalEntity l"),
    @NamedQuery(name="LegalEntity.findByName",query="SELECT l FROM LegalEntity l WHERE l.name LIKE :name"),@NamedQuery(name="LegalEntity.deleteById",query="DELETE FROM LegalEntity l WHERE l.id = :id")
})
public class LegalEntity extends Person{
    private static final long serialVersionUID = 1L;
    @Basic
    @Column(name="taxpayers_id", length=20)
    private String taxpayersId;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="dt_foundation")
    private Date dtFoundation;

    public LegalEntity() {
    }

    public LegalEntity(String address, String name, String taxpayersId, Date dtFoundation) {
        super(name, address);
        this.taxpayersId = taxpayersId;
        this.dtFoundation = dtFoundation;
    }

    public LegalEntity(String address, String name, Date dtFoundation, String taxpayersId) {
        super(address, name);
        this.taxpayersId = taxpayersId;
        this.dtFoundation = dtFoundation;
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
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LegalEntity other = (LegalEntity) obj;
        if ((this.taxpayersId == null) ? (other.taxpayersId != null) : !this.taxpayersId.equals(other.taxpayersId)) {
            return false;
        }
        if (this.dtFoundation != other.dtFoundation && (this.dtFoundation == null || !this.dtFoundation.equals(other.dtFoundation))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
}
