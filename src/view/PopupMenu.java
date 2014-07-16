/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.IconFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author lhg
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
        
        configItem.setText( "Configurações" );
        configItem.setIcon( IconFactory.getIcon( "/resources/config.png", 20 ) );
        
        add( ticketQueryItem );
        add( configItem );
    }
    
    private JMenuItem ticketQueryItem = new JMenuItem();
    private JMenuItem configItem = new JMenuItem();
}
