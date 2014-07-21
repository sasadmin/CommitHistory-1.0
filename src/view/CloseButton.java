package view;

import controller.IconFactory;
import java.awt.Cursor;
import javax.swing.JLabel;

/**
 *
 * @author AC
 */
public class CloseButton
    extends JLabel
{
    /**
     * MinimizeButton
     * 
     */
    public CloseButton()
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
        
        setIcon( IconFactory.getIcon( "/resources/close.png", 12 ) );
    }
}