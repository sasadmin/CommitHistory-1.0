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