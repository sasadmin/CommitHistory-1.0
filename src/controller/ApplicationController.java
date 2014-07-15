package controller;

import java.awt.TrayIcon.MessageType;
import model.Commit;
import view.TrayDialog;
import view.TrayIcon;

/**
 *
 * @author Galimberti
 */
public class ApplicationController
{
    private static ApplicationController defaultInstance;
    
    private ApplicationController()
    {
    }
    
    /**
     * getInstance
     * 
     * @return ApplicationController
     */
    public static ApplicationController getInstance()
    {
        if ( defaultInstance == null )
        {
            defaultInstance = new ApplicationController();
        }
        
        return defaultInstance;
    }
    
    /**
     * validateCommit
     * 
     * @param commit Commit
     * @return boolean
     */
    private boolean validateCommit( Commit commit )
    {
        if ( commit.getTicket().trim().isEmpty() )
        {
            TrayIcon.getInstance().showMessage( "É necessário informar o ticket!", MessageType.ERROR );
            
            return false;
        }
        
        else if ( commit.getRevision().trim().isEmpty() )
        {
            TrayIcon.getInstance().showMessage( "É necessário informar a revisão!", MessageType.ERROR );
            
            return false;
        }
        
        return true;
    }
    
    /**
     * save
     * 
     */
    public void save()
    {
        Commit commit = TrayDialog.getInstance().getCommit();
        
        if ( validateCommit( commit ) )
        {
            registerCommit( commit );
            
            TrayIcon.getInstance().showMessage( "Commit salvo com sucesso", MessageType.INFO );
        }
    }
    
    private void registerCommit( Commit commit )
    {
        
    }
    
    /**
     * showDialog
     * 
     */
    public void showDialog()
    {
        TrayDialog.getInstance().setVisible( true );
        TrayIcon.getInstance().updateActions( true );
    }
    
    /**
     * closeDialog
     * 
     */
    public void closeDialog()
    {
        TrayDialog.getInstance().setVisible( false );
        TrayIcon.getInstance().updateActions( false );
    }
}