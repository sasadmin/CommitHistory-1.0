package model.service;

import controller.ConfigurationManager;
import util.FileUtilities;
import data.Commit;
import java.io.File;
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
            work.mkdir();
        }
        
        if ( work.isDirectory() )
        {
            File file = new File( path + File.separator + commit.getTicket() + ".txt" );
            String msgCommit = "";
            
            if ( file.exists() )
            {
                String text = FileUtilities.loadText( file );
                
                msgCommit = SvnController.obtainLogCommit( commit );
                
                if ( !msgCommit.trim().isEmpty() )
                {
                    saveText( commit, file, text + msgCommit + "\n" );
                }
            }
                    
            else
            {
                 msgCommit = SvnController.obtainLogCommit( commit );
                
                if ( !msgCommit.trim().isEmpty() )
                {
                    saveText( commit, file, msgCommit + "\n" );
                }
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
        
        ConfigurationManager.getInstance().setProperty( commit.getRevision()+ "|" + commit.getTicket(), String.valueOf( !commit.getVersion().isEmpty() ) );
        ConfigurationManager.getInstance().save();
    }
}
