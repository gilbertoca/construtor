/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "natural_person")
@NamedQueries({
    @NamedQuery(name = "NaturalPerson.findAll", query = "SELECT n FROM NaturalPerson n")})
public class NaturalPerson implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cd_person")
    private Integer cdPerson;
    @Column(name = "dt_birth")
    @Temporal(TemporalType.DATE)
    private Date dtBirth;
    @Column(name = "legal_document")
    private String legalDocument;
    @JoinColumn(name = "cd_person", referencedColumnName = "cd_person", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cdPerson")
    private Employee employee;

    public NaturalPerson() {
    }

    public NaturalPerson(Integer cdPerson) {
        this.cdPerson = cdPerson;
    }

    public Integer getCdPerson() {
        return cdPerson;
    }

    public void setCdPerson(Integer cdPerson) {
        this.cdPerson = cdPerson;
    }

    public Date getDtBirth() {
        return dtBirth;
    }

    public void setDtBirth(Date dtBirth) {
        this.dtBirth = dtBirth;
    }

    public String getLegalDocument() {
        return legalDocument;
    }

    public void setLegalDocument(String legalDocument) {
        this.legalDocument = legalDocument;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
        if (!(object instanceof NaturalPerson)) {
            return false;
        }
        NaturalPerson other = (NaturalPerson) object;
        if ((this.cdPerson == null && other.cdPerson != null) || (this.cdPerson != null && !this.cdPerson.equals(other.cdPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.NaturalPerson[cdPerson=" + cdPerson + "]";
    }

}
