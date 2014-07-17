package util;

import controller.ConfigurationManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author AC
 */
public class FileUtilities
{
    private static int BUFFER_SIZE = 4096;

    /**
     *
     * @param file File
     * @param text String
     * @throws Exception
     */
    public static void saveText( File file, String text ) throws Exception
    {
        FileWriter fw = null;
        
        try
        {
            createFile( file );

            fw = new FileWriter( file );
            
            fw.write( text );
        }
                
        finally
        {
            if ( fw != null )
            {
                fw.close();
            }
        }
    }

    /**
     *
     * @param path String
     * @return File
     * @throws Exception
     */
    public static File createFile( String path ) throws Exception
    {
        File file = new File( path );

        createFile( file );

        return file;
    }

    /**
     *
     * @param file File
     * @return File
     * @throws Exception
     */
    public static File createFile( File file ) throws Exception
    {
        File parent = file.getParentFile();

        if ( ! parent.exists() )
        {
            List<File> dirs = new ArrayList<File>();

            dirs.add( parent );

            while ( true )
            {
                parent = parent.getParentFile();

                if ( parent == null )
                {
                    break;
                }

                if ( parent.exists() )
                {
                    break;
                }

                dirs.add( parent );
            }

            Collections.reverse( dirs );

            for ( File dir : dirs )
            {
                if ( ! dir.mkdir() )
                {
                    throw new Exception( "Unable to create directory: " + dir );
                }

                dir.setReadable( true, true );
                dir.setWritable( true, true );
                dir.setExecutable( true, true );
            }
        }

        if ( ! file.exists() )
        {
            if ( ! file.createNewFile() )
            {
                throw new Exception( "Unable to create file: " + file );
            }
        }

        file.setWritable( true, true );
        file.setReadable( true, true );

        return file;
    }

    /**
     *
     * @param url URL
     * @param target File
     * @throws Exception
     */
    public static void copyResource( URL url, File target ) throws Exception
    {
        createFile( target );

        InputStream in = url.openStream();
        OutputStream out = new FileOutputStream( target );

        copyData( in, out );

        in.close();
        out.close();
    }

    /**
     *
     * @param source
     * @param target
     */
    public static void copyFile( File source, File target ) throws Exception
    {
        copyFile( source, target, false );
    }

    /**
     * @param source File
     * @param target File
     * @param isReadOnly boolean
     * @throws IOException
     */
    public static void copyFile( File source, File target, boolean isReadOnly ) throws Exception
    {
        if ( !source.exists() )
        {
            throw new Exception( "File not found: " + source );
        }

        if ( source.equals( target ) )
        {
            throw new Exception( "Cannot copy file to itself: " + source );
        }

        if ( !source.canRead() )
        {
            throw new Exception( "Cannot read file: " + source );
        }

        File parent = target.getParentFile();

        if ( ! parent.exists() )
        {
            throw new Exception( "Directory not found: " + parent );
        }

        if ( ! parent.canWrite() )
        {
            throw new Exception( "Cannot create file: " + target );
        }

        if ( target.exists() )
        {
            target.delete();
        }

        try
        {
            createFile( target );

            FileInputStream in = new FileInputStream( source );

            try
            {
                FileOutputStream out = new FileOutputStream( target );

                try
                {
                    copyData( in, out );
                }

                finally
                {
                    if ( out != null )
                    {
                        out.close();
                    }
                }
            }

            finally
            {
                if ( in != null )
                {
                    in.close();
                }
            }
        }

        catch ( Exception e )
        {
            throw new Exception( "Copying file failed: " + source + " => " + target, e );
        }

        if ( source.length() != target.length() )
        {
            throw new Exception( "Source and target file have different length!" );
        }

        if ( !target.exists() )
        {
            throw new Exception( "Copying file failed: " + source + " => " + target );
        }

        if ( isReadOnly )
        {
            target.setReadOnly();
        }
    }

    /**
     *
     * @param in
     * @param out
     * @throws Exception
     */
    public static void copyData( InputStream in, OutputStream out ) throws Exception
    {
        BufferedInputStream bi = new BufferedInputStream( in );
        BufferedOutputStream bo = new BufferedOutputStream( out );

        byte buffer[] = new byte[BUFFER_SIZE];

        while ( true )
        {
            int n = bi.read( buffer );

            if ( n <= 0 )
            {
                break;
            }

            bo.write( buffer, 0, n );
        }

        bo.flush();
    }

    /**
     *
     * @param data
     * @param target
     * @throws Exception
     */
    public static void copyData( byte[] data, File target ) throws Exception
    {
        createFile( target );

        FileOutputStream fos = new FileOutputStream( target );

        BufferedOutputStream bos = new BufferedOutputStream( fos );

        bos.write( data );

        bos.close();
        fos.close();
    }

    /**
     * loadText
     *
     * @param file File
     * @return String
     * @throws Exception
     */
    public static String loadText( File file ) throws Exception
    {
        String data = "";

        FileReader reader = null;

        try
        {
            reader = new FileReader( file );

            data = loadText( reader );
        }

        finally
        {
            if ( reader != null )
            {
                reader.close();
            }
        }

        return data;
    }

    /**
     * loadText
     *
     * @param reader Reader
     * @return String
     * @throws Exception
     */
    public static String loadText( Reader reader ) throws Exception
    {
        StringBuilder b = new StringBuilder();

        BufferedReader bufferedReader = null;

        try
        {
           bufferedReader = new BufferedReader( reader );
           
            while ( true )
            {
                String line = bufferedReader.readLine();

                if ( line == null )
                {
                    break;
                }

                b.append( line );
                b.append( "\n" );
            }
        }
        
        finally
        {
            if ( bufferedReader != null )
            {
                bufferedReader.close();
            }
        }

        return b.toString();
    }

    /**
     * replaceInvalidCharacters
     *
     * @param path String
     * @return String
     */
    public static String replaceInvalidCharacters( String path )
    {
        ConfigurationManager cm = ConfigurationManager.getInstance();

        String defaultInvalidCharacters = "!$*<>{}:?\"\'\\/|";

        String replace = cm.getProperty( "DocumentEditor.escapeCharacters", defaultInvalidCharacters );

        if ( replace == null || replace.isEmpty() )
        {
            replace = defaultInvalidCharacters;
        }

        StringBuilder buffer = new StringBuilder();

        for ( int i = 0; i < path.length(); i++ )
        {
            char c = path.charAt( i );

            if ( replace.indexOf( c ) != -1 )
            {
                buffer.append( '-' );
            }

            else
            {
                buffer.append( c );
            }
        }

        path = buffer.toString();

        return path;
    }
}