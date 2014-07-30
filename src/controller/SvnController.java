package controller;

import data.Commit;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author AC
 */
public class SvnController
{
    private static final String WORK = "svn://svn.interact.com.br/sas/work";
    private static final String QUARENTINE = "svn://svn.interact.com.br/sas/quarentine";
    private static final String RELEASES = "svn://svn.interact.com.br/sas/releases";

    /**
     * obtainLogCommit
     * 
     * @param commit Commit
     * @return String
     * @throws Exception
     */
    public static String obtainLogCommit( Commit commit ) throws Exception
    {
        String value = "";
        
        String repository1 = ConfigurationManager.getInstance().getProperty( "repository.1", WORK );
        String repository2 = ConfigurationManager.getInstance().getProperty( "repository.2", QUARENTINE );
        String repository3 = ConfigurationManager.getInstance().getProperty( "repository.3", RELEASES );
        String repository4 = ConfigurationManager.getInstance().getProperty( "repository.4" );
        String repository5 = ConfigurationManager.getInstance().getProperty( "repository.5" );

        if ( repository1 != null && !repository1.isEmpty() )
        {
            value += obtainLogCommit( commit, repository1 );
        }
        
        if ( repository2 != null && !repository2.isEmpty() )
        {
            value += obtainLogCommit( commit, repository2 );
        }
        
        if ( repository3 != null && !repository3.isEmpty() )
        {
            value += obtainLogCommit( commit, repository3 );
        }
        
        if ( repository4 != null && !repository4.isEmpty() )
        {
            value += obtainLogCommit( commit, repository4 );
        }
        
        if ( repository5 != null && !repository5.isEmpty() )
        {
            value += obtainLogCommit( commit, repository5 );
        }
        
        return value;
    }
    
    /**
     * obtainLogCommit
     * 
     * @param cmd String[]
     * @return String
     * @throws Exception
     */
    public static String obtainLogCommit( Commit commit, String repository ) throws Exception
    {
        String[] cmd = new String[]{ "svn", "log", "-r", commit.getRevision(), "-v", repository };
                
        String result = "";
        
        BufferedReader in = null;

        try
        {
            Process p = Runtime.getRuntime().exec( cmd );

            in = new BufferedReader( new InputStreamReader( p.getInputStream() ) );

            while ( true )
            {
                String line = in.readLine();

                if ( line == null )
                {
                    break;
                }

                result += processLine( line );
            }
        }
                
        finally
        {
            if ( in != null )
            {
                in.close();
            }
        }
        
        return result;
    }
    
    /**
     * processLine
     * 
     * @param line String
     * @return String
     */
    private static String processLine( String line )
    {
        if ( line.contains( "-------------" ) )
        {
            line = "";
        }
                
        else if ( !line.isEmpty() )
        {
            line += "\n";
        }

        return line;
    }
}