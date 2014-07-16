package controller;

import java.awt.TrayIcon.MessageType;
import data.Commit;
import model.ModelManager;
import view.TrayDialog;
import view.TrayIcon;

/**
 *
 * @author Galimberti
 */
public class ApplicationController
{
    private static ApplicationController defaultInstance;

    private final TrayDialog trayDialog;
    
    /**
     * ApplicationController
     * 
     */
    private ApplicationController()
    {
        trayDialog = new TrayDialog();
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
     * @param commit
     */
    public void saveCommit( Commit commit )
    {
        if ( commit != null && validateCommit( commit ) )
        {
            try
            {
                ModelManager.getInstance().getCommitModel().saveCommit( commit );
                trayDialog.clearInputs();
                
                TrayIcon.getInstance().showMessage( "Commit salvo com sucesso", MessageType.INFO );
            }
            
            catch ( Exception e )
            {
                ApplicationController.getInstance().handleException( e );
            }
        }
    }
    
    /**
     * showDialog
     * 
     */
    public void showDialog()
    {
        toggleDialog( true );
    }
    
    /**
     * closeDialog
     * 
     */
    public void closeDialog()
    {
        toggleDialog( false );
    }
    
    /**
     * toggleDialog
     * 
     * @param visible 
     */
    public void toggleDialog( boolean visible )
    {
        trayDialog.setVisible( visible );
        TrayIcon.getInstance().updateActions( visible );
    }
    
    /**
     * toggleDialog
     * 
     */
    public void toggleDialog()
    {
        toggleDialog( ! trayDialog.isVisible() );
    }
    
    /**
     * handleException
     * 
     * @param e Exception
     */
    public void handleException( Exception e )
    {
        e.printStackTrace();
    }
    
    /**
     * exit
     * 
     */
    public void exit()
    {
        try
        {
            ConfigurationManager.getInstance().save();
        }
        
        catch ( Exception e )
        {
            handleException( e );            
        }
                
        finally
        {
            System.exit( 0 );
        }
    }
}