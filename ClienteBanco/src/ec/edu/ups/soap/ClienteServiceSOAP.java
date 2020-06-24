package ec.edu.ups.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2020-06-24T17:37:27.441-05:00
 * Generated source version: 2.7.11
 * 
 */
@WebService(targetNamespace = "http://Services.ups.edu.ec/", name = "ClienteServiceSOAP")
@XmlSeeAlso({ObjectFactory.class})
public interface ClienteServiceSOAP {

    @WebMethod
    @RequestWrapper(localName = "deposito", targetNamespace = "http://Services.ups.edu.ec/", className = "ec.edu.ups.soap.Deposito")
    @ResponseWrapper(localName = "depositoResponse", targetNamespace = "http://Services.ups.edu.ec/", className = "ec.edu.ups.soap.DepositoResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ec.edu.ups.soap.Respuesta deposito(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.Double arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3
    );

    @WebMethod
    @RequestWrapper(localName = "retiro", targetNamespace = "http://Services.ups.edu.ec/", className = "ec.edu.ups.soap.Retiro")
    @ResponseWrapper(localName = "retiroResponse", targetNamespace = "http://Services.ups.edu.ec/", className = "ec.edu.ups.soap.RetiroResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ec.edu.ups.soap.Respuesta retiro(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.Double arg2
    );

    @WebMethod
    @RequestWrapper(localName = "transferencia", targetNamespace = "http://Services.ups.edu.ec/", className = "ec.edu.ups.soap.Transferencia")
    @ResponseWrapper(localName = "transferenciaResponse", targetNamespace = "http://Services.ups.edu.ec/", className = "ec.edu.ups.soap.TransferenciaResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ec.edu.ups.soap.Respuesta transferencia(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.Double arg2
    );

    @WebMethod
    @RequestWrapper(localName = "login", targetNamespace = "http://Services.ups.edu.ec/", className = "ec.edu.ups.soap.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://Services.ups.edu.ec/", className = "ec.edu.ups.soap.LoginResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ec.edu.ups.soap.Respuesta login(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws Exception_Exception;
}
