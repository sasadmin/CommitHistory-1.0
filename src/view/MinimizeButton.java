package view;

import controller.ApplicationController;
import controller.IconFactory;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author Galimberti
 */
public class MinimizeButton
    extends JLabel
{
    /**
     * MinimizeButton
     * 
     */
    public MinimizeButton()
    {
        initComponents();
    }

    /**
     * initComponents
     * 
     */
    private void initComponents()
    {
        setSize( 12, 12 );
        
        setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        
        setToolTipText( "Minimizar janela" );
        
        setIcon( IconFactory.getIcon( "/resources/minimize.png", 12 ) );
        
        addMouseListener( new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
               ApplicationController.getInstance().closeDialog();
            }  
        } );
    }
}