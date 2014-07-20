/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ApplicationController;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.interpolation.PropertySetter;
import org.jdesktop.swingx.image.GaussianBlurFilter;
import org.jdesktop.swingx.util.GraphicsUtilities;

/**
 *
 * @author Galimberti
 */
public class GlassPane extends JPanel
{
    private DefaultWindow defaultWindow;
    private BufferedImage blurBuffer;
    private BufferedImage backBuffer;
    private float alpha = 0.0f;

    /**
     * GlassPane
     * 
     * @param defaultWindow 
     */
    public GlassPane( DefaultWindow defaultWindow )
    {
        setSize( ApplicationController.defaultDimension );
        setPreferredSize( ApplicationController.defaultDimension );
        
        setLayout( new GridBagLayout() );

        this.defaultWindow = defaultWindow;
        this.defaultWindow.setAlpha( 0.0f );
        add( defaultWindow, new GridBagConstraints() );

        addMouseListener( new MouseAdapter()
        {
        } );
    }

    /**
     * createBlur
     * 
     */
    private void createBlur()
    {
        JRootPane root = SwingUtilities.getRootPane( this );
        blurBuffer = GraphicsUtilities.createCompatibleImage( getWidth(), getHeight() );
        Graphics2D g2 = blurBuffer.createGraphics();
        root.paint( g2 );
        g2.dispose();

        backBuffer = blurBuffer;

        blurBuffer = GraphicsUtilities.createThumbnailFast( blurBuffer, getWidth() / 2 );
        blurBuffer = new GaussianBlurFilter( 5 ).filter( blurBuffer, null );
    }

    /**
     * paintComponent
     * 
     * @param g 
     */
    @Override
    protected void paintComponent( Graphics g )
    {
        if ( isVisible() && blurBuffer != null )
        {
            Graphics2D g2 = ( Graphics2D ) g.create();

            g2.setRenderingHint( RenderingHints.KEY_INTERPOLATION,
                                 RenderingHints.VALUE_INTERPOLATION_BILINEAR );
            g2.drawImage( backBuffer, 0, 0, null );

            g2.setComposite( AlphaComposite.SrcOver.derive( alpha ) );
            g2.drawImage( blurBuffer, 0, 0, getWidth(), getHeight(), null );
            g2.dispose();
        }
    }

    /**
     * getAlpha
     * 
     * @return 
     */
    public float getAlpha()
    {
        return alpha;
    }

    /**
     * setAlpha
     * 
     * @param alpha 
     */
    public void setAlpha( float alpha )
    {
        this.alpha = alpha;
        repaint();
    }

    /**
     * fadeIn
     * 
     */
    public void fadeIn()
    {
        createBlur();

        setVisible( true );
        
        SwingUtilities.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                Animator animator = PropertySetter.createAnimator( 400, defaultWindow, "alpha", 1.0f );
                animator.setAcceleration( 0.2f );
                animator.setDeceleration( 0.3f );
                animator.addTarget( new PropertySetter( GlassPane.this, "alpha", 1.0f ) );
                animator.start();
            }
        } );
    }

    /**
     * fadeOut
     * 
     */
    public void fadeOut()
    {
        SwingUtilities.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                Animator animator = PropertySetter.createAnimator( 400, defaultWindow, "alpha", 0.0f );
                animator.setAcceleration( 0.2f );
                animator.setDeceleration( 0.3f );
                animator.addTarget( new PropertySetter( GlassPane.this, "alpha", 0.0f ) );
                animator.start();
                
                new Thread( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep( 400 );
                            
                            GlassPane.this.setVisible( false );
                        }
                        
                        catch ( Exception e )
                        {
                        }
                    }
                } ).start();
            }
        } );
    }
}
