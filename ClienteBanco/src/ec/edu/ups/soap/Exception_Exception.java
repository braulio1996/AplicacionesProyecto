
package ec.edu.ups.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.11
 * 2020-06-24T17:37:27.418-05:00
 * Generated source version: 2.7.11
 */

@WebFault(name = "Exception", targetNamespace = "http://Services.ups.edu.ec/")
public class Exception_Exception extends java.lang.Exception {
    
    private ec.edu.ups.soap.Exception exception;

    public Exception_Exception() {
        super();
    }
    
    public Exception_Exception(String message) {
        super(message);
    }
    
    public Exception_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception_Exception(String message, ec.edu.ups.soap.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public Exception_Exception(String message, ec.edu.ups.soap.Exception exception, Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public ec.edu.ups.soap.Exception getFaultInfo() {
        return this.exception;
    }
}
