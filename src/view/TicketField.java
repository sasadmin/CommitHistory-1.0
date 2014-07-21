package view;

import controller.ApplicationController;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author LHG
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
