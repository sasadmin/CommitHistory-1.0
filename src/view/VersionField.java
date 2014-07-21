package view;

import controller.ConfigurationManager;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author LHG
 */
public class VersionField
    extends AutoCompleteTextField
{
    public VersionField()
    {
        refreshPossibility();
    }
    
    /**
     * refreshPossibility
     * 
     */
    public final void refreshPossibility()
    {
        removeAllPossibilities();
        
        Set<String> keys = ConfigurationManager.getInstance().getStringPropertyNames();
        
        Set<String> versions = new HashSet();
       
        for ( String va : keys )
        {
            if ( va.contains( "|" ) )
            {
                versions.add( ConfigurationManager.getInstance().getProperty( va ) );
            }
        }
        
        for( String value : versions )
        {
            addPossibility( value );
        }
    }
}
