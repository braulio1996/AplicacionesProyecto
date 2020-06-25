
package ec.edu.ups.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para transaccion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="transaccion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cajero" type="{http://Services.ups.edu.ec/}cajero" minOccurs="0"/>
 *         &lt;element name="cliente" type="{http://Services.ups.edu.ec/}cliente" minOccurs="0"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="depositante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://Services.ups.edu.ec/}localDate" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="saldoCuenta" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transaccion", propOrder = {
    "cajero",
    "cliente",
    "codigo",
    "depositante",
    "fecha",
    "monto",
    "saldoCuenta",
    "tipo"
})
public class Transaccion {

    protected Cajero cajero;
    protected Cliente cliente;
    protected int codigo;
    protected String depositante;
    protected LocalDate fecha;
    protected Double monto;
    protected Double saldoCuenta;
    protected String tipo;

    /**
     * Obtiene el valor de la propiedad cajero.
     * 
     * @return
     *     possible object is
     *     {@link Cajero }
     *     
     */
    public Cajero getCajero() {
        return cajero;
    }

    /**
     * Define el valor de la propiedad cajero.
     * 
     * @param value
     *     allowed object is
     *     {@link Cajero }
     *     
     */
    public void setCajero(Cajero value) {
        this.cajero = value;
    }

    /**
     * Obtiene el valor de la propiedad cliente.
     * 
     * @return
     *     possible object is
     *     {@link Cliente }
     *     
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define el valor de la propiedad cliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Cliente }
     *     
     */
    public void setCliente(Cliente value) {
        this.cliente = value;
    }

    /**
     * Obtiene el valor de la propiedad codigo.
     * 
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     */
    public void setCodigo(int value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad depositante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepositante() {
        return depositante;
    }

    /**
     * Define el valor de la propiedad depositante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepositante(String value) {
        this.depositante = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFecha(LocalDate value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMonto(Double value) {
        this.monto = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoCuenta.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSaldoCuenta() {
        return saldoCuenta;
    }

    /**
     * Define el valor de la propiedad saldoCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSaldoCuenta(Double value) {
        this.saldoCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

}
