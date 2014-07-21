package controller;

import java.net.ServerSocket;
import javax.swing.UIManager;
import view.TrayIcon;

/**
 *
 * @author LHG
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
            if ( System.getProperty( "os.name" ).startsWith( "Windows" ) )
            {
                UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );

                UIManager.put( "swing.boldMetal", Boolean.FALSE );
            }

            TrayIcon.getInstance();
            
            ApplicationController.getInstance().showDialog();
        }
        
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}