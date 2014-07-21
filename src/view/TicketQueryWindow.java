/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ApplicationController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Galimberti
 */
public class TicketQueryWindow
        extends DefaultWindow
{
    /**
     * 
     */
    public TicketQueryWindow()
    {
        initComponents();
    }

    /**
     * initComponents
     * 
     */
    private void initComponents()
    {
        setSize( new Dimension( 200, 100 ) );
        setPreferredSize( new Dimension( 200, 100 ) );

        ticketLabel.setText( "Ticket:" );
        ticketLabel.setForeground( Color.BLACK );
        ticketLabel.setFont( ApplicationController.defaultFont );

        openButton.setText( "Abrir" );
        openButton.setToolTipText( "Abrir o arquivo de log" );
        openButton.setForeground( Color.BLACK );
        openButton.setFont( ApplicationController.defaultFont );

        copyButton.setText( "Copiar" );
        copyButton.setToolTipText( "Copiar para área de transferência" );
        copyButton.setForeground( Color.BLACK );
        copyButton.setFont( ApplicationController.defaultFont );
        
        ticketField.addFocusListener( new FocusAdapter() 
        {
            @Override
            public void focusGained(FocusEvent e) 
            {
                ( (TicketField) e.getComponent() ).selectAll();
            }
        } );

        setLayout( new GridBagLayout() );

        add( closeButton, new GridBagConstraints( 0, 0, 3, 1, 0.0, 0.0, 
                                                     GridBagConstraints.EAST, GridBagConstraints.NONE, 
                                                     new Insets( 0, 0, 15, 15 ), 0, 0 ) );
        
        add( ticketLabel, new GridBagConstraints( 1, 0, 1, 1, 0.0, 0.0,
                                                  GridBagConstraints.WEST, GridBagConstraints.NONE,
                                                  new Insets( 30, 15, 0, 0 ), 0, 0 ) );

        add( ticketField, new GridBagConstraints( 2, 0, 1, 1, 1.0, 0.0,
                                                  GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                                                  new Insets( 30, 0, 0, 15 ), 0, 0 ) );

        add( openButton, new GridBagConstraints( 1, 1, 1, 1, 0.0, 1.0,
                                               GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE,
                                               new Insets( 0, 10, 10, 0 ), 0, 0 ) );

        add( copyButton, new GridBagConstraints( 2, 1, 1, 1, 1.0, 1.0,
                                                  GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE,
                                                  new Insets( 0, 0, 10, 10 ), 0, 0 ) );

        openButton.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                //TODO
            }
        } );

        copyButton.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                //TODO
            }
        } );
        
        closeButton.addMouseListener( new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
                ApplicationController.getInstance().closeWindow();
            }  
        } );
    }

    private JLabel ticketLabel = new JLabel();
    private TicketField ticketField = new TicketField();

    private JButton openButton = new JButton();
    private JButton copyButton = new JButton();
    
    private CloseButton closeButton = new CloseButton();
}
