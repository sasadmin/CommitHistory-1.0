package view;

import controller.ApplicationController;
import controller.ConfigurationManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 *
 * @author LHG
 */
public class ConfigurationWindow
    extends DefaultWindow
{
    public ConfigurationWindow()
    {
        initComponents();
        initConfig();
    }
    
    /**
     * initConfig
     * 
     */
    private void initConfig()
    {
        String path = ConfigurationManager.getInstance().getProperty( "ch.home" );
        
        if ( path != null && !path.isEmpty() )
        {
            folderSelector.setSelectedFolder( new File( path ) );
        }
        
        checkTop.setSelected( Boolean.valueOf( ConfigurationManager.getInstance().getProperty( "ch.top", "true" ) ) );
    }
    
    /**
     * saveConfig
     * 
     */
    private void saveConfig()
    {
        try
        {
            ConfigurationManager.getInstance().setProperty( "ch.home", folderSelector.getSelectedFolder().getAbsolutePath() );
            ConfigurationManager.getInstance().setProperty( "ch.top", String.valueOf( checkTop.isSelected() ) );
            ConfigurationManager.getInstance().save();
        }
        
        catch ( Exception e )
        {
            ApplicationController.getInstance().handleException( e );
        }
    }
    
    /**
     * initComponents
     * 
     */
    private void initComponents()
    {
        setSize( new Dimension( 200, 120 ) );
        setPreferredSize( new Dimension( 200, 120 ) );
        
        saveButton.setText( "Salvar" );
        saveButton.setForeground( Color.BLACK );
        saveButton.setFont( ApplicationController.defaultFont );
        
        folderLabel.setText( "Home:" );
        folderLabel.setForeground( Color.BLACK );
        folderLabel.setFont( ApplicationController.defaultFont );
        
        checkLabel.setText( "Janela:" );
        checkLabel.setForeground( Color.BLACK );
        checkLabel.setFont( ApplicationController.defaultFont );
        
        checkTop.setText( "Sempre no Topo" );
        checkTop.setForeground( Color.BLACK );
        checkTop.setFont( ApplicationController.defaultFont );
        checkTop.setOpaque( false );
        
        setLayout( new GridBagLayout() );
        
        add( folderLabel, 
             new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0, 
                                     GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                     new Insets( 5, 10, 0, 0 ), 0, 0 ) );
        
        add( folderSelector, 
             new GridBagConstraints( 1, 0, 1, 1, 1.0, 0.0, 
                                     GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                     new Insets( 5, 0, 0, 10 ), 0, 0 ) );
        
        add( checkLabel, 
             new GridBagConstraints( 0, 1, 1, 1, 0.0, 0.0, 
                                     GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                     new Insets( 0, 10, 0, 0 ), 0, 0 ) );
        
        add( checkTop, 
             new GridBagConstraints( 1, 1, 1, 1, 1.0, 0.0, 
                                     GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                                     new Insets( 0, 0, 0, 10 ), 0, 0 ) );
        
        add( saveButton, 
             new GridBagConstraints( 0, 2, 2, 1, 1.0, 0.0, 
                                     GridBagConstraints.CENTER, GridBagConstraints.NONE, 
                                     new Insets( 10, 0, 0, 0 ), 0, 0 ) );
        
        saveButton.addActionListener( new ActionListener() 
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                saveConfig();
                
                ApplicationController.getInstance().closeWindow();
            }
        } );
    }
    
    private JLabel folderLabel = new JLabel();
    private JLabel checkLabel = new JLabel();
    
    private FolderSelector folderSelector = new FolderSelector();
    private JCheckBox checkTop = new JCheckBox();
    
    private JButton saveButton = new JButton();
}
