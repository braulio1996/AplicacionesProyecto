package ec.edu.ups.Vista;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.JefeCredito;
import ec.edu.ups.ON.CajeroON;
import ec.edu.ups.ON.CreditoON;

@ManagedBean(name="operativo")
@ApplicationScoped
public class OperativoMB {
	@Inject
	private CreditoON crediON;

	@Inject
	private CajeroON cajeON;
	
	private JefeCredito credito;
	private Cajero cajero;
	private String cedula;
	private String nombre;
	private String rol;
	private String correo;
	private String clave;
	private String Direccion;
	
	
	@PostConstruct
	public void init() {
		credito = new JefeCredito();
		cajero = new Cajero();
	}


	public JefeCredito getCredito() {
		return credito;
	}


	public void setCredito(JefeCredito credito) {
		this.credito = credito;
	}


	public Cajero getCajero() {
		return cajero;
	}


	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public String getDireccion() {
		return Direccion;
	}


	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	
	public String registrarUsuario() {
		System.out.println("Rol: "+this.rol);
		
		if (this.rol == "Cajero") {
			try {
				cajero.setCedula(this.getCedula());
				cajero.setNombre(this.getNombre());
				cajero.setTipo(this.getRol());
				cajero.setNombre(this.getNombre());
				cajero.setDireccion(this.getDireccion());
				cajero.setCorreo(this.getCorreo());
			
				cajeON.guardar(cajero);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				credito.setCedula(this.getCedula());
				credito.setNombre(this.getNombre());
				credito.setTipo(this.getRol());
				credito.setNombre(this.getNombre());
				credito.setDireccion(this.getDireccion());
				credito.setCorreo(this.getCorreo());
			
				crediON.guardar(credito);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "inicioAdmin";
	}
	
}
