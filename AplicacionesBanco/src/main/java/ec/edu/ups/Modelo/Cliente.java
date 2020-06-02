package ec.edu.ups.Modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente extends Persona {
	private String telefono;

	@OneToOne(cascade = CascadeType.ALL)
	private Cuenta cuenta;
	@OneToMany
	private List<Acceso> accesos;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SolicitudCredito> solicitudesCredito;

	
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

}
