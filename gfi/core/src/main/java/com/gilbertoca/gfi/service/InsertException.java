package com.gilbertoca.gfi.service;

/**
 * An exception that is thrown by classes wanting to find object so it's checked in the web layer.
 *
 * <p><a href="InsertException.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:gilbertoca@gmail.com">Gilberto</a>
 */
public class InsertException extends Exception {

    /** 
     * Constructor for InsertException.
     *
     * @param message
     */
    public InsertException(String message) {
        super(message);
    }
}
