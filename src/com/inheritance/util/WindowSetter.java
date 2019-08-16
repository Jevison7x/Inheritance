/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author 123
 */
public class WindowSetter
{

    public static void centraliedWindow(Window window)
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = window.getSize().width;
        int h = window.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        window.setLocation(x, y);
    }

    public static void setNimbusLookAndFeel(Window parentComponent)
    {
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException xcp)
        {
            JOptionPane.showMessageDialog(parentComponent, xcp.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            xcp.printStackTrace(System.err);
        }
    }

    public static void setWindowsLookAndFeel(Window parentComponent)
    {
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException xcp)
        {
            JOptionPane.showMessageDialog(parentComponent, xcp.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            xcp.printStackTrace(System.err);
        }
    }
}
