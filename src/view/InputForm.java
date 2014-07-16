/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

/**
 *
 * @author Usuario
 * @param <T>
 */
public interface InputForm<T>
{
    public boolean validateInput();
    
    public T obtainInput();
    
    public void setInput( T value );
}
