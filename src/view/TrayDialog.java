package view;

import controller.ApplicationController;
import controller.IconFactory;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import data.Commit;

/**
 *
 * @author Galimberti
 */
public class TrayDialog
    extends JDialog
{
    /**
     * TrayDialog
     * 
     */
    public TrayDialog()
    {
        initComponents();
    }
    
    /**
     * getCommit
     * 
     * @return Commit
     */
    private Commit obtainInput()
    {
        Commit commit = new Commit();
        
        commit.setTicket( ticketField.getText() );
        commit.setRevision( revisionField.getText() );
        commit.setVersion( versionField.getText() );
        
        return commit;
    }
    
    /**
     * clearInputs
     * 
     */
    public void clearInputs()
    {
        ticketField.setText( "" );
        revisionField.setText( "" );
        versionField.setText( "" );
        
        ticketField.requestFocus();
    }
    
    /**
     * saveCommit
     * 
     */
    private void saveCommit()
    {
        ApplicationController.getInstance().saveCommit( obtainInput() );
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
        
        ticketLabel.setText( "Ticket:" );
        ticketLabel.setFont( ApplicationController.defaultFont );
        revisionLabel.setText( "Revisão:" );
        revisionLabel.setFont( ApplicationController.defaultFont );
        versionLabel.setText( "Versão:" );
        versionLabel.setFont( ApplicationController.defaultFont );
        applicationNameLabel.setText( ApplicationController.applicationName );
        applicationNameLabel.setFont( ApplicationController.defaultTitleFont );
        
        saveButton.setFont( ApplicationController.defaultFont );
        
        setLayout( new GridBagLayout() );
        
        add( pane, new GridBagConstraints( 0, 0, 1, 1, 1.0, 1.0, 
                                           GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                                           new Insets( 0,0,0,0 ), 0, 0 ) );
        
        pane.setLayout( new GridBagLayout() );

        final Insets insets = new Insets( 5, 10, 0, 10 );
        
        pane.add( applicationNameLabel, new GridBagConstraints( 0, 0, 2, 1, 1.0, 0.0, 
                                                          GridBagConstraints.WEST, GridBagConstraints.NONE, 
                                                          insets, 0, 0 ) );
        
        pane.add( minimizeButton, new GridBagConstraints( 2, 0, 1, 1, 0.0, 0.0, 
                                                          GridBagConstraints.CENTER, GridBagConstraints.NONE, 
                                                          insets, 0, 0 ) );
        
        pane.add( ticketLabel, new GridBagConstraints( 0, 1, 1, 1, 0.0, 0.0, 
                                                       GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                       insets, 0, 0 ) );
        
        pane.add( ticketField, new GridBagConstraints( 1, 1, 2, 1, 1.0, 0.0, 
                                                       GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                       insets, 0, 0 ) );
        
        pane.add( revisionLabel, new GridBagConstraints( 0, 2, 1, 1, 0.0, 0.0, 
                                                         GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                         insets, 0, 0 ) );
        
        pane.add( revisionField, new GridBagConstraints( 1, 2, 2, 1, 1.0, 0.0, 
                                                         GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                         insets, 0, 0 ) );
        
        pane.add( versionLabel, new GridBagConstraints( 0, 3, 1, 1, 0.0, 0.0, 
                                                        GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                        insets, 0, 0 ) );
        
        pane.add( versionField, new GridBagConstraints( 1, 3, 2, 1, 1.0, 0.0, 
                                                        GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                                        insets, 0, 0 ) );
        
        pane.add( buttonsPanel, new GridBagConstraints( 0, 4, 4, 1, 1.0, 1.0, 
                                                        GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                                                        insets, 0, 0 ) );
        
        buttonsPanel.setOpaque( false );
        
        buttonsPanel.setLayout( new GridBagLayout() );
        
        buttonsPanel.add( menuButton, new GridBagConstraints( 0, 0, 1, 1, 1.0, 0.0, 
                                                              GridBagConstraints.WEST, GridBagConstraints.NONE, 
                                                              new Insets( 10, 0, 10, 5 ), 0, 0 ) );
        
        buttonsPanel.add( saveButton, new GridBagConstraints( 1, 0, 1, 1, 1.0, 0.0, 
                                                              GridBagConstraints.EAST, GridBagConstraints.NONE, 
                                                              new Insets( 10 ,5, 10, 0 ), 0, 0 ) );
        
        saveButton.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                saveCommit();
            }
        } );
        
        FocusListener focusEvent = new FocusAdapter() 
        {
            @Override
            public void focusGained(FocusEvent e) 
            {
                ( (JTextField) e.getComponent() ).selectAll();
            }
        };
        
        saveButton.setFocusable( false );
        menuButton.setFocusable( false );
        
        ticketField.addFocusListener( focusEvent );
        revisionField.addFocusListener( focusEvent );
        versionField.addFocusListener( focusEvent );
        
        ticketField.addKeyListener( new KeyAdapter() 
        {
            @Override
            public void keyReleased( KeyEvent e ) 
            {
                if ( e.getKeyCode() == KeyEvent.VK_ENTER )
                {
                    revisionField.requestFocus();
                }
            }
        } );
        
        revisionField.addKeyListener( new KeyAdapter() 
        {
            @Override
            public void keyReleased( KeyEvent e ) 
            {
                if ( e.getKeyCode() == KeyEvent.VK_ENTER )
                {
                    versionField.requestFocus();
                }
            }
        } );
        
        versionField.addKeyListener( new KeyAdapter() 
        {
            @Override
            public void keyReleased( KeyEvent e ) 
            {
                if ( e.getKeyCode() == KeyEvent.VK_ENTER )
                {
                    saveCommit();
                }
            }
        } );
    }
   
    private final DialogPane pane = new DialogPane();
    private JPanel buttonsPanel = new JPanel();
    
    private JButton saveButton = new JButton( "Salvar" );
    private JButton menuButton = new JButton( IconFactory.getIcon( "/resources/menu.png", 16 ) );
    
    private JLabel ticketLabel = new JLabel();
    private JLabel revisionLabel = new JLabel();
    private JLabel versionLabel = new JLabel();
    private JLabel applicationNameLabel = new JLabel();
    
    private JTextField ticketField = new JTextField();
    private JTextField revisionField = new JTextField();
    private JTextField versionField = new JTextField();
    
    private MinimizeButton minimizeButton = new MinimizeButton();
}