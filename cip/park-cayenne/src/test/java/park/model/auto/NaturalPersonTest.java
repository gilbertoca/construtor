/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package park.model.auto;

import java.util.List;
import park.model.Person;
import org.apache.cayenne.query.SelectQuery;
import java.util.Arrays;
import park.model.LegalEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import park.model.NaturalPerson;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.ObjectContext;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gilberto
 */
public class NaturalPersonTest {

    public NaturalPersonTest() {
    }

    @Test
    public void testCreateAndList() throws ParseException {
        ObjectContext c = DataContext.createDataContext();

        NaturalPerson nP = c.newObject(NaturalPerson.class);
        nP.setName("NATURAL_PERSON1005");
        nP.setAddress("ADDRESS1005");
        nP.setDtBirth(new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1974"));
        nP.setLegalDocument("LEGAL_DOCUMENT1005");

        LegalEntity lP = c.newObject(LegalEntity.class);
        lP.setName("LEGALENTITY2");
        lP.setAddress("ADDRESS2");
        lP.setDtFoundation(new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1974"));
        lP.setTaxpayersId("TAXPAYERS1002");

        c.commitChanges();

        // before testing select, let's remove objects from memory
        c.invalidateObjects(Arrays.asList(nP, lP));

        SelectQuery q = new SelectQuery(Person.class);
        List<Person> results = c.performQuery(q);

        for (Person result : results) {

            String attribute = null;
            if (result instanceof NaturalPerson) {
                attribute = ((NaturalPerson) result).getNaturalPerson().getName();
            } else {
                attribute = ((LegalEntity) result).getLegalEntity().getName();
            }

            System.out.println(String.format("ID: %s, type: %s, attribute: %s",
                    result.getObjectId(), result.getClass().getName(),
                    attribute));
        }
    }
}
