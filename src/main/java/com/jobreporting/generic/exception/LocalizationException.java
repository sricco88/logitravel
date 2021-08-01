/*
 * Licensed To: ThoughtExecution & 9sistemes
 * Authored By: Rishi Raj Bansal
 * Developed in: Sep-Oct 2016
 *
 * ===========================================================================
 * This is FULLY owned and COPYRIGHTED by ThoughtExecution
 * This code may NOT be RESOLD or REDISTRIBUTED under any circumstances, and is only to be used with this application
 * Using the code from this application in another application is strictly PROHIBITED and not PERMISSIBLE
 * ===========================================================================
 */

package com.jobreporting.generic.exception;

import com.jobreporting.generic.exception.base.ExceptionDetail;

public class LocalizationException extends ApplicationException {
	
	protected Throwable throwable = null;
	
	/**
     * Constructor for LocalizationException
     * @param msg - Message associated with the exception
     */
    public LocalizationException(String msg) {
    	super(msg);
    	error = msg;
    }

    /**
     * Initializes a newly created <code>LocalizationException</code> object.
     * @param	msg - the message associated with the Exception.
     * @param   cause - Throwable object
     */
    public LocalizationException(String msg, Throwable cause) {
    	super(msg, cause);
    }
    
    /**
     * Constructor for LocalizationException to set exceptionDetail
     */
    public LocalizationException(ExceptionDetail exceptionDetail) {
            super(exceptionDetail);
    }





}
