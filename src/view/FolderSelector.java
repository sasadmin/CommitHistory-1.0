package view;

import controller.ConfigurationManager;
import controller.IconFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author LHG
 */
public class FolderSelector
    extends JPanel
{
    public FolderSelector()
    {
        initComponents();
    }
    
    /**
     * getSelectedFolder
     * 
     * @return 
     */
    public File getSelectedFolder()
    {
        return chooser.getSelectedFile();
    }
    
    /**
     * setSelectedFolder
     * 
     * @param f 
     */
    public void setSelectedFolder( File f )
    {
        chooser.setSelectedFile( f );
        pathField.setText( f.getAbsolutePath() );
    }
    
    /**
     * initComponents
     * 
     */
    private void initComponents()
    {
        setOpaque( false );
        
        chooseButton.setContentAreaFilled( false );
        chooseButton.setBorderPainted( false );
        chooseButton.setFocusable( false );
        chooseButton.setMargin( new Insets( 0, 0, 0, 0 ) );
        chooseButton.setIcon( IconFactory.getIcon( "/resources/folder.png", 16 ) );
        
        chooser.setMultiSelectionEnabled( false );
        chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
        
        pathField.setEditable( false );
        
        setLayout( new GridBagLayout() );
        
        add( pathField, 
             new GridBagConstraints( 0, 0, 1, 1, 1.0, 0.0, 
                                     GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, 
                                     new Insets( 0, 0, 0, 0 ), 0, 0 ) );
        
        add( chooseButton, 
             new GridBagConstraints( 1, 0, 1, 1, 0.0, 0.0, 
                                     GridBagConstraints.WEST, GridBagConstraints.NONE, 
                                     new Insets( 0, 5, 0, 0 ), 0, 0 ) );
        
        chooseButton.addActionListener( new ActionListener() 
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                if ( chooser.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION )
                {
                    pathField.setText( chooser.getCurrentDirectory().getAbsolutePath() );
                }
            }
        } );
    }
    
    private JFileChooser chooser = new JFileChooser();
    private JButton chooseButton = new JButton();
    private JTextField pathField = new JTextField();
}
