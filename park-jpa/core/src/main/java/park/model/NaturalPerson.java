package park.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the naturalperson database table.
 * 
 */
@Entity
@Table(name = "NATURAL_PERSON")
@DiscriminatorValue("NP")
@NamedQueries({
    @NamedQuery(name = NaturalPerson.FIND_ALL, query = "SELECT np FROM NaturalPerson np")
})
public class NaturalPerson extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "findAllNaturalPerson";
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_BIRTH")
    private Date dtBirth;
    @Column(length = 20)
    private String legalDocument;

    public NaturalPerson() {
    }

    public NaturalPerson(String address, String name, Date dtBirth, String legalDocument) {
        super(address, name);
        this.dtBirth = dtBirth;
        this.legalDocument = legalDocument;
    }

    public Date getDtBirth() {
        return this.dtBirth;
    }

    public void setDtBirth(Date dtBirth) {
        this.dtBirth = dtBirth;
    }

    public String getLegalDocument() {
        return this.legalDocument;
    }

    public void setLegalDocument(String legalDocument) {
        this.legalDocument = legalDocument;
    }
}
