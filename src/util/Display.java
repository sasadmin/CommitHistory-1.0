/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.awt.TrayIcon;

/**
 *
 * @author Usuario
 */
public class Display 
{
    /**
     * Display
     * 
     */
    private Display()
    {
    }
    
    /**
     * alert
     * 
     * @param alert 
     */
    public static void alert( String alert )
    {
        displayMessage( alert, TrayIcon.MessageType.WARNING );
    }
    
    /**
     * info
     * 
     * @param info 
     */
    public static void info( String info )
    {
        displayMessage( info, TrayIcon.MessageType.INFO );
    }
    
    /**
     * exception
     * 
     * @param exception 
     */
    public static void exception( String exception )
    {
        displayMessage( exception, TrayIcon.MessageType.ERROR );
    }
    
    /**
     * displayMessage
     * 
     * @param message
     * @param type 
     */
    private static void displayMessage( String message, TrayIcon.MessageType type )
    {
        view.TrayIcon.getInstance().showMessage( message, type );
    }
}
