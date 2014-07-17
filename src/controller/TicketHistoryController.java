package controller;

import com.interact.ant.TicketHistory;
import data.Commit;
import java.security.Permission;
import util.Display;

/**
 *
 * @author AC
 */
public class TicketHistoryController
{
    /**
     * assign
     * 
     * @param commit Commit
     */
    public static void assign( Commit commit )
    {
        System.setSecurityManager( new MySecurityManager() );

        try
        {
            String[] parameters = new String[]
            {
                "assign", commit.getTicket(), commit.getVersion(), commit.getRevision()
            };

            TicketHistory.main( parameters );
        }
                
        catch ( SecurityException e )
        {
            //Do something if the external code used System.exit()
        }
                
        catch ( Exception e )
        {
            Display.exception( e.getLocalizedMessage() );
        }
                
        finally
        {
            System.setSecurityManager( null );
        }
    }

    private static class MySecurityManager extends SecurityManager
    {
        /**
         * checkExit
         * 
         * @param status int
         */
        @Override
        public void checkExit( int status )
        {
            throw new SecurityException();
        }

        @Override
        public void checkPermission( Permission perm )
        {
        }

        @Override
        public void checkPermission( Permission perm, Object context )
        {
        }
    }
}