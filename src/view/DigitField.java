package view;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author AC
 */
public class DigitField
        extends JTextField
{

    /**
     * DigitField
     * 
     */
    public DigitField()
    {
        setDocument( new PlainDocument()
        {
            @Override
            /**
             * insertString
             * 
             * @param offs int
             * @param str String
             * @param a AttributeSet
             * @throws BadLocationException
             */
            public void insertString( int offs, String str, AttributeSet a ) throws BadLocationException
            {
                String value = str;

                for ( int i = 0; i < value.length(); i++ )
                {
                    if ( !Character.isDigit( value.charAt( i ) ) )
                    {
                        String  en = String.valueOf( value.charAt( i ) );
                        
                        str = str.replace( en, "" );
                    }
                }

                super.insertString( offs, str, a );
            }
        } );
    }
}
