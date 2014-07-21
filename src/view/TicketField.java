/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.ApplicationController;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Galimberti
 */
public class TicketField
    extends AutoCompleteTextField
{
    public TicketField()
    {
        try
        {
            MaskFormatter maskData = new MaskFormatter( "TSI-#####" );
            maskData.install( this );
        }
        
        catch ( Exception e )
        {
            ApplicationController.getInstance().handleException( e );
        }
        
        addPossibility( "TSI-28549" );
        addPossibility( "TSI-28123" );
        addPossibility( "TSI-24558" );
        addPossibility( "TSI-24559" );
    }
}
