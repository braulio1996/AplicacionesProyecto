package ec.edu.ups.Modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
/**
*Esta clase hija hereda atributos de la clase Persona
* @version: 27/05/2020
* @author: Braulio Castro
*/

@Entity

public class Cliente extends Persona {
	private String telefono;

	@OneToOne(cascade = CascadeType.ALL)
	private Cuenta cuenta;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Acceso> accesos;
	@OneToMany(cascade = CascadeType.ALL)
	private List<SolicitudCredito> solicitudesCredito;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Transaccion>transacciones;
	
		
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public List<Acceso> getAccesos() {
		return accesos;
	}

	public void setAccesos(List<Acceso> accesos) {
		this.accesos = accesos;
	}

	public List<SolicitudCredito> getSolicitudesCredito() {
		return solicitudesCredito;
	}

	public void setSolicitudesCredito(List<SolicitudCredito> solicitudesCredito) {
		this.solicitudesCredito = solicitudesCredito;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}


	
}
