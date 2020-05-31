package ec.edu.ups.Modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cuenta {
	@Id
	@GeneratedValue
	private int codigo;
	private String numero;
	private Double saldo;
	@OneToMany
	private List<Transferencia> trasferencias;
	@OneToOne
	private Cliente cliente;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public List<Transferencia> getTrasferencias() {
		return trasferencias;
	}

	public void setTrasferencias(List<Transferencia> trasferencias) {
		this.trasferencias = trasferencias;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
