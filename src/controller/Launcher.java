package controller;

import java.net.ServerSocket;
import javax.swing.UIManager;
import view.TrayDialog;
import view.TrayIcon;

/**
 *
 * @author Galimberti
 */
public class Launcher
{
    private static ServerSocket serverSocket; 
    
    /**
     * main
     * 
     * @param args 
     */
    public static void main( String[] args )
    {
        try 
        {  
            serverSocket = new ServerSocket( 9581 );  
        } 
        
        catch ( Exception e ) 
        {  
            System.exit( 0 );
        }  
        
        try 
        {
            UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
            
            UIManager.put( "swing.boldMetal", Boolean.FALSE );
        
            ApplicationController.getInstance().showDialog();

            TrayIcon.getInstance();

            ConfigurationManager.getInstance();
        }
        
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}