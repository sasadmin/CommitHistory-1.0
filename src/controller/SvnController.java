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
        String[] cmdWork =       { "svn", "log", "-r", commit.getRevision(), "-v", WORK };
        String[] cmdQuarentine = { "svn", "log", "-r", commit.getRevision(), "-v", QUARENTINE };
        String[] cmdReleases =   { "svn", "log", "-r", commit.getRevision(), "-v", RELEASES };
        
        return obtainLogCommit( cmdWork ) + obtainLogCommit( cmdQuarentine ) + obtainLogCommit( cmdReleases );
    }
    
    /**
     * obtainLogCommit
     * 
     * @param cmd String[]
     * @return String
     * @throws Exception
     */
    public static String obtainLogCommit( String[] cmd ) throws Exception
    {
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