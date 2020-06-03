package ec.edu.ups.Modelo;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *Esta clase Define los atributos de la entidad Amortizacion
 * @version: 02/06/2020
 * @author: Marcela Zhagui
 *
 */

@Entity
public class Amortizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAmortizacion;
    @ManyToOne(optional = false)
    private Cuenta idCuenta;
    private int periodo;
    private double interes;
    private double capitalSin;
    private double couta;
    private double deuda;
    private String estado;
    private Date fecha;

    
    
    
	public int getIdAmortizacion() {
		return idAmortizacion;
	}
	public void setIdAmortizacion(int idAmortizacion) {
		this.idAmortizacion = idAmortizacion;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public double getInteres() {
		return interes;
	}
	public void setInteres(double interes) {
		this.interes = interes;
	}
	public double getCapitalSin() {
		return capitalSin;
	}
	public void setCapitalSin(double capitalSin) {
		this.capitalSin = capitalSin;
	}
	public double getCouta() {
		return couta;
	}
	public void setCouta(double couta) {
		this.couta = couta;
	}
	public double getDeuda() {
		return deuda;
	}
	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Cuenta getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(Cuenta idCuenta) {
		this.idCuenta = idCuenta;
	}
	@Override
	public String toString() {
		return "Amortizacion [idAmortizacion=" + idAmortizacion + ", periodo=" + periodo + ", interes=" + interes
				+ ", capitalSin=" + capitalSin + ", couta=" + couta + ", deuda=" + deuda + ", estado=" + estado
				+ ", fecha=" + fecha + ", idCuenta=" + idCuenta + "]";
	}
    
    
    
    
}
