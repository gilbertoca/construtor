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
package com.google.constructor.extras.security.examples.secure;

import java.util.List;
import org.apache.click.Page;
import org.apache.click.control.Column;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import com.google.constructor.extras.security.shiro.jpa.UserJPAService;

public class UserView extends Page {

    public Table userTable = new Table();
    private UserJPAService userSerice = new UserJPAService();

    public UserView() {
        userTable.setClass(Table.CLASS_ITS);
        userTable.setPageSize(4);
        userTable.setShowBanner(true);
        userTable.setSortable(true);

        userTable.addColumn(new Column("username"));
        userTable.addColumn(new Column("password"));

        Column column = new Column("email");
        column.setAutolink(true);
        column.setSortable(false);
        userTable.addColumn(column);

        // Set data provider to populate the userTable row list from
        userTable.setDataProvider(new DataProvider() {

            public List getData() {
                return (List) userSerice.getAll();
            }
        });
    }
    // ----------------------------------------------------- Event Handlers
}
