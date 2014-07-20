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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXButton;

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

        okButton.setText( "Buscar" );
        okButton.setForeground( Color.BLACK );
        okButton.setFont( ApplicationController.defaultFont );

        closeButton.setText( "Fechar" );
        closeButton.setForeground( Color.BLACK );
        closeButton.setFont( ApplicationController.defaultFont );

        setLayout( new GridBagLayout() );

        add( ticketLabel, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0,
                                                  GridBagConstraints.WEST, GridBagConstraints.NONE,
                                                  new Insets( 25, 15, 0, 0 ), 0, 0 ) );

        add( ticketField, new GridBagConstraints( 1, 0, 1, 1, 1.0, 0.0,
                                                  GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                                                  new Insets( 25, 0, 0, 15 ), 0, 0 ) );

        add( okButton, new GridBagConstraints( 0, 1, 1, 1, 0.0, 1.0,
                                               GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE,
                                               new Insets( 0, 10, 10, 0 ), 0, 0 ) );

        add( closeButton, new GridBagConstraints( 1, 1, 1, 1, 1.0, 1.0,
                                                  GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE,
                                                  new Insets( 0, 0, 10, 10 ), 0, 0 ) );

        okButton.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                //TODO
            }
        } );

        closeButton.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                ApplicationController.getInstance().closeWindow();
            }
        } );
    }

    private JLabel ticketLabel = new JLabel();
    private JTextField ticketField = new JTextField();

    private JButton okButton = new JButton();
    private JButton closeButton = new JButton();
}
