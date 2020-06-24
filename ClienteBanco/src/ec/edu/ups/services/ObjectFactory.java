
package ec.edu.ups.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.edu.ups.services package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Deposito_QNAME = new QName("http://Services.ups.edu.ec/", "deposito");
    private final static QName _Retiro_QNAME = new QName("http://Services.ups.edu.ec/", "retiro");
    private final static QName _Transferencia2Response_QNAME = new QName("http://Services.ups.edu.ec/", "transferencia2Response");
    private final static QName _Transferencia2_QNAME = new QName("http://Services.ups.edu.ec/", "transferencia2");
    private final static QName _Exception_QNAME = new QName("http://Services.ups.edu.ec/", "Exception");
    private final static QName _Login_QNAME = new QName("http://Services.ups.edu.ec/", "login");
    private final static QName _LoginResponse_QNAME = new QName("http://Services.ups.edu.ec/", "loginResponse");
    private final static QName _DepositoResponse_QNAME = new QName("http://Services.ups.edu.ec/", "depositoResponse");
    private final static QName _RetiroResponse_QNAME = new QName("http://Services.ups.edu.ec/", "retiroResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.edu.ups.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DepositoResponse }
     * 
     */
    public DepositoResponse createDepositoResponse() {
        return new DepositoResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link RetiroResponse }
     * 
     */
    public RetiroResponse createRetiroResponse() {
        return new RetiroResponse();
    }

    /**
     * Create an instance of {@link Transferencia2 }
     * 
     */
    public Transferencia2 createTransferencia2() {
        return new Transferencia2();
    }

    /**
     * Create an instance of {@link Deposito }
     * 
     */
    public Deposito createDeposito() {
        return new Deposito();
    }

    /**
     * Create an instance of {@link Retiro }
     * 
     */
    public Retiro createRetiro() {
        return new Retiro();
    }

    /**
     * Create an instance of {@link Transferencia2Response }
     * 
     */
    public Transferencia2Response createTransferencia2Response() {
        return new Transferencia2Response();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link Transferencia }
     * 
     */
    public Transferencia createTransferencia() {
        return new Transferencia();
    }

    /**
     * Create an instance of {@link Persona }
     * 
     */
    public Persona createPersona() {
        return new Persona();
    }

    /**
     * Create an instance of {@link Acceso }
     * 
     */
    public Acceso createAcceso() {
        return new Acceso();
    }

    /**
     * Create an instance of {@link Cajero }
     * 
     */
    public Cajero createCajero() {
        return new Cajero();
    }

    /**
     * Create an instance of {@link Cliente }
     * 
     */
    public Cliente createCliente() {
        return new Cliente();
    }

    /**
     * Create an instance of {@link Transaccion }
     * 
     */
    public Transaccion createTransaccion() {
        return new Transaccion();
    }

    /**
     * Create an instance of {@link SolicitudCredito }
     * 
     */
    public SolicitudCredito createSolicitudCredito() {
        return new SolicitudCredito();
    }

    /**
     * Create an instance of {@link Cuenta }
     * 
     */
    public Cuenta createCuenta() {
        return new Cuenta();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link Respuesta }
     * 
     */
    public Respuesta createRespuesta() {
        return new Respuesta();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Deposito }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services.ups.edu.ec/", name = "deposito")
    public JAXBElement<Deposito> createDeposito(Deposito value) {
        return new JAXBElement<Deposito>(_Deposito_QNAME, Deposito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Retiro }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services.ups.edu.ec/", name = "retiro")
    public JAXBElement<Retiro> createRetiro(Retiro value) {
        return new JAXBElement<Retiro>(_Retiro_QNAME, Retiro.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Transferencia2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services.ups.edu.ec/", name = "transferencia2Response")
    public JAXBElement<Transferencia2Response> createTransferencia2Response(Transferencia2Response value) {
        return new JAXBElement<Transferencia2Response>(_Transferencia2Response_QNAME, Transferencia2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Transferencia2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services.ups.edu.ec/", name = "transferencia2")
    public JAXBElement<Transferencia2> createTransferencia2(Transferencia2 value) {
        return new JAXBElement<Transferencia2>(_Transferencia2_QNAME, Transferencia2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services.ups.edu.ec/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services.ups.edu.ec/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services.ups.edu.ec/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepositoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services.ups.edu.ec/", name = "depositoResponse")
    public JAXBElement<DepositoResponse> createDepositoResponse(DepositoResponse value) {
        return new JAXBElement<DepositoResponse>(_DepositoResponse_QNAME, DepositoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetiroResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services.ups.edu.ec/", name = "retiroResponse")
    public JAXBElement<RetiroResponse> createRetiroResponse(RetiroResponse value) {
        return new JAXBElement<RetiroResponse>(_RetiroResponse_QNAME, RetiroResponse.class, null, value);
    }

}
