package ec.edu.ups.Vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.Modelo.Administrador;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Credito;
import ec.edu.ups.ON.AdministradorON;
import ec.edu.ups.ON.CajeroON;
import ec.edu.ups.ON.ClienteON;
import ec.edu.ups.ON.CreditoON;

@ManagedBean
@ViewScoped
public class AdministradorMB {
	@Inject
	private AdministradorON adminON;
	
	@Inject
	private ClienteON clieOn;
	
	@Inject
	private CreditoON crediON;
	
	@Inject
	private CajeroON cajeON;

	private Administrador administrador = new Administrador();
	private Cliente cliente = new Cliente();
	private Credito credito = new Credito();
	private Cajero cajero = new Cajero();


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

	public String registrar() {
		try {
			adminON.registrar(administrador);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}

	public String login() {
		try {
			if(administrador.getTipo().equalsIgnoreCase("admin")) {
				adminON.loginC(administrador.getCorreo(), administrador.getClave());
				return "inicioAdmin";	
			}if(administrador.getTipo().equalsIgnoreCase("cliente")) {
				clieOn.loginC(administrador.getCorreo(), administrador.getClave());
				return "inicioCliente";
			}if(administrador.getTipo().equalsIgnoreCase("credito")) {
				crediON.loginC(administrador.getCorreo(), administrador.getClave());
				return "inicioCredito";
			}if(administrador.getTipo().equalsIgnoreCase("cajero")){
				cajeON.loginC(administrador.getCorreo(), administrador.getClave());
				return "inicioCajero";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String guardarCliente() throws Exception {
		clieOn.guardar(cliente);
		return "listaClientes";
		
	}

	public String guardarCajero() throws Exception {
		cajeON.guardar(cajero);
		
		return "listaCajeros";
		
	}

	public String guardarCredito() throws Exception {
		
		crediON.guardar(credito);
		
		return "listaCreditos";
	}

	public List<Cliente> listarCliente() throws Exception {
		return clieOn.listar();
	}

	public List<Credito> listarCredito() throws Exception {
		return crediON.listar();
	}

	public List<Cajero> listarCajero() throws Exception {
		return cajeON.listar();
	}
	public String editarAjaxCliente(String cedula) {
		System.out.println(cedula);
		try {
			cliente=clieOn.buscar(cedula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String editarAjaxCajero(String cedula) {
		System.out.println(cedula);
		try {
			cajero=cajeON.buscar(cedula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String editarAjaxCredito(String cedula) {
		System.out.println(cedula);
		try {
			credito=crediON.buscar(cedula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String ediatrCliente() throws Exception {
		clieOn.editar(cliente);
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
	public String eliminarCliente(String cedula) throws Exception {
		System.out.println("=======================  "+cedula);
		clieOn.eliminar(cedula);
		return null;
	}
	public String eliminarCredito(String cedula) throws Exception {
		System.out.println("=======================  "+cedula);
		crediON.eliminar(cedula);
		return null;
	}
	public String eliminarCajero(String cedula) throws Exception {
		System.out.println("=======================  "+cedula);
		cajeON.eliminar(cedula);
		return null;
	}
}
