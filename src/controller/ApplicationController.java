/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
    public static ApplicationController getInstance()
    {
        if ( defaultInstance == null )
        {
            defaultInstance = new ApplicationController();
        }
        
        return defaultInstance;
    }
    
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
    
    public void showDialog()
    {
        TrayDialog.getInstance().setVisible( true );
        TrayIcon.getInstance().updateActions( true );
    }
    
    public void closeDialog()
    {
        TrayDialog.getInstance().setVisible( false );
        TrayIcon.getInstance().updateActions( false );
    }
}
