
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.click.extras.security.examples.secure;

import java.util.ArrayList;
import java.util.List;
import org.apache.click.extras.security.shiro.RoleJPAService;
import org.apache.click.extras.security.shiro.UserJPAService;
import org.apache.click.extras.security.jpa.model.Role;
import org.apache.click.extras.security.jpa.model.User;

public class DataPopulator {
    public UserJPAService userService = new UserJPAService();
    public RoleJPAService roleService = new RoleJPAService();

    public DataPopulator() {
    /*it could be BaseCayenneService as well
    <SHIRO_USER ID='-1' USERNAME='bruce@hotmail.com' PASSWORD='bruce' EMAIL='bruce@hotmail.com' VERSION='1'/>
    <SHIRO_USER ID='-2' USERNAME='sue@hotmail.com' PASSWORD='sue12' EMAIL='sue@hotmail.com' VERSION='1'/>
    <SHIRO_USER ID='-3' USERNAME='jack@hotmail.com' PASSWORD='jack1' EMAIL='jack@hotmail.com' VERSION='1'/>
    <SHIRO_ROLE ID='-1' NAME='ADMIN_ROLE' DESCRIPTION='Administrator role (can edit Users)' />
    <SHIRO_ROLE ID='-2' NAME='USER_ROLE' DESCRIPTION='Default role for all Users' />
    <SHIRO_ROLE_PERMISSION ROLE_ID='-1' PERMISSION='admin' />
    <SHIRO_ROLE_PERMISSION ROLE_ID='-1' PERMISSION='secure' />
    <SHIRO_ROLE_PERMISSION ROLE_ID='-2' PERMISSION='none' />
    <SHIRO_USER_ROLE USER_ID='-1' ROLE_ID='-1' />
    <SHIRO_USER_ROLE USER_ID='-1' ROLE_ID='-2' />
    <SHIRO_USER_ROLE USER_ID='-2' ROLE_ID='-2' />
    <SHIRO_USER_ROLE USER_ID='-3' ROLE_ID='-2' />*/
        //User's roles
        Role role1, role2;
        List<Role> roleEntities = new ArrayList<Role>(2);
        role1 = new Role("ADMIN_ROLE", "Administrator role (can edit Users)");
        role1.addPermission("admin");
        role1.addPermission("secure");
        roleEntities.add(role1);
        role2 = new Role("USER_ROLE", "Default role for all Users");
        role2.addPermission("none");
        roleEntities.add(role2);
        roleService.insert(roleEntities);
        
        //Users
        User user1, user2, user3;
        List<User> userEntities = new ArrayList<User>(3);
        user1 = new User("bruce@hotmail.com", "bruce", "bruce@hotmail.com");
        user1.addRole(roleService.getRoleByName(role1.getName()));
        user1.addRole(roleService.getRoleByName(role2.getName()));
        userEntities.add(user1);
        user2 = new User("sue@hotmail.com", "sue12", "sue@hotmail.com");
        user2.addRole(roleService.getRoleByName(role2.getName()));
        userEntities.add(user2);
        user3 = new User("jack@hotmail.com", "jack1", "jack@hotmail.com");
        user3.addRole(roleService.getRoleByName(role2.getName()));
        userEntities.add(user3);
        userService.insert(userEntities);
    }
}
