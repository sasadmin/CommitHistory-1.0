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

/**
 *
 * @author Galimberti
 */
public class AboutWindow
    extends DefaultWindow
{
    /**
     * 
     */
    public AboutWindow()
    {
        initComponents();
    }
    
    /**
     * initComponents
     * 
     */
    private void initComponents()
    {
        setSize( new Dimension( 220, 130 ) );
        setPreferredSize( new Dimension( 220, 130 ) );
        
        aboutLabel.setText( "CommitHistory 1.0" );
        aboutLabel.setFont( ApplicationController.defaultFont );
        aboutLabel.setForeground( Color.BLACK );
        
        developersLabel.setText( "Developed by:" );
        developersLabel.setFont( ApplicationController.defaultFont );
        developersLabel.setForeground( Color.BLACK );
        
        galimbertiLabel.setText( "Luiz Galimberti" );
        galimbertiLabel.setFont( ApplicationController.defaultFont );
        galimbertiLabel.setForeground( Color.BLACK );
        
        ivanLabel.setText( "Ivan Lampert" );
        ivanLabel.setFont( ApplicationController.defaultFont );
        ivanLabel.setForeground( Color.BLACK );
        
        alexLabel.setText( "Alex Carvalho" );
        alexLabel.setFont( ApplicationController.defaultFont );
        alexLabel.setForeground( Color.BLACK );
        
        closeButton.setText( "Fechar" );
        closeButton.setFont( ApplicationController.defaultFont );
        closeButton.setForeground( Color.BLACK );
        
        setLayout( new GridBagLayout() );
        
        add( aboutLabel, new GridBagConstraints( 0, 0, 2, 1, 1.0, 0.0, 
                                                 GridBagConstraints.CENTER, GridBagConstraints.NONE, 
                                                 new Insets( 0, 0, 0, 0 ), 0, 0 ) );
        
        add( developersLabel, new GridBagConstraints( 0, 1, 1, 1, 0.0, 0.0, 
                                                      GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                      new Insets( 10, 10, 0, 0 ), 0, 0 ) );
        
        add( galimbertiLabel, new GridBagConstraints( 1, 1, 2, 1, 1.0, 0.0, 
                                                      GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                      new Insets( 10, 0, 0, 0 ), 0, 0 ) );
        
        add( ivanLabel, new GridBagConstraints( 1, 2, 2, 1, 1.0, 0.0, 
                                                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                new Insets( 0, 0, 0, 0 ), 0, 0 ) );
        
        add( alexLabel, new GridBagConstraints( 1, 3, 2, 1, 1.0, 0.0, 
                                                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                new Insets( 0, 0, 0, 0 ), 0, 0 ) );
        
        add( closeButton, new GridBagConstraints( 0, 4, 2, 1, 1.0, 0.0, 
                                                  GridBagConstraints.CENTER, GridBagConstraints.NONE, 
                                                  new Insets( 10, 0, 0, 0 ), 0, 0 ) );
        
        closeButton.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                ApplicationController.getInstance().closeWindow();
            }
        } );
    }
    
    private JLabel aboutLabel = new JLabel();
    private JLabel developersLabel = new JLabel();
    
    private JLabel galimbertiLabel = new JLabel();
    private JLabel ivanLabel = new JLabel();
    private JLabel alexLabel = new JLabel();
    
    private JButton closeButton = new JButton();
}
