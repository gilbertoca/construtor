package com.google.constructor.cip.shiro.examples;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.click.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class Logout extends Page {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean onSecurityCheck() {
        //get the subject associated with this user
        //request.  If the user has previously logged
        //in during this session then the subject will
        //be the subject authenticated at login
        Subject subject = SecurityUtils.getSubject();

        if (subject != null) {
            //see:  http://incubator.apache.org/shiro/static/current/apidocs/org/apache/shiro/subject/Subject.html#logout()
            subject.logout();
        }

        getContext().getSession().invalidate();
        setRedirect(Login.class);
        return false;
    }

}