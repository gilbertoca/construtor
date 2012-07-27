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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class BasicApp implements Runnable {

    JFrame mainFrame;
    JLabel label;

    public void run() {
        mainFrame = new JFrame("BasicApp");
        label = new JLabel("Hello, world!");
        label.setFont(new Font("SansSerif", Font.PLAIN, 22));
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(false);
                // Perform any other operations you might need
                // before exit.
                System.exit(0);
            }
        });
        mainFrame.add(label);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Runnable app = new BasicApp();
        try {
            SwingUtilities.invokeAndWait(app);
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}