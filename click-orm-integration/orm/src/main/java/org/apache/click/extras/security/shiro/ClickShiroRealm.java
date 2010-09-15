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
package org.apache.click.extras.security.shiro;
import org.apache.click.extras.security.jpa.model.Role;
import org.apache.click.extras.security.jpa.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class ClickShiroRealm extends AuthorizingRealm {
    private IUserService userService;

    public ClickShiroRealm() {
        setName("ClickShiroRealm"); //This name must match the name in the User class's getPrincipals() method
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userService.getUserByName(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
        } else {
            return null;
        }
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId = (Long) principals.fromRealm(getName()).iterator().next();
        User user = userService.find(userId);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for (Role role : user.getRoles()) {
                info.addRole(role.getName());
                info.addStringPermissions(role.getPermissions());
            }
            return info;
        } else {
            return null;
        }
    }

    public IUserService getUserService() {
        return this.userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    
}
