package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Galimberti
 */
public class DialogPane
    extends JPanel
{
    public DialogPane()
    {
        setBorder( new LineBorder( new Color( 0, 0, 0 ), 1, true ) );
    }
    
    /**
     * paintComponent
     * 
     * @param g Graphics
     */
    @Override
    public void paintComponent( Graphics g )
    {
        Graphics2D g2d = (Graphics2D)g;

        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                              RenderingHints.VALUE_ANTIALIAS_ON );

        g2d.setColor( new Color( 186, 221, 255 ) );
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
        AffineTransform defaultTransform = g2d.getTransform();

        g2d.clip( new Rectangle( 0, 0, getWidth(), getHeight() ) );
        g2d.rotate(  Math.toRadians( -30 ) );
        g2d.setColor( new Color(  231, 241, 251 ) );
        g2d.fillOval( (getWidth() / 2) * -1, 0, (int)(getWidth() * 1.2), getHeight() );

        g2d.setTransform( defaultTransform );
    }
}
