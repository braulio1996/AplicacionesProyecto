package ec.edu.ups.Vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.Modelo.Administrador;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.JefeCredito;
import ec.edu.ups.ON.AdministradorON;
import ec.edu.ups.ON.CajeroON;
import ec.edu.ups.ON.ClienteON;
import ec.edu.ups.ON.CreditoON;
/**
 * Esta Clase define los ManagedBean
 * @version: 02/06/2020
 * @author Braulio Castro
 *
 */
@ManagedBean(name = "operativo")
@ApplicationScoped
public class OperativoMB {
	@Inject
	private CreditoON crediON;

	@Inject
	private AdministradorON adminON;
	@Inject
	private ClienteON clieOn;
	@Inject
	private CajeroON cajeON;

	private JefeCredito credito;
	private Cliente cliente;
	private Cajero cajero;
	private String cedula;
	private String nombre;
	private String rol;
	private String RCaj;
	private String RJefe;
	private String correo;
	private String clave;
	private String Direccion;
	private List<Cajero> cajeros;
	private List<Cliente> clientes;
	private List<JefeCredito> creditos;
	Date myDate = new Date();

	@PostConstruct
	public void init() {
		credito = new JefeCredito();
		cliente = new Cliente();
		cajero = new Cajero();
		cajeros = new ArrayList<>();
		creditos = new ArrayList<>();

	}

    



	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


	public List<Cajero> getCajeros() {
		return cajeros;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public String getRCaj() {
		return RCaj;
	}

	public void setRCaj(String rCaj) {
		RCaj = rCaj;
	}

	public String getRJefe() {
		return RJefe;
	}

	public void setRJefe(String rJefe) {
		RJefe = rJefe;
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

	
	/**
	 * Este metodo  registra los usuarios con los respectivos roles
	 * ya sea Jede de Credito o Cajero, por la que solo el usuario administrador 
	 * tiene permiso de asignar el rol, si el admin no asinga el rol, este no podra ser 
	 * registrado 
	 * @param administrador objeto de tipo administrador
	 * @return
	 */
	public String registrarUsuario(Administrador administrador) {
		
		System.out.println("Rol: " + this.rol);
		ClienteON cOn = new ClienteON();
		
		if (this.rol.equals("Cajero")) {

			try {
				cajero.setCedula(this.getCedula());
				cajero.setNombre(this.getNombre());
				cajero.setTipo(this.getRol());
				cajero.setNombre(this.getNombre());
				cajero.setDireccion(this.getDireccion());
				cajero.setCorreo(this.getCorreo());
				cajero.setClave(adminON.generarContraseña());
				cajeros.add(cajero);
				administrador.setCajeros(cajeros);
				adminON.update(administrador);
				cOn.enviarCorreo(this.getCorreo(), "Bienvenido al Sistema", "Contraseña: "+ cajero.getClave());
				cajero = new Cajero();
				cajeros.clear();
				
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (this.rol.contentEquals("Jefe Credito")) {
			try {
				credito.setCedula(this.getCedula());
				credito.setNombre(this.getNombre());
				credito.setTipo(this.getRol());
				credito.setNombre(this.getNombre());
				credito.setDireccion(this.getDireccion());
				credito.setCorreo(this.getCorreo());
				credito.setClave(adminON.generarContraseña());
				creditos.add(credito);
				administrador.setCreditos(creditos);
				adminON.update(administrador);
				cOn.enviarCorreo(this.getCorreo(), "Bienvenido al Sistema", "Contraseña: "+ credito.getClave());
				credito = new JefeCredito();
				creditos.clear();
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No se ha seleccionado el tipo");
		}

		return "inicioAdmin?faces-redirect=true";
	}

}
