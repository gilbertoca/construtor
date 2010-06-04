/*
 * Copyright 2004-2006 Takashi Okamoto, Malcolm A. Edgar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.click.extras.jpa.test;

import junit.framework.Assert;

public class VehicleTypeEditTest extends BaseClickTestCase {

    public void testVehicleTypeEdit() {
        // Bootstrap the container   
        container.start();

        // Simulate a user requesting the page, HomePage.   
        VehicleTypeEdit page = (VehicleTypeEdit) container.testPage(VehicleTypeEdit.class);

        page.getContext().setRequestAttribute("vType", "CAR");
        page.getContext().setRequestAttribute("manufacturer", "FIAT");
        page.getContext().setRequestAttribute("model", "PALIO");
        // Assert that form submit.
        Assert.assertTrue(page.onOkClicked());

        container.stop();
    }
}
