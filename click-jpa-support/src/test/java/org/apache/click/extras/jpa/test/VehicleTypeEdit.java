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

package org.apache.click.extras.jpa.test;

import org.apache.click.Page;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.extras.jpa.JpaForm;

/**
 *
 * @author Gilberto Caetano de Andrade
 */
public class VehicleTypeEdit extends Page {

   private JpaForm form = new JpaForm("form", VehicleType.class);

    public VehicleTypeEdit() {
        form.add(new TextField("vType"));
        form.add(new TextField("manufacturer"));
        form.add(new TextField("model"));

        form.add(new Submit("ok", "   OK   ", this, "onOkClicked"));
        form.add(new Submit("cancel", this, "onCancelClicked"));

        form.setButtonAlign("right");
        addControl(form);
    }

    public void setVehicleType(VehicleType vehicleType) {
        form.setValueObject(vehicleType);
    }

    public boolean onOkClicked() {
        if (form.isValid()) {
           if (form.saveChanges()) {
               setRedirect("vehicletype-list.htm");
           }
        }
        return true;
    }

    public boolean onCancelClicked() {
        setRedirect("vehicletype-list.htm");
        return false;
    }
}