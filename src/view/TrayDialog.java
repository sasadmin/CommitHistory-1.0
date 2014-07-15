/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.ApplicationController;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Commit;

/**
 *
 * @author Galimberti
 */
public class TrayDialog
    extends JDialog
{
    private static TrayDialog defaultInstance;
    
    private TrayDialog()
    {
        initComponents();
    }
    
    public static TrayDialog getInstance()
    {
        if ( defaultInstance == null )
        {
            defaultInstance = new TrayDialog();
        }
        
        return defaultInstance;
    }
    
    public Commit getCommit()
    {
        Commit commit = new Commit();
        
        commit.setTicket( ticketField.getText() );
        commit.setRevision( revisionField.getText() );
        commit.setVersion( versionField.getText() );
        
        return commit;
    }
    
    //Obtain the image URL
    protected static ImageIcon createImage( String path, String description ) 
    {
        URL imageURL = MinimizeButton.class.getResource( path );
        
        if (imageURL == null) 
        {
            System.err.println( "Resource not found: " + path );
            return null;
        } 
        
        else 
        {
            Image img = new ImageIcon( imageURL ).getImage();
            
            return new ImageIcon( img.getScaledInstance( 16, 16, Image.SCALE_SMOOTH ), description );
        }
    }
    
    /**
     * initComponents
     * 
     */
    private void initComponents()
    {
        setModal( false );
        setUndecorated( true );
        setSize( 250, 150 );
        
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        
        int taskBarHeight = scrnSize.height - winSize.height;
        
        setLocation( scrnSize.width - getWidth(), 
                     scrnSize.height - getHeight() - taskBarHeight );
        
        Font defaultFont = new Font( "monospaced", Font.BOLD, 12 );
        
        ticketLabel.setText( "Ticket:" );
        ticketLabel.setFont( defaultFont );
        revisionLabel.setText( "Revisão:" );
        revisionLabel.setFont( defaultFont );
        versionLabel.setText( "Versão:" );
        versionLabel.setFont( defaultFont );
        
        DialogPane pane = new DialogPane();
        
        setLayout( new GridBagLayout() );
        
        add( pane, new GridBagConstraints( 0, 0, 1, 1, 1.0, 1.0, 
                                           GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                                           new Insets( 0,0,0,0 ), 0, 0 ) );
        
        pane.setLayout( new GridBagLayout() );
        
        pane.add( minimizeButton, new GridBagConstraints( 3, 0, 1, 1, 0.0, 0.0, 
                                                          GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, 
                                                          new Insets( 10,10,10,10 ), 0, 0 ) );
        
        pane.add( ticketLabel, new GridBagConstraints( 0, 1, 1, 1, 0.0, 0.0, 
                                                       GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                       new Insets( 0,5,5,0 ), 0, 0 ) );
        
        pane.add( ticketField, new GridBagConstraints( 1, 1, 2, 1, 1.0, 0.0, 
                                                       GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                       new Insets( 0,5,5,25 ), 0, 0 ) );
        
        pane.add( revisionLabel, new GridBagConstraints( 0, 2, 1, 1, 0.0, 0.0, 
                                                         GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                         new Insets( 0,5,5,0 ), 0, 0 ) );
        
        pane.add( revisionField, new GridBagConstraints( 1, 2, 2, 1, 1.0, 0.0, 
                                                         GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                         new Insets( 0,5,5,25 ), 0, 0 ) );
        
        pane.add( versionLabel, new GridBagConstraints( 0, 3, 1, 1, 0.0, 0.0, 
                                                        GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                        new Insets( 0,5,5,0 ), 0, 0 ) );
        
        pane.add( versionField, new GridBagConstraints( 1, 3, 2, 1, 1.0, 0.0, 
                                                        GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                        new Insets( 0,5,5,25 ), 0, 0 ) );
        
        pane.add( buttonsPanel, new GridBagConstraints( 0, 4, 4, 1, 1.0, 1.0, 
                                                        GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                                                        new Insets( 0,0,0,0 ), 0, 0 ) );
        
        buttonsPanel.setOpaque( false );
        
        buttonsPanel.setLayout( new GridBagLayout() );
        
        buttonsPanel.add( saveButton, new GridBagConstraints( 0, 0, 1, 1, 1.0, 0.0, 
                                                                           GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, 
                                                                           new Insets( 10,5,10,10 ), 0, 0 ) );
        
        buttonsPanel.add( new JButton( createImage( "/resources/menu.png", "menu icon" ) ), new GridBagConstraints( 1, 0, 1, 1, 1.0, 0.0, 
                                                                    GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, 
                                                                    new Insets( 10,10,10,5 ), 0, 0 ) );
        
        saveButton.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                ApplicationController.getInstance().save();
            }
        } );
    }
    
    private JPanel buttonsPanel = new JPanel();
    
    private JButton saveButton = new JButton( "Salvar" );
    
    private JLabel ticketLabel = new JLabel();
    private JLabel revisionLabel = new JLabel();
    private JLabel versionLabel = new JLabel();
    
    private JTextField ticketField = new JTextField();
    private JTextField revisionField = new JTextField();
    private JTextField versionField = new JTextField();
    
    private MinimizeButton minimizeButton = new MinimizeButton();
}
