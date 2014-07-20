/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ApplicationController;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
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

        Graphics2D g2d = ( Graphics2D ) g.create();
        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        g2d.setColor( new Color( 186, 221, 255 ) );
        g2d.fillRoundRect( x, y, w, h, arc, arc );
        AffineTransform defaultTransform = g2d.getTransform();

        int parentWidth = ApplicationController.defaultDimension.width;
        int parentHeight = ApplicationController.defaultDimension.height;
        
        int parentX = ( getWidth() - parentWidth ) / 2;
        int parentY = ( getHeight() - parentHeight ) / 2;
        
        g2d.clip( new RoundRectangle2D.Double( x, y, w, h, arc, arc ) );
        
        Graphics2D g2dParent = (Graphics2D)g2d.create( parentX, parentY, parentWidth, parentHeight );
        
        g2dParent.rotate(  Math.toRadians( -30 ) );
        g2dParent.setColor( new Color(  231, 241, 251 ) );
        g2dParent.fillOval( (parentWidth / 2) * -1, 0, (int)(parentWidth * 1.2), parentHeight );

        g2d.setTransform( defaultTransform );
        
        g2d.setStroke( new BasicStroke( 3f ) );
        g2d.setColor( Color.GRAY );
        g2d.setClip( 0, 0, getWidth(), getHeight() );
        g2d.drawRoundRect( x, y, w, h, arc, arc );

        g2d.dispose();
    }
}