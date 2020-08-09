package ec.edu.ups.Modelo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 *Esta clase Define los atributos de la entidad Solicitud Credito                  
 * @version: 27/05/2020
 * @author: Braulio Castro
 * 
 */
@Entity
public class SolicitudCredito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String tipo;
	private Double monto;
	private int meses;
	private double ingreso;
	private double egreso;
	private String estado;
	private String observaciones;
	private String fecha;
	
	@Lob
	private byte[] fotoCedulaF;
	@Lob
	private byte[] fotoCedulaT;
	@Lob
	private byte[] fotoPlantilla;
	@Lob
	private byte[] fotoRolPagos;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cliente cliente;
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

	
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public byte[] getFotoCedulaF() {
		return fotoCedulaF;
	}

	public void setFotoCedulaF(byte[] fotoCedulaF) {
		this.fotoCedulaF = fotoCedulaF;
	}

	public byte[] getFotoCedulaT() {
		return fotoCedulaT;
	}

	public void setFotoCedulaT(byte[] fotoCedulaT) {
		this.fotoCedulaT = fotoCedulaT;
	}

	public byte[] getFotoPlantilla() {
		return fotoPlantilla;
	}

	public void setFotoPlantilla(byte[] fotoPlantilla) {
		this.fotoPlantilla = fotoPlantilla;
	}

	public byte[] getFotoRolPagos() {
		return fotoRolPagos;
	}

	public void setFotoRolPagos(byte[] fotoRolPagos) {
		this.fotoRolPagos = fotoRolPagos;
	}

	public int getMeses() {
		return meses;
	}

	public void setMeses(int meses) {
		this.meses = meses;
	}

	public double getIngreso() {
		return ingreso;
	}

	public void setIngreso(double ingreso) {
		this.ingreso = ingreso;
	}

	public double getEgreso() {
		return egreso;
	}

	public void setEgreso(double egreso) {
		this.egreso = egreso;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
