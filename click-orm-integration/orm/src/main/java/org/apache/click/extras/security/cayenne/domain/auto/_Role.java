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
    public static final String SHIRO_ROLE_PERMISSIONS_PROPERTY = "shiroRolePermissions";
    public static final String SHIRO_USER_ROLES_PROPERTY = "shiroUserRoles";

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

    public void addToShiroRolePermissions(RolePermission obj) {
        addToManyTarget("shiroRolePermissions", obj, true);
    }
    public void removeFromShiroRolePermissions(RolePermission obj) {
        removeToManyTarget("shiroRolePermissions", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<RolePermission> getShiroRolePermissions() {
        return (List<RolePermission>)readProperty("shiroRolePermissions");
    }


    public void addToShiroUserRoles(UserRole obj) {
        addToManyTarget("shiroUserRoles", obj, true);
    }
    public void removeFromShiroUserRoles(UserRole obj) {
        removeToManyTarget("shiroUserRoles", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<UserRole> getShiroUserRoles() {
        return (List<UserRole>)readProperty("shiroUserRoles");
    }


}
