package view;

import controller.ApplicationController;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class TrayIcon
{

    private static TrayIcon defaultInstance;

    private TrayIcon()
    {
        initComponents();
    }

    public static TrayIcon getInstance()
    {
        if ( defaultInstance == null )
        {
            defaultInstance = new TrayIcon();
        }

        return defaultInstance;
    }

    /**
     * showMessage
     *
     * @param message String
     * @param type
     */
    public void showMessage( String message, MessageType type )
    {
        trayIcon.displayMessage( "Commit History",
                                 message,
                                 type );
    }

    //Obtain the image URL
    protected static Image createImage( String path, String description )
    {
        URL imageURL = TrayIcon.class.getResource( path );

        if ( imageURL == null )
        {
            System.err.println( "Resource not found: " + path );
            return null;
        }

        else
        {
            return ( new ImageIcon( imageURL, description ) ).getImage();
        }
    }
    
    public void updateActions( boolean isShowingDialog )
    {
        openItem.setEnabled( !isShowingDialog );
        minimizeItem.setEnabled( isShowingDialog );
        exitItem.setEnabled( true );
    }

    private void initComponents()
    {
        if ( !SystemTray.isSupported() )
        {
            System.out.println( "SystemTray is not supported" );
            return;
        }

        final PopupMenu popup = new PopupMenu();
        trayIcon = new java.awt.TrayIcon( createImage( "/resources/tray.png", "tray icon" ) );
        final SystemTray tray = SystemTray.getSystemTray();

        popup.add( openItem );
        popup.add( minimizeItem );
        popup.add( exitItem );

        trayIcon.setPopupMenu( popup );
        trayIcon.setToolTip( "Commit History" );
        trayIcon.setImageAutoSize( true );

        try
        {
            tray.add( trayIcon );
        }

        catch ( AWTException e )
        {
            System.out.println( "TrayIcon could not be added." );
            return;
        }
        
        trayIcon.addMouseListener( new MouseAdapter()
        {
            @Override
            public void mouseClicked( MouseEvent e )
            {
                if ( e.getButton() == MouseEvent.BUTTON1 )
                {
                    ApplicationController.getInstance().showDialog();
                }
            }
        });

        openItem.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                ApplicationController.getInstance().showDialog();
            }
        } );

        minimizeItem.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                ApplicationController.getInstance().closeDialog();
            }
        } );

        exitItem.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                tray.remove( trayIcon );
                System.exit( 0 );
            }
        } );
    }

    private MenuItem openItem = new MenuItem( "Abrir" );
    private MenuItem minimizeItem = new MenuItem( "Minimizar" );
    private MenuItem exitItem = new MenuItem( "Fechar" );
        
    private java.awt.TrayIcon trayIcon;
}
