package ec.edu.ups.Vista;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.Modelo.Administrador;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Credito;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.ON.AdministradorON;
import ec.edu.ups.ON.CajeroON;
import ec.edu.ups.ON.ClienteON;
import ec.edu.ups.ON.CreditoON;


@ManagedBean(name = "login")
@SessionScoped
public class LoginController {
	private String nombreUsuario;
	private FacesMessages facesMsg;
	
	@Inject
	private AdministradorON adminON;

	@Inject
	private ClienteON clieOn;

	@Inject
	private CreditoON crediON;

	@Inject
	private CajeroON cajeON;
	
	private Administrador administrador;
	private Cliente cliente;
	private Credito credito;
	private Cajero cajero;
	private List<Cajero> cajeros;
	private List<Cliente> clientes;
	private List<Credito> creditos;
	private String correo;
	private String clave;
	private Cuenta cuenta;
	
	@PostConstruct
	public void init() {
		administrador = new Administrador();
		cliente = new Cliente();
		credito = new Credito();
		cajero = new Cajero();
		cuenta = new Cuenta();
		clientes = new ArrayList<>();
		cajeros = new ArrayList<>();
		creditos = new ArrayList<>();
		correo = "";
		clave = "";
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
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

	public List<Cajero> getCajeros() {
		return cajeros;
	}

	public void setCajeros(List<Cajero> cajeros) {
		this.cajeros = cajeros;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Credito> getCreditos() {
		return creditos;
	}

	public void setCreditos(List<Credito> creditos) {
		this.creditos = creditos;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public String getAdministradors() {
		return null;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public Cajero getCajero() {
		return cajero;
	}

	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String login() {
		System.out.println("Entro metodo Login");
		boolean client=false;
		boolean mail = false;
		
		try {
			mail =  clieOn.buscarCorreo(this.correo);
			if(mail == true) {
				System.out.println("Entro xq existe correo");
				if (clieOn.loginC(this.correo, this.clave) != null) {
					client = true;
					cliente = clieOn.loginC(getCorreo(), getClave());
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
					setNombreUsuario(cliente.getNombre());
					
					clieOn.enviarCorreo(this.correo, "Acceso a la cuenta", "Acceso correcto a la cuenta");
					return "inicioCliente";
				}else{
					System.out.println("Existe el correo pero no la clave");
					clieOn.enviarCorreo(this.correo, "Acceso a la cuenta", "Su intento ha sido fallido, con contraseña: "+this.clave);
				}
			}else {
				System.out.println("No existe la cuenta");
			}
			
			if (adminON.loginC(this.correo, this.clave) != null) {
				client = true;
				administrador = adminON.loginC(getCorreo(), getClave());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
				setNombreUsuario(administrador.getNombre());
				return "inicioAdmin";
			}
			if (crediON.loginC(this.correo, this.clave) != null) {
				client = true;
				credito = crediON.loginC(getCorreo(), getClave());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
				setNombreUsuario(credito.getNombre());
				return "inicioCredito";
			} 
			if (cajeON.loginC(this.correo, this.clave).getTipo().equalsIgnoreCase("cajero")) {
				client = true;
				cajero = cajeON.loginC(getCorreo(), getClave());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
				setNombreUsuario(cajero.getNombre());
				return "inicioCajero";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}