package util;

import java.awt.TrayIcon;

/**
 *
 * @author IL
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
