/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.swing.UIManager;
import view.TrayDialog;
import view.TrayIcon;

/**
 *
 * @author Galimberti
 */
public class Launcher
{
    public static void main( String[] args )
    {
        try 
        {
            UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
        }
        
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        
        UIManager.put( "swing.boldMetal", Boolean.FALSE );
        
        TrayDialog window = TrayDialog.getInstance();
        
        window.setVisible( true );
        
        TrayIcon.getInstance();
    }
}
