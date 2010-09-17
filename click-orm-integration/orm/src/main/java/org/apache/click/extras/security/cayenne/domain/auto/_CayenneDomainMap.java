package org.apache.click.extras.security.cayenne.domain.auto;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.NamedQuery;
import org.apache.click.extras.security.cayenne.domain.Role;
import org.apache.click.extras.security.cayenne.domain.User;

/**
 * This class was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public class _CayenneDomainMap {

    public static final String ROLE_FIND_BY_ROLE_NAME_QUERYNAME = "Role.findByRoleName";

    public static final String USER_FIND_BY_USER_NAME_QUERYNAME = "User.findByUserName";

    public List<Role> performRole_findByRoleName(ObjectContext context , String name) {
        String[] parameters = {
            "name",
        };

        Object[] values = {
            name,
        };

        return context.performQuery(new NamedQuery("Role.findByRoleName", parameters, values));
    }

    public List<User> performUser_findByUserName(ObjectContext context , String userName) {
        String[] parameters = {
            "userName",
        };

        Object[] values = {
            userName,
        };

        return context.performQuery(new NamedQuery("User.findByUserName", parameters, values));
    }
}