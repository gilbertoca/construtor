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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.click.Page;
import org.apache.click.control.Column;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.extras.control.DateField;
import park.model.Employee;
import park.model.Parking;
import park.model.Stay;
import park.model.dto.EmployeeLookUp;
import park.service.util.EntityManagerContext;


/**
 *
 * @author gilberto
 */
public class EditEmployee extends BorderPage {

    private static final long serialVersionUID = 1L;
    private Form form = new Form("form");
    protected Table staysOutgoingtable = new Table("staysOutgoingtable");
    protected Table staysEntrancetable = new Table("staysEntrancetable");
    /**
     * Used to manage the data model state. On Get method we set it to false(update)
     * and when posting - OnClick method - we set i to true(insert)
     * isNew(false)=update, otherwise insert
     */
    protected HiddenField isNewField = new HiddenField("isNew", Boolean.class);
    /** The source track */
    protected HiddenField referrerField = new HiddenField("referrer", String.class);
    /**
     * The data model ID, used to get data from and to database.
     * The default option is declare it as HiddenField, but sometimes - when the user
     * enter the ID data - we use a normal TextField. For example:
     *  default --> protected HiddenField idField = new HiddenField("id", Long.class);
     *  sometimes-> protected TextField idField = new TextField("licensePlate", true);
     *  sometimes-> protected Select idField = new Select("id",true);
     * Don't forget to set i read only when on Update operation.
     */
    public Select idField = new Select("id",true);
    /** Bindable variables(ID, used on the Get method) can automatically have their value set by request parameters */
    public Long id;
    /** Bindable variables(used to track where the page was requested) can automatically have their value set by request parameters */
    public String referrer;
    
    private EntityManager em = EntityManagerContext.getEntityManager();

    // Constructor -----------------------------------------------------------
    public EditEmployee() {
        System.out.println("\n EditEmployee() method \n");

        getModel().put("title", getMessage("editEmployee.title"));
        getModel().put("heading", getMessage("editEmployee.heading"));

        addControl(form);
        form.add(referrerField);

        FieldSet naturalPersonFieldSet = new FieldSet("naturalPersonFieldSet");
        FieldSet employeeFieldSet = new FieldSet("employeeFieldSet");

        //the idField is here - see below
        form.add(naturalPersonFieldSet);
        form.add(employeeFieldSet);
        form.add(isNewField);
        
        idField.setRequired(true);
        idField.setDefaultOption(Option.EMPTY_OPTION);

        naturalPersonFieldSet.add(idField);
        DateField dtAdmissionField = new DateField("dtAdmission", true);
        dtAdmissionField.setFormatPattern(getMessage("date.format"));
        dtAdmissionField.setShowCalendar(false);
        employeeFieldSet.add(dtAdmissionField);
        
        Select parkingSelect = new Select("parkingId");
        parkingSelect.setRequired(true);
        parkingSelect.setDefaultOption(Option.EMPTY_OPTION);
        parkingSelect.setDataProvider(new DataProvider() {

            public List getData() {
                List options = new ArrayList();
                List<Parking> parkings = (List<Parking>) em.createNamedQuery("Parking.findAll").getResultList();
                for (Parking parking : parkings) {
                    options.add(new Option(parking.getId(), parking.getAddress()));
                }
                return options;
            }
        });
        employeeFieldSet.add(parkingSelect);
        //StayOutgoing Table
        staysOutgoingtable.setClass(Table.CLASS_SIMPLE);
        staysOutgoingtable.addColumn(new Column("dtOutgoing"));
        staysOutgoingtable.addColumn(new Column("id"));
        employeeFieldSet.add(staysOutgoingtable);
        //StayEntrance Table
        staysEntrancetable.setClass(Table.CLASS_SIMPLE);
        staysEntrancetable.addColumn(new Column("dtEntrance"));
        staysEntrancetable.addColumn(new Column("id"));
        employeeFieldSet.add(staysEntrancetable);

        form.add(new Submit("okBt", this, "onOkClick"));
        form.add(new Submit("cancelBt", this, "onCancelClick"));
    }

    private List getStaysByEmployeeId(Long id, String namedQuery) {
        System.out.println("\n getStaysBy**Id(Long id, String namedQuery) method \n");
        Query queryObject = em.createNamedQuery(namedQuery);
        queryObject.setParameter("employeeId", id);
        return queryObject.getResultList();
    }

    // Event Handlers ---------------------------------------------------------
    @Override
    public void onInit() {
        super.onInit();
        System.out.println("\n onInit() method \n");
        // Set the id on the table's controlLink. If you view the
        // output rendered by Table note that the id parameter
        // is rendered for each Paging and Sorting link.
        staysOutgoingtable.getControlLink().setParameter("id", id);
        // Set data provider to populate the table row list from
        staysOutgoingtable.setDataProvider(new DataProvider<Stay>() {
            public List<Stay> getData() {
                return (List<Stay>) getStaysByEmployeeId(id,"Stay.findByEmployeeOutgoingId");
            }
        });
        staysEntrancetable.getControlLink().setParameter("id", id);
        // Set data provider to populate the table row list from
        staysEntrancetable.setDataProvider(new DataProvider<Stay>() {
            public List<Stay> getData() {
                return (List<Stay>) getStaysByEmployeeId(id,"Stay.findByEmployeeEntranceId");
            }
        });        
        idField.setDataProvider(new DataProvider() {
            String query = id == null? "Employee.lookUpNameNotExists":"Employee.lookUpName";
            public List getData() {
                List options = new ArrayList();
                List<EmployeeLookUp> result =
                        em.createNamedQuery(query, EmployeeLookUp.class).getResultList();
                for (EmployeeLookUp employee : result) {
                    options.add(new Option(employee.getId(), employee.getEmployeeName()));
                }
                return options;
            }
        });
        
    }

    /**
     * When page is first displayed on the GET request.
     *
     * @see Page#onGet()
     */
    @Override
    public void onGet() {
        System.out.println("\n onGet() method \n");
        if (id != null) {
            Employee employee = em.find(Employee.class, id);
            if (employee != null) {
                // Copy employee data to form. The idField value will be set by
                // this call
                form.copyFrom(employee);
                //id parameter of the page is NOT null, then isNew=false
                isNewField.setValueObject(false);
                //it is the PK, here we can't change it, whick in this case idField
                //is not a HiddenField
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
        
        if (form.isValid()) {
            Employee employee = null;
            //local variable, don't confuse it with the public id parameter of the page
            Long _id = Long.parseLong(idField.getValue());
            //isNew(false)=update, othewise insert
            Boolean _isNew = (Boolean) isNewField.getValueObject();

            if (_isNew) {
                employee = new Employee();
            } else {
                employee = em.find(Employee.class, _id);
            }

            form.copyTo(employee);
            //We need transation
            try {
                em.getTransaction().begin();
                if (_isNew) {
                    em.persist(employee);
                } else {
                    em.merge(employee);
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

            //The referrerField HiddenField was set on GET request (onGet() method)
            String _referrer = referrerField.getValue();
            if (_referrer != null) {
                setRedirect(_referrer);
            } else {
                setRedirect(ViewEmployee.class);
            }

            return true;

        } else {
            return true;
        }
    }

    public boolean onCancelClick() {
        System.out.println("\n onCancelClick() method \n");
        //The referrerField HiddenField was set on GET request (onGet() method)
        String _referrer = referrerField.getValue();
        if (_referrer != null) {
            setRedirect(_referrer);
        } else {
            setRedirect(ViewEmployee.class);
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
