package park.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the person database table.
 * 
 */

@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CD_PERSON" )
    @GeneratedValue
    protected Integer cdPerson;
    @Column(length = 100)
    protected String address;
    @Column(nullable = false, length = 100)
    protected String name;
    @Version
    @Column(name = "VERSION")
    protected Integer version;

    public Person() {
    }

    public Person(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public Integer getCdPerson() {
        return this.cdPerson;
    }

    public void setCdPerson(Integer cdPerson) {
        this.cdPerson = cdPerson;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }
}
