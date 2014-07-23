package view;

import controller.ApplicationController;
import controller.FindTextController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import util.Display;

/**
 *
 * @author AC
 */
public class TextQueryWindow
        extends DefaultWindow
{
    /**
     * TicketQueryWindow
     * 
     */
    public TextQueryWindow()
    {
        initComponents();
    }
    
    /**
     * searchText
     * 
     */
    public void searchText()
    {
        if ( textField.getText().trim().isEmpty() )
        {
            Display.alert( "Informe o texto para busca" );
        }
                
        else
        {
            FindTextController.getInstance().find( textField.getText() );
        }
    }
    
    /**
     * initComponents
     * 
     */
    private void initComponents()
    {
        setSize( new Dimension( 200, 100 ) );
        setPreferredSize( new Dimension( 200, 100 ) );

        textLabel.setText( "Texto:" );
        textLabel.setForeground( Color.BLACK );
        textLabel.setFont( ApplicationController.defaultFont );

        searchButton.setText( "Buscar" );
        searchButton.setToolTipText( "Busca o texto nos arquivos de log" );
        searchButton.setForeground( Color.BLACK );
        searchButton.setFont( ApplicationController.defaultFont );

        textField.addFocusListener( new FocusAdapter() 
        {
            @Override
            public void focusGained(FocusEvent e) 
            {
                ( (JTextField) e.getComponent() ).selectAll();
            }
        } );

        setLayout( new GridBagLayout() );

        add( closeButton, 
             new GridBagConstraints( 0, 0, 3, 1, 0.0, 0.0, 
                                     GridBagConstraints.EAST, GridBagConstraints.NONE, 
                                     new Insets( 0, 0, 15, 15 ), 0, 0 ) );
        
        add( textLabel, 
             new GridBagConstraints( 1, 0, 1, 1, 0.0, 0.0,
                                     GridBagConstraints.WEST, GridBagConstraints.NONE,
                                     new Insets( 30, 15, 0, 0 ), 0, 0 ) );

        add( textField, 
             new GridBagConstraints( 2, 0, 1, 1, 1.0, 0.0,
                                     GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                                     new Insets( 30, 0, 0, 15 ), 0, 0 ) );

        add( searchButton, 
             new GridBagConstraints( 1, 1, 2, 1, 0.0, 1.0,
                                     GridBagConstraints.SOUTH, GridBagConstraints.NONE,
                                     new Insets( 0, 10, 10, 0 ), 0, 0 ) );

        searchButton.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                searchText();
            }
        } );
        
        textField.addKeyListener( new KeyAdapter()
        {

            @Override
            public void keyPressed( KeyEvent e )
            {
                if ( e.getKeyCode() == KeyEvent.VK_ENTER )
                {
                    searchText();
                }
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

    private JLabel textLabel = new JLabel();
    private JTextField textField = new JTextField();

    private JButton searchButton = new JButton();
    
    private CloseButton closeButton = new CloseButton();
}

