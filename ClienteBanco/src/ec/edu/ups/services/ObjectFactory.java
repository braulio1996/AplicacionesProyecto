
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
    private final static QName _TransferenciaResponse_QNAME = new QName("http://Services.ups.edu.ec/", "transferenciaResponse");
    private final static QName _Transferencia_QNAME = new QName("http://Services.ups.edu.ec/", "transferencia");
    private final static QName _DepositoResponse_QNAME = new QName("http://Services.ups.edu.ec/", "depositoResponse");
    private final static QName _RetiroResponse_QNAME = new QName("http://Services.ups.edu.ec/", "retiroResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.edu.ups.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Transferencia }
     * 
     */
    public Transferencia createTransferencia() {
        return new Transferencia();
    }

    /**
     * Create an instance of {@link DepositoResponse }
     * 
     */
    public DepositoResponse createDepositoResponse() {
        return new DepositoResponse();
    }

    /**
     * Create an instance of {@link RetiroResponse }
     * 
     */
    public RetiroResponse createRetiroResponse() {
        return new RetiroResponse();
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
     * Create an instance of {@link TransferenciaResponse }
     * 
     */
    public TransferenciaResponse createTransferenciaResponse() {
        return new TransferenciaResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferenciaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services.ups.edu.ec/", name = "transferenciaResponse")
    public JAXBElement<TransferenciaResponse> createTransferenciaResponse(TransferenciaResponse value) {
        return new JAXBElement<TransferenciaResponse>(_TransferenciaResponse_QNAME, TransferenciaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Transferencia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services.ups.edu.ec/", name = "transferencia")
    public JAXBElement<Transferencia> createTransferencia(Transferencia value) {
        return new JAXBElement<Transferencia>(_Transferencia_QNAME, Transferencia.class, null, value);
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
