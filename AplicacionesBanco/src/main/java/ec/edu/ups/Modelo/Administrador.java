package ec.edu.ups.Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Administrador extends Persona {
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cajero> cajeros;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<JefeCredito> creditos;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cliente> clientes;

	public List<Cajero> getCajeros() {
		return cajeros;
	}

	public void setCajeros(List<Cajero> cajeros) {
		this.cajeros = cajeros;
	}

	public List<JefeCredito> getCreditos() {
		return creditos;
	}

	public void setCreditos(List<JefeCredito> creditos) {
		this.creditos = creditos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
