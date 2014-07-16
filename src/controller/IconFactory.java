package controller;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Alex Carvalho
 */
public class IconFactory
{
    /**
     * IconFactory
     * 
     */
    private IconFactory() 
    {
    }
    
    /**
     * getIcon
     * 
     * @param path String
     * @param size int
     * @return ImageIcon
     */
    public static ImageIcon getIcon( String path, int size )
    {
        ImageIcon image = null;
        
        Image img = getImage( path );
        
        if ( img != null )
        {
            image = new ImageIcon( img.getScaledInstance( size, size, Image.SCALE_SMOOTH ) );
        }
        
        return image;
    }
    
    /**
     * getImage
     * 
     * @param path String
     * @return Image
     */
    public static Image getImage( String path )
    {
        Image img = null;
        
        URL imageURL = IconFactory.class.getResource( path );
        
        if ( imageURL == null ) 
        {
            System.err.println( "Resource not found: " + path );
        } 
        
        else 
        {
            img = new ImageIcon( imageURL ).getImage();
        }
        
        return img;
    }
}