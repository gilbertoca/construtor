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
    public static final String EMPLOYEE_PROPERTY = "employee";

    public static final String ID_PK_COLUMN = "ID";

    public void setDtBirth(Date dtBirth) {
        writeProperty(DT_BIRTH_PROPERTY, dtBirth);
    }
    public Date getDtBirth() {
        return (Date)readProperty(DT_BIRTH_PROPERTY);
    }

    public void setLegalDocument(String legalDocument) {
        writeProperty(LEGAL_DOCUMENT_PROPERTY, legalDocument);
    }
    public String getLegalDocument() {
        return (String)readProperty(LEGAL_DOCUMENT_PROPERTY);
    }


    public Employee getEmployee() {
        return (Employee)readProperty(EMPLOYEE_PROPERTY);
    }


}
