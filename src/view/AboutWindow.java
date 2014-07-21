package view;

import controller.ApplicationController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author LHG
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
        aboutLabel.setFont( ApplicationController.defaultTitleFont );
        aboutLabel.setForeground( Color.BLACK );
        
        developersLabel.setText( "Developed by:" );
        developersLabel.setFont( ApplicationController.defaultFont );
        developersLabel.setForeground( Color.BLACK );
        
        galimbertiLabel.setText( "Luiz Galimberti" );
        galimbertiLabel.setFont( ApplicationController.defaultNames );
        galimbertiLabel.setForeground( Color.BLACK );
        
        ivanLabel.setText( "Ivan Lampert" );
        ivanLabel.setFont( ApplicationController.defaultNames );
        ivanLabel.setForeground( Color.BLACK );
        
        alexLabel.setText( "Alex Carvalho" );
        alexLabel.setFont( ApplicationController.defaultNames );
        alexLabel.setForeground( Color.BLACK );
        
        setLayout( new GridBagLayout() );
        
        add( closeButton, 
             new GridBagConstraints( 0, 0, 1, 1, 1.0, 0.0, 
                                     GridBagConstraints.EAST, GridBagConstraints.NONE, 
                                     new Insets( 10, 0, 0, 18 ), 0, 0 ) );
        
        add( aboutLabel, 
             new GridBagConstraints( 0, 1, 1, 1, 0.0, 0.0, 
                                     GridBagConstraints.CENTER, GridBagConstraints.NONE, 
                                     new Insets( 0, 0, 0, 0 ), 0, 0 ) );
        
        add( developersLabel, 
             new GridBagConstraints( 0, 2, 1, 1, 0.0, 0.0, 
                                     GridBagConstraints.CENTER, GridBagConstraints.NONE, 
                                     new Insets( 4, 0, 2, 0 ), 0, 0 ) );
        
        add( galimbertiLabel, 
             new GridBagConstraints( 0, 3, 1, 1, 0.0, 0.0, 
                                     GridBagConstraints.CENTER, GridBagConstraints.NONE, 
                                     new Insets( 0, 0, 0, 0 ), 0, 0 ) );
        
        add( alexLabel, 
             new GridBagConstraints( 0, 4, 1, 1, 0.0, 0.0, 
                                     GridBagConstraints.CENTER, GridBagConstraints.NONE, 
                                     new Insets( 0, 0, 0, 0 ), 0, 0 ) );
        
        add( ivanLabel, 
             new GridBagConstraints( 0, 5, 1, 1, 0.0, 0.0, 
                                     GridBagConstraints.CENTER, GridBagConstraints.NONE, 
                                     new Insets( 0, 0, 2, 0 ), 0, 0 ) );
        
        closeButton.addMouseListener( new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
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
    
    private CloseButton closeButton = new CloseButton();
}
