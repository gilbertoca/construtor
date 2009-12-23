package park.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the legalentity database table.
 * 
 */
@Entity
@Table(name = "LEGAL_ENTITY")
@DiscriminatorValue("LE")
@NamedQueries({
    @NamedQuery(name = LegalEntity.FIND_ALL, query = "SELECT lp FROM LegalEntity lp")
})
public class LegalEntity extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "findAllLegalEntity";
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_FOUNDATION", nullable = false)
    private Date dtFoundation;
    @Column(name = "TAXPAYERS_ID", nullable = false, length = 20)
    private String taxpayersId;

    public LegalEntity() {
    }

    public LegalEntity(String address, String name, Date dtFoundation, String taxpayersId) {
        super(address, name);
        this.dtFoundation = dtFoundation;
        this.taxpayersId = taxpayersId;
    }

    public Date getDtFoundation() {
        return this.dtFoundation;
    }

    public void setDtFoundation(Date dtFoundation) {
        this.dtFoundation = dtFoundation;
    }

    public String getTaxpayersId() {
        return this.taxpayersId;
    }

    public void setTaxpayersId(String taxpayersId) {
        this.taxpayersId = taxpayersId;
    }
}
