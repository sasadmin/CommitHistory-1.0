package model;

import data.Commit;

/**
 *
 * @author IL
 */
public interface CommitModel 
{
    public void saveCommit( Commit commit ) throws Exception;
}
