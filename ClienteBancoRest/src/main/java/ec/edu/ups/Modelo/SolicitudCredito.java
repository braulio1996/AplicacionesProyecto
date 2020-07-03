package ec.edu.ups.Modelo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SolicitudCredito implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String tipo;
	private Double monto;
	private String estado;
	private byte[] fotoCedulaF;
	private byte[] fotoCedulaT;
	private byte[] fotoPlantilla;
	private byte[] fotoRolPagos;

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

}
