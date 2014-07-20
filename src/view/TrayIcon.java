package view;

import controller.ApplicationController;
import controller.IconFactory;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author LHG
 */
public class TrayIcon
{
    private static TrayIcon defaultInstance;

    /**
     * TryIcon
     * 
     */
    private TrayIcon()
    {
        initComponents();
    }

    /**
     * getInstance
     * 
     * @return TrayIcon
     */
    public static TrayIcon getInstance()
    {
        if ( defaultInstance == null )
        {
            defaultInstance = new TrayIcon();
        }

        return defaultInstance;
    }

    /**
     * showMessage<br>
     * utilizar util.Display para exibição de mensagens
     * @param message String
     * @param type
     */
    public void showMessage( String message, MessageType type )
    {
        trayIcon.displayMessage( ApplicationController.applicationName,
                                 message,
                                 type );
    }

    /**
     * updateActions
     * 
     * @param isShowingDialog boolean
     */
    public void updateActions( boolean isShowingDialog )
    {
        openItem.setEnabled( !isShowingDialog );
        minimizeItem.setEnabled( isShowingDialog );
        exitItem.setEnabled( true );
    }

    /**
     * initComponents
     * 
     */
    private void initComponents()
    {
        if ( !SystemTray.isSupported() )
        {
            System.out.println( "SystemTray is not supported" );
            return;
        }

        final PopupMenu popup = new PopupMenu();
        trayIcon = new java.awt.TrayIcon( IconFactory.getImage( "/resources/tray.png" ) );
        final SystemTray tray = SystemTray.getSystemTray();

        popup.add( aboutItem );
        popup.addSeparator();
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
                    ApplicationController.getInstance().toggleDialog();
                }
            }
        });

        aboutItem.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                ApplicationController.getInstance().toggleDialog( true );
                ApplicationController.getInstance().closeWindow( false );
                ApplicationController.getInstance().openWindow( new AboutWindow() );
            }
        } );

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
                ApplicationController.getInstance().exit();
            }
        } );
    }

    private MenuItem aboutItem = new MenuItem( "Sobre" );
    private MenuItem openItem = new MenuItem( "Abrir" );
    private MenuItem minimizeItem = new MenuItem( "Minimizar" );
    private MenuItem exitItem = new MenuItem( "Fechar" );
        
    private java.awt.TrayIcon trayIcon;
}