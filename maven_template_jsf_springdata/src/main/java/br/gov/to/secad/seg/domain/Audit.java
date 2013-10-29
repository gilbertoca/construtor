package br.gov.to.secad.seg.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "audit", schema = "reca")
@NamedQueries({
    @NamedQuery(name = "Audit.findAll", query = "SELECT a FROM Audit a"),
    @NamedQuery(name = "Audit.findById", query = "SELECT a FROM Audit a WHERE a.id = :id"),
    @NamedQuery(name = "Audit.findByUserId", query = "SELECT a FROM Audit a WHERE a.userId = :userId"),
    @NamedQuery(name = "Audit.findByEventType", query = "SELECT a FROM Audit a WHERE a.eventType = :eventType"),
    @NamedQuery(name = "Audit.findByTableName", query = "SELECT a FROM Audit a WHERE a.tableName = :tableName"),
    @NamedQuery(name = "Audit.findByRecordId", query = "SELECT a FROM Audit a WHERE a.recordId = :recordId"),
    @NamedQuery(name = "Audit.findByEventDate", query = "SELECT a FROM Audit a WHERE a.eventDate = :eventDate"),
    @NamedQuery(name = "Audit.findByDescription", query = "SELECT a FROM Audit a WHERE a.description = :description")})
public class Audit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="audit_id_seq")
    @SequenceGenerator(schema="reca", name="audit_id_seq", sequenceName="audit_id_seq", allocationSize=1)    
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false, length = 14)
    private String userId;
    
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false, length = 10)
    private EventType eventType = EventType.CREATED;
   @Basic(optional = false)
    @Column(name = "table_name", nullable = false, length = 50)
    private String tableName;
    @Basic(optional = false)
    @Column(name = "record_id", nullable = false, length = 50)
    private String recordId;

    @Basic(optional = false)
    @Column(name = "event_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 4000)
    private String description;

    public Audit() {
    }

    public Audit(String userId, EventType eventType, String tableName, String recordId, Date eventDate, String description) {
        this.userId = userId;
        this.eventType = eventType;
        this.tableName = tableName;
        this.recordId = recordId;
        this.eventDate = eventDate;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Audit)) {
            return false;
        }
        Audit other = (Audit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.to.secad.seg.domain.Audit[id=" + id + "]";
    }


}
