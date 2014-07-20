/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import org.jdesktop.swingx.JXPanel;

/**
 *
 * @author Galimberti
 */
public class DefaultWindow
        extends JXPanel
{
    /**
     * 
     */
    public DefaultWindow()
    {
        setOpaque( false );
    }

    /**
     * paintComponent
     * 
     * @param g 
     */
    @Override
    protected void paintComponent( Graphics g )
    {
        int x = 3;
        int y = 3;
        int w = getWidth() - 6;
        int h = getHeight() - 6;
        int arc = 30;

        Graphics2D g2 = ( Graphics2D ) g.create();
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        g2.setColor( new Color( 0, 0, 0, 200 ) );

        g2.fillRoundRect( x, y, w, h, arc, arc );

        g2.setStroke( new BasicStroke( 3f ) );
        g2.setColor( Color.WHITE );
        g2.drawRoundRect( x, y, w, h, arc, arc );

        g2.dispose();
    }
}