// $Id: MapflowAppException.java,v 1.4 2005/09/20 11:17:39 ArturW Exp $
package com.mapflow.geo.common.exceptions;

import java.lang.RuntimeException;
import java.text.ParseException;


/**
 * @author Dave Conway
 */
public class MapflowAppException extends Exception {

    public MapflowAppException(String message) {
        super(message);
    }

    public MapflowAppException(String message, Throwable ex) {
        super(message, ex);
    }  
    
    public MapflowAppException(RuntimeException e, String message) {
        super(e);
    } 
    
    public MapflowAppException(ParseException e, String message) {
        super(e);
    } 
}
