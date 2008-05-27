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
    public ResourceLocatorException() {} 
    public ResourceLocatorException(String msg) { super(msg); }
    public ResourceLocatorException(String msg, Throwable cause) { super(msg, cause); }
    public ResourceLocatorException(Throwable cause) { super(cause); }
}