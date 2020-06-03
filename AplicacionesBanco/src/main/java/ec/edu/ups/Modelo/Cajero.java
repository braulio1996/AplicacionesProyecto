package ec.edu.ups.Modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Cajero extends Persona {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Transaccion>transacciones;

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}
	
	
}
