
package ec.ups.edu.SOAP;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudCredito complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudCredito">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fotoCedulaF" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="fotoCedulaT" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="fotoPlantilla" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="fotoRolPagos" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
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
@XmlType(name = "solicitudCredito", propOrder = {
    "codigo",
    "estado",
    "fotoCedulaF",
    "fotoCedulaT",
    "fotoPlantilla",
    "fotoRolPagos",
    "monto",
    "tipo"
})
public class SolicitudCredito {

    protected int codigo;
    protected String estado;
    protected byte[] fotoCedulaF;
    protected byte[] fotoCedulaT;
    protected byte[] fotoPlantilla;
    protected byte[] fotoRolPagos;
    protected Double monto;
    protected String tipo;

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
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad fotoCedulaF.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFotoCedulaF() {
        return fotoCedulaF;
    }

    /**
     * Define el valor de la propiedad fotoCedulaF.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFotoCedulaF(byte[] value) {
        this.fotoCedulaF = value;
    }

    /**
     * Obtiene el valor de la propiedad fotoCedulaT.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFotoCedulaT() {
        return fotoCedulaT;
    }

    /**
     * Define el valor de la propiedad fotoCedulaT.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFotoCedulaT(byte[] value) {
        this.fotoCedulaT = value;
    }

    /**
     * Obtiene el valor de la propiedad fotoPlantilla.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFotoPlantilla() {
        return fotoPlantilla;
    }

    /**
     * Define el valor de la propiedad fotoPlantilla.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFotoPlantilla(byte[] value) {
        this.fotoPlantilla = value;
    }

    /**
     * Obtiene el valor de la propiedad fotoRolPagos.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFotoRolPagos() {
        return fotoRolPagos;
    }

    /**
     * Define el valor de la propiedad fotoRolPagos.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFotoRolPagos(byte[] value) {
        this.fotoRolPagos = value;
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
