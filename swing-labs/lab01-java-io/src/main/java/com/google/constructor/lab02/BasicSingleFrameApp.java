/*
 * Copyright 2012 gilberto.andrade.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.constructor.lab02;

import java.awt.Font;
import javax.swing.JLabel;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 *
 * @author gilberto.andrade
 */
public class BasicSingleFrameApp extends SingleFrameApplication {
    JLabel label;

    @Override
    protected void startup() {
        getMainFrame().setTitle("BasicSingleFrameApp");
        label = new JLabel("Hello, world!");
        label.setFont(new Font("SansSerif", Font.PLAIN, 22));
        show(label);
    }

    public static void main(String[] args) {
        Application.launch(BasicSingleFrameApp.class, args);
    }

}
