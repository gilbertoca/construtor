
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
package com.google.constructor.cip.shiro.examples;

import com.google.constructor.cip.security.jpa.model.Role;
import com.google.constructor.cip.security.jpa.model.User;
import com.google.constructor.cip.shiro.jpa.RoleJPAService;
import com.google.constructor.cip.shiro.jpa.UserJPAService;
import java.util.ArrayList;
import java.util.List;

public class DataPopulator {
    public UserJPAService userService = new UserJPAService();
    public RoleJPAService roleService = new RoleJPAService();

    public DataPopulator() {
        //Clean any data existent
        userService.delete(userService.getAll());
        roleService.delete(roleService.getAll());

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
