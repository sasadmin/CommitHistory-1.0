package controller;

import data.Commit;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import model.ModelManager;
import util.Display;
import view.DefaultWindow;
import view.GlassPane;
import view.TrayDialog;
import view.TrayIcon;

/**
 *
 * @author LHG
 */
public class ApplicationController
{
    private static ApplicationController defaultInstance;

    public static final String applicationName = "Commit History";
    
    public static final Font defaultFont = new Font( "monospaced", Font.BOLD, 12 );
    public static final Font defaultTitleFont = new Font( "monospaced", Font.BOLD, 15 );
    
    public static final Dimension defaultDimension = new Dimension( 250, 150 );
    
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
        StringBuilder validateInfo = new StringBuilder();
        
        final String prefixInfo = "É necessário informar ";
        
        if ( commit.getTicket().trim().isEmpty() )
        {
            validateInfo.append( prefixInfo ).append( "o ticket" );
        }
        
        if ( commit.getRevision().trim().isEmpty() )
        {
            validateInfo.append( validateInfo.toString().isEmpty() ? prefixInfo : " e " );
            
            validateInfo.append( "a revisão" );
        }
        
        if ( ! commit.getVersion().trim().isEmpty() )
        {
            String[] version = commit.getVersion().split( "\\." );
            
            if ( version.length != 4 )
            {
                String msg = "A versão informada não é valida!" ;
                
                validateInfo.append( validateInfo.toString().isEmpty() ? msg : "\n" + msg );
            }
        }
        
        if ( validateInfo.toString().isEmpty() )
        {
            String value = ConfigurationManager.getInstance().getProperty( commit.getRevision() );
            
            if( !value.isEmpty() && Boolean.valueOf( value ) )
            {
                String msg = "Esta revisão já está associada a este ticket!" ;
                
                validateInfo.append( validateInfo.toString().isEmpty() ? msg : "\n" + msg );
            }
        }
        
        if ( ! validateInfo.toString().isEmpty() )
        {
            Display.alert( validateInfo + "!" );
        }
        
        return validateInfo.toString().isEmpty();
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
                String value = ConfigurationManager.getInstance().getProperty( commit.getRevision()+ "-" + commit.getTicket() );
            
                if( value.isEmpty() )
                {
                    ModelManager.getInstance().getCommitModel().saveCommit( commit );
                    trayDialog.clearInputs();
                    Display.info( "Commit salvo com sucesso" );
                }
                
                if ( ! commit.getVersion().trim().isEmpty() )
                {
                    signTicketHistory( commit );
                }
            }
            
            catch ( Exception e )
            {
                ApplicationController.getInstance().handleException( e );
            }
        }
    }
    
    /**
     * signTicketHistory
     * 
     * @param commit Commit
     */
    private void signTicketHistory( Commit commit )
    {
//        TicketHistoryController.assign( commit );
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
        TrayIcon.getInstance().updateActions( visible );
        trayDialog.setVisible( visible );
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
     * openWindow
     * 
     * @param window 
     */
    public void openWindow( DefaultWindow window )
    {
        GlassPane glassPane = new GlassPane( window );
        
        trayDialog.setGlassPane( glassPane );
        
        glassPane.fadeIn();
    }
    
    /**
     * closeWindow
     * 
     */
    public void closeWindow()
    {
        ((GlassPane)trayDialog.getGlassPane()).fadeOut();
    }
    
    /**
     * handleException
     * 
     * @param e Exception
     */
    public void handleException( Exception e )
    {
        Display.exception( e.getMessage() );
//        e.printStackTrace();
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