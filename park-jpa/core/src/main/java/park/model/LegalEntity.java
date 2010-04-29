package park.model;

import java.io.Serializable;
import java.util.Date;

public class LegalEntity extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String taxpayersId;
    private Date dtFoundation;

    public LegalEntity() {
    }

    public LegalEntity(Person person, String taxpayersId, Date dtFoundation) {
        super(person.getName(), person.getAddress());
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
