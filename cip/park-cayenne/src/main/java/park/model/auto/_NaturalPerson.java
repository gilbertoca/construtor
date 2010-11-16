package park.model.auto;

import java.util.Date;

import park.model.Employee;
import park.model.Person;

/**
 * Class _NaturalPerson was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _NaturalPerson extends Person {

    public static final String DT_BIRTH_PROPERTY = "dtBirth";
    public static final String LEGAL_DOCUMENT_PROPERTY = "legalDocument";
    public static final String EMPLOYEE_ROLE_PROPERTY = "employeeRole";
    public static final String PERSON_PROPERTY = "person";

    public static final String PERSON_ID_PK_COLUMN = "PERSON_ID";

    public void setDtBirth(Date dtBirth) {
        writeProperty("dtBirth", dtBirth);
    }
    public Date getDtBirth() {
        return (Date)readProperty("dtBirth");
    }

    public void setLegalDocument(String legalDocument) {
        writeProperty("legalDocument", legalDocument);
    }
    public String getLegalDocument() {
        return (String)readProperty("legalDocument");
    }

    public void setEmployeeRole(Employee employeeRole) {
        setToOneTarget("employeeRole", employeeRole, true);
    }

    public Employee getEmployeeRole() {
        return (Employee)readProperty("employeeRole");
    }


    public void setPerson(Person person) {
        setToOneTarget("person", person, true);
    }

    public Person getPerson() {
        return (Person)readProperty("person");
    }


}