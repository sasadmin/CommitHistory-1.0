/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.ApplicationController;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
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
        
        setIcon( createImage( "/resources/minimize.png", "minimize icon" ) );
        
        addMouseListener( new MouseAdapter()  
        {  
            @Override
            public void mouseClicked(MouseEvent e)  
            {  
               ApplicationController.getInstance().closeDialog();
            }  
        });
    }
    
    //Obtain the image URL
    protected static ImageIcon createImage( String path, String description ) 
    {
        URL imageURL = MinimizeButton.class.getResource( path );
        
        if (imageURL == null) 
        {
            System.err.println( "Resource not found: " + path );
            return null;
        } 
        
        else 
        {
            Image img = new ImageIcon( imageURL ).getImage();
            
            return new ImageIcon( img.getScaledInstance( 12, 12, Image.SCALE_SMOOTH ), description );
        }
    }
}
