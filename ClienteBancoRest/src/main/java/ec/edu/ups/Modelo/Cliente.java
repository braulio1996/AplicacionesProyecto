package ec.edu.ups.Modelo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente extends Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String telefono;


	private Cuenta cuenta;

	private List<Acceso> accesos;

	private List<SolicitudCredito> solicitudesCredito;

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
