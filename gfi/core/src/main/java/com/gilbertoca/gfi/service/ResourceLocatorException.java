/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilbertoca.gfi.service;

/**
 *
 * @author gilberto
 */
public class ResourceLocatorException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ResourceLocatorException() {} 
    public ResourceLocatorException(final String msg) { super(msg); }
    public ResourceLocatorException(final String msg, final Throwable cause) { super(msg, cause); }
    public ResourceLocatorException(final Throwable cause) { super(cause); }
}