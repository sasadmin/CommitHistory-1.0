package model.service;

import controller.ConfigurationManager;
import util.FileUtilities;
import data.Commit;
import java.io.File;
import java.io.FilenameFilter;
import model.CommitModel;
import controller.SvnController;

/**
 *
 * @author IL
 */ 
public class CommitModelService 
    implements CommitModel
{
    /**
     * CommitModelService
     * 
     */
    public CommitModelService() 
    {
    }
    
    /**
     * saveCommit
     * 
     * @param commit
     * @throws Exception 
     */
    @Override
    public void saveCommit( final Commit commit ) throws Exception 
    {
        if ( commit == null )
        {
            throw new Exception( "Commit is null!" );
        }
        
        String path = System.getProperty( "user.home" ) + File.separator + "CommitHistory";
        
        File work = new File( path );
        
        if ( !work.exists() )
        {
            work = FileUtilities.createFile( work );
        }
        
        if ( work.isDirectory() )
        {
            final String pathTicket = commit.getTicket() + ".txt";
            
            File[] files = work.listFiles( new FilenameFilter()
            {
                @Override
                public boolean accept( File dir, String name )
                {
                    return name.equalsIgnoreCase( pathTicket );
                }
            });
            
            if ( files.length == 1 )
            {
                File f = files[0];
                
                String text = FileUtilities.loadText( f );
                
                saveText( commit, f, text + SvnController.obtainLogCommit( commit ) );
            }
                    
            else
            {
                File ticket = new File( path + File.separator + pathTicket );
                
                saveText( commit, ticket, SvnController.obtainLogCommit( commit ) );
            }
        }
    }
    
    /**
     * saveText
     * 
     * @param commit Commit
     * @param file File
     * @param text String
     * @throws Exception
     */
    private void saveText( Commit commit, File file, String text ) throws Exception
    {
        FileUtilities.saveText( file, text );
        
        ConfigurationManager.getInstance().setProperty( commit.getRevision()+ "-" + commit.getTicket(), String.valueOf( !commit.getVersion().isEmpty() ) );
        ConfigurationManager.getInstance().save();
    }
}
