package model.service;

import controller.ApplicationController;
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
        
        File work = new File( ConfigurationManager.getInstance().getProperty( "ch.home", System.getProperty( "user.home" ) + File.separator + "CommitHistory" ) );
        
        if ( !work.exists() )
        {
            work.mkdir();
        }
        
        File file = ApplicationController.getInstance().getFile( commit.getTicket(), true );

        String text = FileUtilities.loadText( file );

        String msgCommit = SvnController.obtainLogCommit( commit );

        if ( !msgCommit.trim().isEmpty() )
        {
            FileUtilities.saveText( file, text + msgCommit + "\n" );
        }
    }
}
