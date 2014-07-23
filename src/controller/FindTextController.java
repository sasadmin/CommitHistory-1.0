package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import util.Display;
import util.FileUtilities;

/**
 *
 * @author AC
 */
public class FindTextController
{
    private static FindTextController defaultInstance;
    
    private String textFind = "";
    private String content = "";
    
    private List<String> found;
            
    /**
     * getInstance
     * 
     * @return FindTextController
     */
    public static FindTextController getInstance()
    {
        if ( defaultInstance == null )
        {
            defaultInstance = new FindTextController();
        }
        
        return defaultInstance;
    }
    
    /**
     * FindTextController
     * 
     */
    public FindTextController()
    {
    }
    
    
    /**
     * find
     * 
     * @param textFind String
     */
    public void find( String textFind )
    {
        this.textFind = textFind;
        
        try
        {
            found = new ArrayList<String>();
            
            String path = ConfigurationManager.getInstance().getProperty( "ch.home", System.getProperty( "user.home" ) + File.separator + "CommitHistory" );

            File home = new File( path );

            if ( home.exists() )
            {
                for( File f : home.listFiles() )
                {
                    String text = FileUtilities.loadText( f );
                    
                    processText( text );
                }
                
                if ( !found.isEmpty() )
                {
                    File file = File.createTempFile( "CommitHistory-" + UUID.randomUUID(), ".txt" );
                    
                    String text = "";
                    
                    for( String t : found )
                    {
                        text += t + "\n";
                    }
                    
                    FileUtilities.saveText( file, text );
                    
                    ApplicationController.getInstance().openFile( file );
                    
                    file.deleteOnExit();
                }
                
                else
                {
                    Display.info( "Texto não encontrado!" );
                }
            }
        }
                
        catch ( Exception e )
        {
            ApplicationController.getInstance().handleException( e );
        }
    }
    
    /**
     * processText
     * 
     * @param textContent String
     */
    private void processText( String textContent )
    {
        String[] lines = textContent.split( "\n" );
        
        for ( int i = 0; i < lines.length; i++ )
        {
            String l = lines[i];
            
            if ( l.isEmpty() )
            {
                write();
            }
                    
            else
            {
                content += l + "\n";
            }
            
            if ( i == lines.length -1 )
            {
                write();
            }
        }
    }
    
    /**
     * write
     * 
     */
    private void write( )
    {
        if ( ! content.trim().isEmpty() )
        {
            if ( content.contains( textFind ) )
            {
                found.add( content );
            }
            
            content = "";
        }
    }
    
}
