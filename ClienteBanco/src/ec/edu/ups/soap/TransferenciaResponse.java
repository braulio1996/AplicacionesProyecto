
package ec.edu.ups.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
<<<<<<< HEAD:ClienteBanco/src/ec/edu/ups/soap/TransferenciaResponse.java
 * <p>Clase Java para transferenciaResponse complex type.
=======
 * <p>Clase Java para transferencia2Response complex type.
>>>>>>> master:ClienteBanco/src/ec/edu/ups/soap/Transferencia2Response.java
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
<<<<<<< HEAD:ClienteBanco/src/ec/edu/ups/soap/TransferenciaResponse.java
 * &lt;complexType name="transferenciaResponse">
=======
 * &lt;complexType name="transferencia2Response">
>>>>>>> master:ClienteBanco/src/ec/edu/ups/soap/Transferencia2Response.java
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://Services.ups.edu.ec/}respuesta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
<<<<<<< HEAD:ClienteBanco/src/ec/edu/ups/soap/TransferenciaResponse.java
@XmlType(name = "transferenciaResponse", propOrder = {
    "_return"
})
public class TransferenciaResponse {
=======
@XmlType(name = "transferencia2Response", propOrder = {
    "_return"
})
public class Transferencia2Response {
>>>>>>> master:ClienteBanco/src/ec/edu/ups/soap/Transferencia2Response.java

    @XmlElement(name = "return")
    protected Respuesta _return;

    /**
     * Obtiene el valor de la propiedad return.
     * 
     * @return
     *     possible object is
     *     {@link Respuesta }
     *     
     */
    public Respuesta getReturn() {
        return _return;
    }

    /**
     * Define el valor de la propiedad return.
     * 
     * @param value
     *     allowed object is
     *     {@link Respuesta }
     *     
     */
    public void setReturn(Respuesta value) {
        this._return = value;
    }

}
