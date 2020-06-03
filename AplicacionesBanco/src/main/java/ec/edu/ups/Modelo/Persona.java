package ec.edu.ups.Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *Esta clase Define los atributos de la entidad Persona                  
 * @version: 27/05/2020
 * @author: Braulio Castro
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Persona {
	@Id
	@GeneratedValue()
	private int codigo;
	private String cedula;
	private String nombre;
	private String direccion;
	private String correo;
	private String clave;
	private String tipo;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 *String de la clace objeto Persona
	 */
	@Override
	public String toString() {
		return "Persona [codigo=" + codigo + ", cedula=" + cedula + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", correo=" + correo + ", clave=" + clave + ", tipo=" + tipo + "]";
	}

}
