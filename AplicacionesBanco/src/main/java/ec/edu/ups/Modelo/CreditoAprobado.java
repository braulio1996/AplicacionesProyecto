package ec.edu.ups.Modelo;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class CreditoAprobado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int numero;
	private String tipo;
	private double monto;
	private Date Fecha;
	@OneToOne
	private Cliente cliente;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Amortizacion> amortizacion;
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Amortizacion> getAmortizacion() {
		return amortizacion;
	}
	public void setAmortizacion(List<Amortizacion> amortizacion) {
		this.amortizacion = amortizacion;
	}
	
	
	
	

}
