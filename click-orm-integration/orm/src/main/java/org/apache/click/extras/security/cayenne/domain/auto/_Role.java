package org.apache.click.extras.security.cayenne.domain.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.click.extras.security.cayenne.domain.RolePermission;
import org.apache.click.extras.security.cayenne.domain.UserRole;

/**
 * Class _Role was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Role extends CayenneDataObject {

    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String ID_PROPERTY = "id";
    public static final String NAME_PROPERTY = "name";
    public static final String PERMISSIONS_PROPERTY = "permissions";
    public static final String USERS_PROPERTY = "users";

    public static final String ID_PK_COLUMN = "ID";

    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }

    public void setId(Long id) {
        writeProperty("id", id);
    }
    public Long getId() {
        return (Long)readProperty("id");
    }

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

    public void addToPermissions(RolePermission obj) {
        addToManyTarget("permissions", obj, true);
    }
    public void removeFromPermissions(RolePermission obj) {
        removeToManyTarget("permissions", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<RolePermission> getPermissions() {
        return (List<RolePermission>)readProperty("permissions");
    }


    public void addToUsers(UserRole obj) {
        addToManyTarget("users", obj, true);
    }
    public void removeFromUsers(UserRole obj) {
        removeToManyTarget("users", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<UserRole> getUsers() {
        return (List<UserRole>)readProperty("users");
    }


}
