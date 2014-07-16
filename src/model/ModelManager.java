package model;

import model.service.CommitModelService;

/**
 *
 * @author Ivan lampert
 */
public class ModelManager 
{
    private final CommitModel commitModel = new CommitModelService();
    
    private static ModelManager defaultInstance;
    
    /**
     * getInstance
     * 
     * @return 
     */
    public static ModelManager getInstance()
    {
        if ( defaultInstance == null )
        {
            defaultInstance = new ModelManager();
        }
        
        return defaultInstance;
    }

    /**
     * ModelManager
     * 
     */
    private ModelManager() 
    {
    }
    
    /**
     * getCommitModel
     * 
     * @return 
     */
    public CommitModel getCommitModel()
    {
        return commitModel;
    }
}
