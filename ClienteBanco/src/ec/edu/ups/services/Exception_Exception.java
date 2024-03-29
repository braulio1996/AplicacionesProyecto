
package ec.edu.ups.services;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.11
 * 2020-07-08T18:09:33.635-05:00
 * Generated source version: 2.7.11
 */

@WebFault(name = "Exception", targetNamespace = "http://Services.ups.edu.ec/")
public class Exception_Exception extends java.lang.Exception {
    
    private ec.edu.ups.services.Exception exception;

    public Exception_Exception() {
        super();
    }
    
    public Exception_Exception(String message) {
        super(message);
    }
    
    public Exception_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception_Exception(String message, ec.edu.ups.services.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public Exception_Exception(String message, ec.edu.ups.services.Exception exception, Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public ec.edu.ups.services.Exception getFaultInfo() {
        return this.exception;
    }
}
