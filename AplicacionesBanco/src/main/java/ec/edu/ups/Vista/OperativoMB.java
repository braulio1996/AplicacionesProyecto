package ec.edu.ups.Vista;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.Modelo.Administrador;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.JefeCredito;
import ec.edu.ups.ON.AdministradorON;
import ec.edu.ups.ON.CajeroON;
import ec.edu.ups.ON.CreditoON;

@ManagedBean(name="operativo")
@ApplicationScoped
public class OperativoMB {
	@Inject
	private CreditoON crediON;

	@Inject
	private CajeroON cajeON;
	
	@Inject
	private AdministradorON adminON;
	
	private JefeCredito credito;
	private Cajero cajero;
	private String cedula;
	private String nombre;
	private String rol;
	private String correo;
	private String clave;
	private String Direccion;
	private Administrador admin;
	private List<Cajero> cajeros;
	private List<JefeCredito> creditos;
	
	
	@PostConstruct
	public void init() {
		credito = new JefeCredito();
		cajero = new Cajero();
		admin=new Administrador();
		cajeros = new ArrayList<>();
		creditos = new ArrayList<>();
	}

    
	public List<Cajero> getCajeros() {
		return cajeros;
	}


	public void setCajeros(List<Cajero> cajeros) {
		this.cajeros = cajeros;
	}


	public List<JefeCredito> getCreditos() {
		return creditos;
	}


	public void setCreditos(List<JefeCredito> creditos) {
		this.creditos = creditos;
	}


	public Administrador getAdmin() {
		return admin;
	}


	public void setAdmin(Administrador admin) {
		this.admin = admin;
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
	
	public String registrarUsuario(Administrador administrador) {
		System.out.println("Rol: "+this.rol);
		
		if (this.rol.equalsIgnoreCase("Cajero")) {
			try {
				cajero.setCedula(this.getCedula());
				cajero.setNombre(this.getNombre());
				cajero.setTipo(this.getRol());
				cajero.setNombre(this.getNombre());
				cajero.setDireccion(this.getDireccion());
				cajero.setCorreo(this.getCorreo());
			
				//cajeON.guardar(cajero);
				cajeros.add(cajero);
				administrador.setCajeros(cajeros);
				adminON.update(administrador);
				cajero = new Cajero();
				cajeros.clear();
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
				creditos.add(credito);
				administrador.setCreditos(creditos);
				adminON.update(administrador);
				credito = new JefeCredito();
				creditos.clear();
				//crediON.guardar(credito);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "inicioAdmin";
	}
	public List<JefeCredito> listarCredito() throws Exception {
		return crediON.listar();
	}

	public List<Cajero> listarCajero() throws Exception {
		return cajeON.listar();
	}
	public String editarAjaxCajero(String cedula) {
		System.out.println(cedula);
		try {
			cajero = cajeON.buscar(cedula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String editarAjaxCredito(String cedula) {
		System.out.println(cedula);
		try {
			credito = crediON.buscar(cedula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String ediatrCajero() throws Exception {
		cajeON.editar(cajero);
		return null;
	}

	public String ediatrCredito() throws Exception {
		crediON.editar(credito);
		return null;
	}
	public String eliminarCredito(String cedula) throws Exception {
		System.out.println("=======================  " + cedula);
		crediON.eliminar(cedula);
		return null;
	}

	public String eliminarCajero(String cedula) throws Exception {
		System.out.println("=======================  " + cedula);
		cajeON.eliminar(cedula);
		return null;
	}
}
