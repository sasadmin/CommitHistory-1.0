package view;

import controller.ApplicationController;
import controller.IconFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author LHG
 */
public class PopupMenu
    extends JPopupMenu 
{
    public PopupMenu()
    {
        initComponents();
    }
    
    private void initComponents()
    {
        ticketQueryItem.setText( "Consultar Ticket" );
        ticketQueryItem.setIcon( IconFactory.getIcon( "/resources/ticket.png", 20 ) );
        
        textQueryItem.setText( "Consultar Texto" );
        textQueryItem.setIcon( IconFactory.getIcon( "/resources/ticket.png", 20 ) );
        
        configItem.setText( "Configurações" );
        configItem.setIcon( IconFactory.getIcon( "/resources/config.png", 20 ) );
        
        add( ticketQueryItem );
        add( textQueryItem );
        add( configItem );
        
        ticketQueryItem.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                ApplicationController.getInstance().openWindow( new TicketQueryWindow() );
            }
        } );
        
        textQueryItem.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                ApplicationController.getInstance().openWindow( new TextQueryWindow() );
            }
        } );
        
        configItem.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                ApplicationController.getInstance().openWindow( new ConfigurationWindow() );
            }
        } );
    }
    
    private JMenuItem ticketQueryItem = new JMenuItem();
    private JMenuItem textQueryItem = new JMenuItem();
    private JMenuItem configItem = new JMenuItem();
}
