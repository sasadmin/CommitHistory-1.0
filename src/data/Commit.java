package data;

/**
 *
 * @author Galimberti
 */
public class Commit
{
    private String ticket;
    private String revision;
    private String version;

    /**
     * Commit
     * 
     */
    public Commit() 
    {
    }

    /**
     * Commit
     * 
     * @param ticket
     * @param revision
     * @param version 
     */
    public Commit( String ticket, String revision, String version ) 
    {
        this.ticket = ticket;
        this.revision = revision;
        this.version = version;
    }

    
    /**
     * getTicket
     * 
     * @return 
     */
    public String getTicket()
    {
        return ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket( String ticket )
    {
        this.ticket = ticket;
    }

    /**
     * @return the revision
     */
    public String getRevision()
    {
        return revision;
    }

    /**
     * @param revision the revision to set
     */
    public void setRevision( String revision )
    {
        this.revision = revision;
    }

    /**
     * @return the version
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion( String version )
    {
        this.version = version;
    }
}
