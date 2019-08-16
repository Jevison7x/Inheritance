/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance.rules;

/**
 * An exception written for the sole purpose of this application to be thrown when neccessary
 *
 * @author Jevison7x
 */
public class InheritanceException extends Exception
{
    /**
     * The exception's sole constructor
     *
     * @param message the exception must have a message to display.
     */
    public InheritanceException(String message)
    {
        super(message);
    }
}
