package ec.edu.ups.Modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Administrador extends Persona {
	@OneToMany(fetch = FetchType.LAZY)
	private List<Cajero>cajeros;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Credito>creditos;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Cliente>clientes;
	public List<Cajero> getCajeros() {
		return cajeros;
	}
	public void setCajeros(List<Cajero> cajeros) {
		this.cajeros = cajeros;
	}
	public List<Credito> getCreditos() {
		return creditos;
	}
	public void setCreditos(List<Credito> creditos) {
		this.creditos = creditos;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
}
