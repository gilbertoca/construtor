/*
 * Copyright 2011 gilberto.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package park.web.page;

import javax.persistence.EntityManager;
import org.apache.click.Page;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.IntegerField;
import park.model.Parking;
import park.service.util.EntityManagerContext;

/**
 *
 * @author gilberto
 */
public class EditParking extends BorderPage {

    private static final long serialVersionUID = 1L;
    private Form form = new Form("form");
    /**
     * Used to manage the data model state. On Get method we set it to false(update)
     * and when posting - OnClick method - we set i to true(insert)
     * isNew(false)=update, otherwise insert
     */
    protected HiddenField isNewField = new HiddenField("isNew", Boolean.class);
    /** The source track */
    protected HiddenField referrerField = new HiddenField("referrer", String.class);
    /** The data model ID, used to get data from and to database */
    protected HiddenField idField = new HiddenField("id", Long.class);
    /** Bindable variables(ID, used on the Get method) can automatically have their value set by request parameters */
    public Long id;
    /** Bindable variables(used to track where the page was requested) can automatically have their value set by request parameters */
    public String referrer;
    
    private EntityManager em = EntityManagerContext.getEntityManager();

    // Constructor -----------------------------------------------------------
    public EditParking() {
        System.out.println("\n EditParking() method \n");

        getModel().put("title", getMessage("editParking.title"));
        getModel().put("heading", getMessage("editParking.heading"));
        getModel().put("menu", "userMenu");

        addControl(form);
        form.add(referrerField);
        form.add(idField);

        //Grouping specific settings
        FieldSet fieldSet = new FieldSet("parkingFieldSet");
        form.add(fieldSet);

        TextField addressField = new TextField("address", true);
        addressField.setMinLength(5);
        addressField.setFocus(true);
        fieldSet.add(addressField);
        IntegerField parkingSpaceField = new IntegerField("parkingSpace", true);
        fieldSet.add(parkingSpaceField);

        form.add(new Submit("okBt", this, "onOkClick"));
        form.add(new Submit("cancelBt", this, "onCancelClick"));
    }

  // Event Handlers ---------------------------------------------------------

    /**
     * When page is first displayed on the GET request.
     *
     * @see Page#onGet()
     */
    @Override
    public void onGet() {
        System.out.println("\n onGet() method \n");
        if (id != null) {
            Parking parking = em.find(Parking.class, id);
            if (parking != null) {
                // Copy parking data to form. The idField value will be set by
                // this call
                form.copyFrom(parking);
                //id parameter of the page is NOT null, then isNew=false
                isNewField.setValueObject(false);
                //it is the PK, here we can't change it.
                idField.setReadonly(true);
            }
        } else {
            //id parameter of the page is null, then isNew=true
            isNewField.setValueObject(true);
        }

        if (referrer != null) {
            // Set referrerField HiddenField to bound referrer field
            referrerField.setValue(referrer);
        }
    }

    public boolean onOkClick() throws Exception {
        System.out.println("\n onOkClick() method \n");
        //isNew(false)=update, othewise insert
        boolean isNew = false;
        if (form.isValid()) {
            Parking parking = null;
            //local variable, don't confuse it with the public id parameter of the page
            Long _id = (Long) idField.getValueObject();
            if (_id != null) {
                parking = em.find(Parking.class, id);
            } else {
                isNew = true;
                parking = new Parking();
            }

            form.copyTo(parking);
            //We need transation
            try {
                em.getTransaction().begin();
                if (isNew) {
                    em.persist(parking);
                } else {
                    em.merge(parking);
                }
                em.getTransaction().commit();
            } catch (Exception ex) {
                try {
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
                throw ex;
            }

            //The referrerField HiddenField was set on GET request
            String _referrer = referrerField.getValue();
            if (_referrer != null) {
                setRedirect(_referrer);
            } else {
                setRedirect(ViewParking.class);
            }

            return true;

        } else {
            return true;
        }
    }

    public boolean onCancelClick() {
        System.out.println("\n onCancelClick() method \n");
        //The referrerField HiddenField was set on GET request
        String _referrer = referrerField.getValue();
        if (_referrer != null) {
            setRedirect(_referrer);
        } else {
            setRedirect(ViewParking.class);
        }
        return true;
    }

    @Override
    public void onDestroy() {
        System.out.println("\n onDestroy() method \n");
        super.onDestroy();
        if (em.isOpen()) {
            em.close();
        }
    }
}

