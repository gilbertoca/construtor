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
package com.google.constructor.cip.shiro.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.constructor.cip.orm.jpa.BaseJPAService;
import com.google.constructor.cip.security.jpa.model.User;
/**
 * This class interacts with EntityManagerFactory's EntityManager 
 * retrieve User objects.
 **/
public class UserJPAService extends BaseJPAService<User, Long> implements IUserService{

    public UserJPAService() {
        super(User.class);
    }
    public User getUserByName(String userName) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", userName);
        List<User> users = (List<User>) findByNamedQuery(User.FIND_BY_USER_NAME, map);
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

}
