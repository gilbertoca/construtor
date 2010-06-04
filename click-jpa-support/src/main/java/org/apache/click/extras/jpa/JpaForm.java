/*
 * Copyright 2004-2006 Takashi Okamoto, Malcolm A. Edgar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.click.extras.jpa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.click.control.Field;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;

import org.apache.commons.lang.StringUtils;

/**
 * Provides JPA data aware Form control: &nbsp; &lt;form method='POST'&gt;.
 *
 * <table class='htmlHeader' cellspacing='10'>
 * <tr>
 * <td>
 *
 * <table class='fields'>
 * <tr>
 * <td align='left'><b><label>First Name:</label></b></td>
 * <td align='left'><input type='text' name='name' value='' size='20' /></td>
 * </tr>
 * <tr>
 * <td align='left'><label>Middle Names:</label></td>
 * <td align='left'><input type='text' name='name' value='' size='20' /></td>
 * </tr>
 * <tr>
 * <td align='left'><b><label>Family Name:</label></b></td>
 * <td align='left'><input type='text' name='name' value='' size='20' /></td>
 * </tr>
 * </table>
 * <table class="buttons" align='right'>
 * <tr><td>
 * <input type='submit' name='ok' value='   OK   '/>&nbsp;<input type='submit' name='cancel' value='Cancel'/>
 * </td></tr>
 * </table>
 *
 * </td>
 * </tr>
 * </table>
 *
 * <a href="http://www.hibernate.org/">JPA</a> is an Object Relational
 * Mapping (ORM) framework. The HibernatteForm supports creating (inserting) and
 * saving (updating) POJO instances. This form will automatically apply the
 * given objects property required validation constraints to the form fields.
 * <p/>
 * The HibernatteForm uses the thread local <tt>Session</tt> obtained via
 * <tt>EntityManagerContext.getSession()</tt> for all object for persistence
 * operations. To use an alternative Session source override set the forms
 * getSession() method.
 * <p/>
 * The example below provides a <tt>User</tt> data object creation
 * and editing page. To edit an existing user object, the object is passed
 * to the page as a request parameter. Otherwise a new user object will
 * be created when {@link #saveChanges()} is called.
 *
 * <pre class="codeJava">
 * <span class="kw">public class</span> UserEdit <span class="kw">extends</span> Page {
 *
 *   <span class="kw">private</span> JpaForm form = <span class="kw">new</span> JpaForm(<span class="st">"form"</span>, User.<span class="kw">class</span>);
 *
 *    <span class="kw">public</span> UserEdit() {
 *        form.add(<span class="kw">new</span> TextField(<span class="st">"firstName"</span>);
 *        form.add(<span class="kw">new</span> TextField(<span class="st">"middleNames"</span>);
 *        form.add(<span class="kw">new</span> TextField(<span class="st">"FamilyName"</span>);
 *
 *        form.add(<span class="kw">new</span> Submit(<span class="st">"ok"</span>, <span class="st">"   OK   "</span>, <span class="kw">this</span>, <span class="st">"onOkClicked"</span>);
 *        form.add(<span class="kw">new</span> Submit(<span class="st">"cancel"</span>, <span class="kw">this</span>, <span class="st">"onCancelClicked"</span>);
 *
 *        form.setButtonAlign(<span class="st">"right"</span>);
 *        form.setLabelRequiredPrefix(<span class="st">"&lt;b&gt;"</span>);
 *        form.setLabelRequiredSuffix(<span class="st">"&lt;/b&gt;"</span>);
 *        addControl(form);
 *    }
 *
 *    <span class="kw">public void</span> setUser(User user) {
 *        form.setValueObject(user);
 *    }
 *
 *    <span class="kw">public boolean</span> onOkClicked() {
 *        <span class="kw">if</span> (form.isValid()) {
 *           <span class="kw">if</span> (form.saveChanges()) {
 *               setRedirect(<span class="st">"user-list.htm"</span>);
 *           }
 *        }
 *        <span class="kw">return true</span>;
 *    }
 *
 *    <span class="kw">public boolean</span> onCancelClicked() {
 *        setRedirect(<span class="st">"user-list.htm"</span>);
 *        <span class="kw">return false</span>;
 *    }
 * } </pre>
 *
 * @see EntityManagerContext
 * @see EntityManagerFilter
 *
 * @author Takashi Okamoto
 * @author Malcolm Edgar
 */
public class JpaForm extends Form {

    private static final long serialVersionUID = -7134198516606088333L;
    /** The form value object classname parameter name. */
    protected static final String FO_CLASS = "FO_CLASS";
    /** The form value object id parameter name. */
    protected static final String FO_ID = "FO_ID";
    // ----------------------------------------------------- Instance Variables
    /** The value object class name hidden field. */
    protected HiddenField classField;
    /** The value object identifier hidden field. */
    protected HiddenField oidField;
    /** The EntityManager. */
    protected EntityManager entityManager;
    /** The EntityManagerFactory. */
    protected EntityManagerFactory entityManagerFactory;
    /**
     * The flag specifying that object validation meta data has been applied to
     * the form fields.
     */
    protected boolean metaDataApplied = false;

    // ----------------------------------------------------------- Constructors
    /**
     * Create a new JpaForm with the given form name and value object
     * class.
     *
     * @param name the form name
     * @param valueClass the value object class
     */
    public JpaForm(String name, Class valueClass) {
        super(name);

        if (valueClass == null) {
            throw new IllegalArgumentException("Null valueClass parameter");
        }

        classField = new HiddenField(FO_CLASS, String.class);
        classField.setValue(valueClass.getName());
        add(classField);

        /*
        String classname = getClassname(valueClass);

        ClassMetadata classMetadata =
        ((HibernateEntityManagerFactory)getEntityManagerFactory()).getSessionFactory().getClassMetadata(classname);

        Type identifierType = classMetadata.getIdentifierType();
        oidField = new HiddenField(FO_ID, identifierType.getReturnedClass());
        add(oidField);
         */

        Metamodel classMetadata = getEntityManager().getMetamodel();
        EntityType entityType = classMetadata.entity(valueClass);
        //SingularAttribute type = entityType.getId(entityType.getIdType().getClass());
        //Whether the identifiable type has a single id attribute.
        if (entityType.hasSingleIdAttribute()) {
            oidField = new HiddenField(FO_ID, entityType.getIdType().getJavaType());
        } else {
            throw new IllegalArgumentException("The identifiable type is idclass attribute, the form won't work!");
        }
        add(oidField);

    }

    // --------------------------------------------------------- Public Methods
    /**
     * Return the form <tt>EntityManager</tt>. If form EntityManager is not
     * defined this method will obtain a EntityManager from the
     * {@link EntityManagerContext}.
     * <p/>
     * Applications using alternative <tt>EntityManager</tt> sources should
     * set the form's session using the {@link #setEntityManager(EntityManager)} method.
     *
     * @return the form EntityManager
     */
    public EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = EntityManagerContext.getEntityManager();
        }
        return entityManager;
    }

    /**
     * Set the user's <tt>EntityManager</tt>.
     *
     * @param entityManager the user's EntityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Return the application <tt>EntityManagerFactory</tt>.
     * If EntiyManagerFactory is not defined this method will obtain the
     * EntityManagerFactory from the {@link EntityManagerContext}.
     * <p/>
     * Applications using an alternative <tt>EntityManagerFactory</tt>
     * sources should set the form's EntityManagerFactory using the
     * {@link #setEntityManagerFactory(EntityManagerFactory)} method.
     *
     * @return the user's EntityManagerFactory
     */
    public EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = EntityManagerContext.getEntityManagerFactory();
        }
        return entityManagerFactory;
    }

    /**
     * Set the form <tt>EntityManagerFactory</tt>.
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Return a JPA value object from the form with the form field values
     * copied into the object's properties.
     *
     * @return the JPA object from the form with the form field values
     * applied to the object properties.
     */
    public Object getValueObject() {
        if (StringUtils.isNotBlank(classField.getValue())) {
            try {
                Class valueClass = Class.forName(classField.getValue());

                Serializable oid = (Serializable) oidField.getValueObject();

                Object valueObject = null;
                if (oid != null && !oid.equals("")) {
                    valueObject = getEntityManager().find(valueClass, oid);
                } else {
                    valueObject = valueClass.newInstance();
                }

                copyTo(valueObject);

                return valueObject;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * Set the given JPA value object in the form, copying the object's
     * properties into the form field values.
     *
     * @param valueObject the JPA value object to set
     */
    public void setValueObject(Object valueObject) {
        if (valueObject != null) {
            /*
            String classname = getClassname(valueObject.getClass());
            Metamodel classMetadata =
            ((HibernateEntityManagerFactory) getEntityManagerFactory()).getSessionFactory().getMetamodel(classname);

            Object identifier = classMetadata.getIdentifier(valueObject, EntityMode.POJO);
             */
            Object identifier = getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(valueObject);
            oidField.setValueObject(identifier);

            copyFrom(valueObject);
        }
    }

    /**
     * Load form value from entity.
     * 
     * @param id Primary key object.
     */
    public void loadEntity(Object id) {
        Class valueClass;
        try {
            valueClass = Class.forName(classField.getValue());
            Object entity = getEntityManager().find(valueClass, id);
            setValueObject(entity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save or update the object to the database and return true.
     * <p/>
     * If no object is added to the form using <tt>setValueObject()</tt>
     * then this method will: <ul>
     * <li>create a new instance of the Class</li>
     * <li>copy the form's field values to the objects properties</li>
     * <li>save the new object to the database</li>
     * </ul>
     * <p/>
     * If an existing persistent object is added to the form using
     * <tt>setValueObject()</tt> then this method will: <ul>
     * <li>load the persistent object record from the database</li>
     * <li>copy the form's field values to the objects properties</li>
     * <li>update the object in the database</li>
     * </ul>
     *
     * @return true if the object was saved or false otherwise
     */
    public boolean saveChanges() {
        Object valueObject = getValueObject();

        EntityManager entityManager = getEntityManager();
        entityManager.merge(valueObject);

        return true;
    }

    /**
     * This method applies the object meta data to the form fields and then
     * invokes the <tt>super.onProcess()</tt> method.
     *
     * @see Form#onProcess()
     *
     * @return true to continue Page event processing or false otherwise
     */
    public boolean onProcess() {
        applyMetaData();
        return super.onProcess();
    }

    /**
     * This method applies the object meta data to the form fields and then
     * invokes the <tt>super.toString()</tt> method.
     *
     * @see Form#toString()
     *
     * @return the HTML string representation of the form
     */
    public String toString() {
        applyMetaData();
        return super.toString();
    }

    // ------------------------------------------------------ Protected Methods
    /**
     * Applies the <tt>ClassMetadata</tt> validation database meta data to the
     * form fields.
     * <p/>
     * The field validation attributes include:
     * <ul>
     * <li>required - is a mandatory field and cannot be null</li>
     * </ul>
     */
    protected void applyMetaData() {
        if (metaDataApplied) {
            return;
        }

        try {
            Class valueClass = Class.forName(classField.getValue());

            /*
            String classname = getClassname(valueClass);

            Metamodel metadata =
            ((HibernateEntityManagerFactory) getEntityManagerFactory()).getSessionFactory().getMetamodel(classname);

            String[] propertyNames = metadata.getPropertyNames();

            boolean[] propertyNullability = metadata.getPropertyNullability();
             
            for (int i = 0; i < propertyNames.length; i++) {
                Field field = getField(propertyNames[i]);
                if (field != null) {
                    field.setRequired(propertyNullability[i]);
                }
            }
             */
            Metamodel classMetadata = getEntityManager().getMetamodel();
            EntityType entityType = classMetadata.entity(valueClass);

            Set<SingularAttribute> attrs = entityType.getAttributes();

            for (SingularAttribute a : attrs) {
                Field field = getField(a.getName());
                if (field != null) {
                    field.setRequired(a.isOptional());
                }
            }
        } catch (ClassNotFoundException cnfe) {
            throw new RuntimeException(cnfe);
        }

        metaDataApplied = true;
    }

    /**
     * Return the original classname for the given class removing any CGLib
     * proxy information.
     *
     * @param aClass the class to obtain the original name from
     * @return the orignial classname for the given class
     */
    protected String getClassname(Class aClass) {

        String classname = aClass.getName();
        if (classname.indexOf("$$") != -1) {
            classname = classname.substring(0, classname.indexOf("$"));
        }

        return classname;
    }
}
