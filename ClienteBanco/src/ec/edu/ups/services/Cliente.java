
package ec.edu.ups.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cliente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cliente">
 *   &lt;complexContent>
 *     &lt;extension base="{http://Services.ups.edu.ec/}persona">
 *       &lt;sequence>
 *         &lt;element name="accesos" type="{http://Services.ups.edu.ec/}acceso" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cuenta" type="{http://Services.ups.edu.ec/}cuenta" minOccurs="0"/>
 *         &lt;element name="solicitudesCredito" type="{http://Services.ups.edu.ec/}solicitudCredito" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transacciones" type="{http://Services.ups.edu.ec/}transaccion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cliente", propOrder = {
    "accesos",
    "cuenta",
    "solicitudesCredito",
    "telefono",
    "transacciones"
})
public class Cliente
    extends Persona
{

    @XmlElement(nillable = true)
    protected List<Acceso> accesos;
    protected Cuenta cuenta;
    @XmlElement(nillable = true)
    protected List<SolicitudCredito> solicitudesCredito;
    protected String telefono;
    @XmlElement(nillable = true)
    protected List<Transaccion> transacciones;

    /**
     * Gets the value of the accesos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accesos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccesos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Acceso }
     * 
     * 
     */
    public List<Acceso> getAccesos() {
        if (accesos == null) {
            accesos = new ArrayList<Acceso>();
        }
        return this.accesos;
    }

    /**
     * Obtiene el valor de la propiedad cuenta.
     * 
     * @return
     *     possible object is
     *     {@link Cuenta }
     *     
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * Define el valor de la propiedad cuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link Cuenta }
     *     
     */
    public void setCuenta(Cuenta value) {
        this.cuenta = value;
    }

    /**
     * Gets the value of the solicitudesCredito property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the solicitudesCredito property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSolicitudesCredito().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SolicitudCredito }
     * 
     * 
     */
    public List<SolicitudCredito> getSolicitudesCredito() {
        if (solicitudesCredito == null) {
            solicitudesCredito = new ArrayList<SolicitudCredito>();
        }
        return this.solicitudesCredito;
    }

    /**
     * Obtiene el valor de la propiedad telefono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Define el valor de la propiedad telefono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Gets the value of the transacciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transacciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransacciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Transaccion }
     * 
     * 
     */
    public List<Transaccion> getTransacciones() {
        if (transacciones == null) {
            transacciones = new ArrayList<Transaccion>();
        }
        return this.transacciones;
    }

}
