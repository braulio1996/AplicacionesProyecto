package ec.edu.ups.Modelo;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    private int codigo;
   
    private int numeroPago;
    private Date fechaVencimiento;
    private double capital;
    private double interes;
    private double pago;
    private double pagoCapital;
    private double saldo;
    @OneToOne
    private CreditoAprobado credito;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getNumeroPago() {
		return numeroPago;
	}
	public void setNumeroPago(int numeroPago) {
		this.numeroPago = numeroPago;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	public double getInteres() {
		return interes;
	}
	public void setInteres(double interes) {
		this.interes = interes;
	}
	public double getPago() {
		return pago;
	}
	public void setPago(double pago) {
		this.pago = pago;
	}
	public double getPagoCapital() {
		return pagoCapital;
	}
	public void setPagoCapital(double pagoCapital) {
		this.pagoCapital = pagoCapital;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public CreditoAprobado getCredito() {
		return credito;
	}
	public void setCredito(CreditoAprobado credito) {
		this.credito = credito;
	}

    
    
  
}
