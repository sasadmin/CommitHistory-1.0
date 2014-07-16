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

    /**
     * ApplicationController
     * 
     */
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
    
    /**
     * registerCommit
     * 
     * @param commit 
     */
    private void registerCommit( Commit commit )
    {
        //TODO
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
        TrayDialog.getInstance().setVisible( visible );
        TrayIcon.getInstance().updateActions( visible );
    }
    
    /**
     * toggleDialog
     * 
     */
    public void toggleDialog()
    {
        toggleDialog( ! TrayDialog.getInstance().isVisible() );
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