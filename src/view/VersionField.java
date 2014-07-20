/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

/**
 *
 * @author Galimberti
 */
public class VersionField
    extends AutoCompleteTextField
{
    public VersionField()
    {
        addPossibility( "SA-WEB-7.0.0.0" );
        addPossibility( "SA-DESKTOP-7.0.0.0" );
    }
}
