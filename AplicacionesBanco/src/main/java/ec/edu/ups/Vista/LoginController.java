package ec.edu.ups.Vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.Modelo.Acceso;
import ec.edu.ups.Modelo.Administrador;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.JefeCredito;
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
	private Acceso acceso;

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
	private JefeCredito credito;
	private Cajero cajero;
	private List<Cajero> cajeros;
	private List<Cliente> clientes;
	private List<JefeCredito> creditos;
	private String correo;
	private String clave;
	private Cuenta cuenta;
	Date myDate = new Date();
	private List<Acceso>accesos;

	@PostConstruct
	public void init() {
		acceso= new Acceso();
		administrador = new Administrador();
		cliente = new Cliente();
		credito = new JefeCredito();
		cajero = new Cajero();
		cuenta = new Cuenta();
		clientes = new ArrayList<>();
		cajeros = new ArrayList<>();
		creditos = new ArrayList<>();
		accesos = new ArrayList<>();
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
	}//Fin metodo getCajeros

	public void setCajeros(List<Cajero> cajeros) {
		this.cajeros = cajeros;
	}//Fin metodo setCajeros

	public List<Cliente> getClientes() {
		return clientes;
	}//Fin metodo getClientes

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}//Fin metodo setClientes

	public List<JefeCredito> getCreditos() {
		return creditos;
	}//Fin metodo getCreditos

	public void setCreditos(List<JefeCredito> creditos) {
		this.creditos = creditos;
	}//Fin metodo setCreditos

	public Administrador getAdministrador() {
		return administrador;
	}//FIn metodo getAdministrador

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}//Fin metodo setAdministrador

	public String getAdministradors() {
		return null;
	}//Fin metodo getAdministradors

	public Cliente getCliente() {
		return cliente;
	}//Fin metodo getCliente

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}//FIn metodo setCliente

	public JefeCredito getCredito() {
		return credito;
	}//Fin metodo getCredito

	public void setCredito(JefeCredito credito) {
		this.credito = credito;
	}//Fin metodo setCredito

	public Cajero getCajero() {
		return cajero;
	}//Fin metodo getCajero

	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}//Fin metodo setCajero

	public String getNombreUsuario() {
		return nombreUsuario;
	}//Fin metodo getNombreUsuario

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}//Fin metodo setnombreusuario

	public String login() throws Exception {
		System.out.println("Entro al metodo");
		boolean client = false;
		
		try {
			if (adminON.loginC(this.correo, this.clave)!=null) {
				client = true;
				administrador = adminON.loginC(getCorreo(), getClave());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
				setAdministrador(administrador);

				return "inicioAdmin?faces-redirect=true";
			}
			
			if (crediON.loginC(this.correo, this.clave)!=null) {
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
			}//Fin if (cajeON.loginC(this.correo, this.clave).getTipo().equalsIgnoreCase("cajero"))

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
					acceso.setClave(clave);
					acceso.setEstado("Correcto");
					acceso.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
					acceso.setHora(new SimpleDateFormat("HH:mm:ss").format(myDate));
					acceso.setCliente(cliente);
					accesos.add(acceso);
					cliente.setAccesos(accesos);
					clieOn.editar(cliente);
					acceso = new Acceso();
					accesos.clear();
					return "inicioCliente?faces-redirect=true";
				} else {
					System.out.println("ERROR. Usuario Incorrecto");
					clieOn.enviarCorreo(this.correo, "Acceso a la cuenta",
							"Su intento ha sido fallido, con contraseña: " + this.clave);
			
					acceso.setClave(clave);
					acceso.setEstado("Fallido");
					acceso.setFecha(new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(myDate));
					acceso.setHora(new SimpleDateFormat("HH:mm:ss").format(myDate));
					acceso.setCliente(cliente);
					accesos.add(acceso);
					cliente.setAccesos(accesos);
					clieOn.editar(cliente);
				}//Fin if (clieOn.loginC(this.correo, this.clave) != null)
			}else {
				System.out.println("Error");
			}//Fin if (clieOn.buscarCorreo(this.correo) != null)
		}//FIn try-catch

		return null;
	}//Fin metodo login
	
	public String updCliente() {
		try {
			clieOn.editar(this.cliente);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se pudo modificar");
			return null;
		}//Fin try-catch
	}//Fin metodo updCliente
}//Fin ControladorLogin
