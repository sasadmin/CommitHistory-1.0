package util;

/**
 *
 * @author AC
 */
public class ApplicationUtilities
{
    private static ApplicationUtilities defaultInstance;
    private String ticket = "";

    /**
     * getInstance
     * 
     * @return ApplicationUtilities
     */
    public static ApplicationUtilities getInstance()
    {
        if ( defaultInstance == null )
        {
            defaultInstance = new ApplicationUtilities();
        }
        
        return defaultInstance;
    }

    /**
     * @return the ticket
     */
    public String getTicket()
    {
        return ticket;
    }

    /**
     * @param aTicket the ticket to set
     */
    public void setTicket( String aTicket )
    {
        ticket = aTicket;
    }
}