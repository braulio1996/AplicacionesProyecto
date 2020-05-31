package ec.edu.ups.Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transferencia {
	@Id
	@GeneratedValue
	private int codigo;
	private String tipo;
	private String fecha;
	private String institucion;
	private String tipoCuenta;
	private String numeroCuenta;
	private Double monto;
	private String identificacion;
	private String nombre;
	private String correo;
	private String concepto;
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getInstitucion() {
		return institucion;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	@Override
	public String toString() {
		return "Transferencia [codigo=" + codigo + ", tipo=" + tipo + ", fecha=" + fecha + ", institucion="
				+ institucion + ", tipoCuenta=" + tipoCuenta + ", numeroCuenta=" + numeroCuenta + ", monto=" + monto
				+ ", identificacion=" + identificacion + ", nombre=" + nombre + ", correo=" + correo + ", concepto="
				+ concepto + "]";
	}
	
		

}
