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
package park.web.page;

import org.apache.click.Page;

public class BorderPage extends Page {

    private static final long serialVersionUID = 1L;

    // Event Handlers ---------------------------------------------------------

    /**
     * @see org.apache.click.Page#onInit()
     */
    @Override
    public void onInit() {
        super.onInit();
        /*
        MenuFactory menuFactory = new MenuFactory();
        rootMenu = menuFactory.getRootMenu();

        // Add rootMenu to Page control list. Note: rootMenu is removed in Page
        // onDestroy() to ensure rootMenu is not serialized
        addControl(rootMenu);

        */
    }

    /**
     * @see org.apache.click.Page#onDestroy()
     */
    @Override
    public void onDestroy() {
        /*
        // Remove menu for when BorderPage is serialized
        if (rootMenu != null) {
            removeControl(rootMenu);
        }
         */
    }

    // Public Methods ---------------------------------------------------------

    /**
     * Returns the name of the border template: &nbsp; <tt>"/border-template.htm"</tt>
     * <p/>
     * Please note this page is designed for extending by Page subclasses and will
     * not be auto mapped as the template name <tt>"border-template.htm"</tt> does
     * not match the Pages class name <tt>BorderPage</tt>.
     *
     * @see org.apache.click.Page#getTemplate()
     */
    @Override
    public String getTemplate() {
        return "/assets/common/template.htm";
    }

}
