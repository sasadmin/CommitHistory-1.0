package view;

/**
 *
 * @author LHG
 */
public class VersionField
    extends AutoCompleteTextField
{
    public VersionField()
    {
        addPossibility( "SA-WEB-7.0.0.0" );
        addPossibility( "SA-DESKTOP-7.0.0.0" );
    }
}
