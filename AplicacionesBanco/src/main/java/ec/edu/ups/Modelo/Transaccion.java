package ec.edu.ups.Modelo;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 *Esta clase Define los atributos de la entidad Transaccion                 
 * @version: 27/05/2020
 * @author: Braulio Castro
 * 
 */

@Entity
public class Transaccion {
	@Id
	@GeneratedValue
	private int codigo;
	private String tipo;
	private Date fecha;
	private Double monto;	

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cliente cliente;

	private String depositante;

	@ManyToOne
	private Cajero cajero;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getDepositante() {
		return depositante;
	}
	public void setDepositante(String depositante) {
		this.depositante = depositante;
	}
	public Cajero getCajero() {
		return cajero;
	}
	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}
	@Override
	public String toString() {
		return "Transaccion [codigo=" + codigo + ", tipo=" + tipo + ", fecha=" + fecha + ", monto=" + monto
				+ ", depositante=" + depositante + "]";
	}
	
}
