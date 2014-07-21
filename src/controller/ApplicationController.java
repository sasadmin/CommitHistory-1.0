package controller;

import data.Commit;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import javax.swing.SwingUtilities;
import model.ModelManager;
import util.Display;
import util.FileUtilities;
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
    public static final Font defaultNames = new Font( "monospaced", Font.PLAIN, 13 );
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
     * getFile
     * 
     * @param ticket String
     * @return File
     */
    public File getFile( String ticket )
    {
        return getFile( ticket, false );
    }
    
    /**
     * getFile
     * 
     * @param ticket String
     * @param createNotExists boolean
     * @return File
     */
    public File getFile( String ticket, boolean createNotExists )
    {
        File file = new File( ConfigurationManager.getInstance().getProperty( "ch.home", System.getProperty( "user.home" ) + File.separator + "CommitHistory" ) + File.separator + ticket + ".txt" );

        try
        {
            if ( !file.exists() && createNotExists )
            {
                FileUtilities.createFile( file );
            }
        }

        catch ( Exception e )
        {
            handleException( e );
        }
        
        return file;
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
                String msg = "A versão informada não é valida" ;
                
                validateInfo.append( validateInfo.toString().isEmpty() ? msg : "\n" + msg );
            }
        }
        
        if ( validateInfo.toString().isEmpty() )
        {
            if( isSave( commit ) )
            {
                String msg = "Esta revisão já está associada a este ticket";
                
                if ( commit.getVersion().trim().isEmpty() )
                {
                    validateInfo.append( msg );
                    validateInfo.append( "\nInforme a versão caso deseje assinar o Ticket History" );
                }
                
                else if ( commit.getVersion().equals( ConfigurationManager.getInstance().getProperty( commit.getRevision()+ "|" + commit.getTicket() ) ) )
                {
                    validateInfo.append( msg );
                }
            }
        }
        
        if ( ! validateInfo.toString().isEmpty() )
        {
            Display.alert( validateInfo + "!" );
        }
        
        return validateInfo.toString().isEmpty();
    }
    
    /**
     * saveCommit
     * 
     * @param commit Commit
     * @return boolean
     */
    public boolean saveCommit( Commit commit )
    {
        boolean save = false;
        
        if ( commit != null && validateCommit( commit ) )
        {
            try
            {
                String msg = "";
                
                if( !isSave( commit ) )
                {
                    ModelManager.getInstance().getCommitModel().saveCommit( commit );

                    msg = "Commit salvo com sucesso!";
                }
                
                if ( !commit.getVersion().trim().isEmpty() )
                {
                    signTicketHistory( commit );
                    
                    msg = msg.trim().isEmpty() ? "TicketHistory assinado com sucesso!" : msg + "\nTicketHistory assinado com sucesso!";
                }
                
                save = !msg.isEmpty(); 
                
                if ( save )
                {
                    ConfigurationManager.getInstance().setProperty( commit.getRevision()+ "|" + commit.getTicket(), commit.getVersion() );
                    ConfigurationManager.getInstance().save();
                    
                    trayDialog.clearInputs();
                    
                    Display.info( msg );
                }
            }
            
            catch ( Exception e )
            {
                ApplicationController.getInstance().handleException( e );
            }
        }
        
        return save;
    }
    
    /**
     * signTicketHistory
     * 
     * @param commit Commit
     */
    private void signTicketHistory( Commit commit )
    {
        TicketHistoryController.assign( commit );
    }
    
    /**
     * isSave
     * 
     * @param commit Commit
     * @return boolean
     */
    private boolean isSave( Commit commit )
    {
        String value = ConfigurationManager.getInstance().getProperty( commit.getRevision()+ "|" + commit.getTicket(), null );
        
        return value != null;
    }
    
    /**
     * openFile
     * 
     * @param ticket String
     */
    public void openFile( String ticket )
    {
        try
        {
            File file = getFile( ticket );

            if ( file.exists())
            {
                if ( Desktop.isDesktopSupported() )
                {
                    Desktop.getDesktop().open( file );
                }
                else
                {
                    Display.alert( "Desktop not suported!" );
                }
            }

            else
            {
                Display.alert( "Arquivo não encontrado!" );
            }
        }
        catch ( Exception e )
        {
            handleException( e );
        }
    }
    
    /**
     * copyClipboard
     * 
     * @param ticket String
     */
    public void copyClipboard( String ticket )
    {
        try
        {
            File file = getFile( ticket );

            if ( file.exists() )
            {
                String value = FileUtilities.loadText( file );

                StringSelection stringSelection = new StringSelection( value );

                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();

                clpbrd.setContents (stringSelection, null);
                
                Display.info( "Copiado para área de tranferência!" );
            }
            else
            {
                Display.alert( "Arquivo não encontrado!" );
            }
        }
                
        catch ( Exception e )
        {
            handleException( e );
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
        TrayIcon.getInstance().updateActions( visible );
        trayDialog.setVisible( visible );
        
        if ( Boolean.valueOf( ConfigurationManager.getInstance().getProperty( "ch.top", "true" ) ) )
        {
            SwingUtilities.invokeLater( new Runnable()
            {
                @Override
                public void run()
                {
                    trayDialog.requestFocusInWindow();
                    trayDialog.setAlwaysOnTop( true );
                }
            } );
        }
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
    public void closeWindow( boolean animation )
    {
        if ( trayDialog.getGlassPane() instanceof GlassPane )
        {
            if ( animation )
            {
                ((GlassPane)trayDialog.getGlassPane()).fadeOut();
            }
            
            else
            {
                ((GlassPane)trayDialog.getGlassPane()).setVisible( false );
            }
        }
    }
    
    /**
     * closeWindow
     * 
     */
    public void closeWindow()
    {
        closeWindow( true );
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