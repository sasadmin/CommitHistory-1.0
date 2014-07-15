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
    public MinimizeButton()
    {
        setSize( 12, 12 );
        
        setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        
        setIcon( IconFactory.getIcon( "/resources/minimize.png", 12 ) );
        
        addMouseListener( new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
               ApplicationController.getInstance().closeDialog();
            }  
        });
    }
}