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
import ec.edu.ups.Modelo.Credito;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.ON.AdministradorON;
import ec.edu.ups.ON.CajeroON;
import ec.edu.ups.ON.ClienteON;
import ec.edu.ups.ON.CreditoON;

@ManagedBean
@ApplicationScoped
public class AdministradorMB {
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
	Date myDate = new Date();

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

	public String registrar() {
		System.out.println("Entro a este metodo");
		return "login-2?faces-redirect=true";
	}

	public String guardarCliente() throws Exception {
		System.out.println(cliente.toString());
		System.out.println(administrador.getNombre());
		cliente.setTipo("Cliente");
		cuenta.setSaldo(0.0);
		cuenta.setCliente(cliente);
		clientes.add(cliente);
		cliente.setCuenta(cuenta);
		administrador.setClientes(clientes);
		adminON.update(administrador);
		String mensaje="Bienvenid@ a la Cooperativa Kawill "+cliente.getNombre()+" \n"+"Su correo es = "+cliente.getCorreo()+" \n"+"Su clave es = "+cliente.getClave()+" \n"+"Su numero de cuenta es = "+cuenta.getNumero()+"\n ";
		clieOn.enviarCorreo(cliente.getCorreo(),"Cuenta Cliente Creada", mensaje+" Creado el "+new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(myDate));
		cliente = new Cliente();
		clientes.clear();
		return "listaClientes";

	}

	public String guardarCajero() throws Exception {
		cajero.setTipo("Cajero");
		cajeros.add(cajero);
		administrador.setCajeros(cajeros);
		adminON.update(administrador);
		cajero = new Cajero();
		cajeros.clear();

		return "listaCajeros";

	}

	public String guardarCredito() throws Exception {
		credito.setTipo("Credito");
		creditos.add(credito);
		administrador.setCreditos(creditos);
		adminON.update(administrador);
		credito = new Credito();
		creditos.clear();

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
			cliente = clieOn.buscar(cedula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		System.out.println("=======================  " + cedula);
		clieOn.eliminar(cedula);
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
