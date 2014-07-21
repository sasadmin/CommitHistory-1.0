package view;

import controller.ApplicationController;
import controller.ConfigurationManager;
import java.util.HashSet;
import java.util.Set;
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

            refreshPossibility();
        }
        
        catch ( Exception e )
        {
            ApplicationController.getInstance().handleException( e );
        }
    }

    /**
     * refreshPossibility
     * 
     */
    public final void refreshPossibility()
    {
        removeAllPossibilities();
        
        Set<String> keys = ConfigurationManager.getInstance().getStringPropertyNames();
       
        Set<String> values = new HashSet();
        
        for ( String va : keys )
        {
            if ( va.contains( "|" ) )
            {
                String[] value = va.split( "\\|" );

                if ( value.length == 2 )
                {
                    values.add( value[1] );
                }
            }
        }
        
        for( String value : values )
        {
            addPossibility( value );
        }
    }
}
