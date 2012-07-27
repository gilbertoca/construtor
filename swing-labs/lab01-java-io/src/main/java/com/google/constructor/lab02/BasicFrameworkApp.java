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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.jdesktop.application.Application;

/**
 *
 * @author gilberto.andrade
 */
public class BasicFrameworkApp extends Application {
    private JFrame mainFrame;
    private JLabel label;

    @Override
    protected void startup() {
        mainFrame = new JFrame("BasicFrameworkApp");
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(false);
                exit();
            }
        });
        label = new JLabel("Hello, world!");
        mainFrame.add(label);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Application.launch(BasicFrameworkApp.class, args);
    }
}
