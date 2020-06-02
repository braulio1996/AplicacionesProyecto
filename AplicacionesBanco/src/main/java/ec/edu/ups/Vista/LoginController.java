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

	public String login() throws Exception {
		boolean client = false;

		try {

			if (adminON.loginC(this.correo, this.clave) != null) {
				client = true;
				administrador = adminON.loginC(getCorreo(), getClave());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
				setAdministrador(administrador);

				return "inicioAdmin?faces-redirect=true";
			}

			if (crediON.loginC(this.correo, this.clave) != null) {
				client = true;
				credito = crediON.loginC(getCorreo(), getClave());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
				setCredito(credito);
				return "inicioCredito?faces-redirect=true";
			}
			if (cajeON.loginC(this.correo, this.clave).getTipo().equalsIgnoreCase("cajero")) {
				client = true;
				cajero = cajeON.loginC(getCorreo(), getClave());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
				setCajero(cajero);
				return "inicioCajero?faces-redirect=true";
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR. Usuario Incorrecto 1");
			
			if (clieOn.buscarCorreo(this.correo) != null) {
				if (clieOn.loginC(this.correo, this.clave) != null) {
					client = true;
					cliente = clieOn.loginC(getCorreo(), getClave());
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
					setCliente(cliente);

					clieOn.enviarCorreo(this.correo, "Acceso a la cuenta", "Acceso correcto a la cuenta");
					return "inicioCliente?faces-redirect=true";
				} else {
					System.out.println("ERROR. Usuario Incorrecto");
					clieOn.enviarCorreo(this.correo, "Acceso a la cuenta",
							"Su intento ha sido fallido, con contraseña: " + this.clave);
				}
			}
			
		}

		return null;
	}
	
	public String updCliente() {
		try {
			clieOn.editar(this.cliente);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se pudo modificar");
			return null;
			
		}
	}
	
}
