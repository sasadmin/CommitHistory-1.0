package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author Alex Carvalho
 */
public class ConfigurationManager
{
    private static ConfigurationManager defaultInstance;
    
    private Properties defaultProperties = new Properties();
    
    private final String path = System.getProperty( "user.home" ) + File.separator + "CommitHistory.properties";

    /**
     * @return ConfigurationManager
     */
    public static synchronized ConfigurationManager getInstance()
    {
        if ( defaultInstance == null )
        {
            defaultInstance = new ConfigurationManager();
        }

        return defaultInstance;
    }

    /**
     * ConfigurationManager
     * 
     */
    private ConfigurationManager()
    {
        try
        {
            load();
        }
        catch ( Exception e )
        {
            ApplicationController.getInstance().handleException( e );
        }
    }

    /**
     * load
     * 
     * @throws Exception
     */
    private void load() throws Exception
    {
        File file = new File( path );

        if ( file.exists() )
        {
            FileInputStream in = null;

            try
            {
                in = new FileInputStream( file );

                defaultProperties.load( in );
            }
                    
            finally
            {
                if ( in != null )
                {
                    in.close();
                }
            }
        }
    }

    /**
     * save
     * 
     * @throws Exception
     */
    public void save() throws Exception
    {
        FileOutputStream out = null;

        try
        {
            out = new FileOutputStream( path );

            defaultProperties.store( out, null );
        }
                
        finally
        {
            if ( out != null )
            {
                out.close();
            }
        }
    }
    
    /**
     * getProperty
     * 
     * @param key String
     * @return String
     * @ignored 
     */
    public String getProperty ( String key )
    {
        return getProperty( key, "" );
    }
    
    /**
     * getProperty
     * 
     * @param key String
     * @param valueDefault String
     * @return String
     * @ignored 
     */
    public String getProperty ( String key, String valueDefault )
    {
        String property = defaultProperties.getProperty( key );
        
        return property != null ? property : valueDefault;
    }
    
    /**
     * setProperty
     * 
     * @param key String
     * @param value String
     * @ignored 
     */
    public void setProperty ( String key, String value )
    {
        defaultProperties.put( key, value );
    }
}
