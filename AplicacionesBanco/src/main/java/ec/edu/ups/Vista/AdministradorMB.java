package ec.edu.ups.Vista;
/**
 * Esta Clase define los ManagedBean
 * @version: 01/05/2020
 * @author Braulio Castro
 *
 */
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
import ec.edu.ups.Modelo.JefeCredito;
import ec.edu.ups.Modelo.Transaccion;
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
	private JefeCredito credito;
	private Cajero cajero;
	private List<Cajero> cajeros;
	private List<JefeCredito> creditos;
	private List<Cliente> clientes;
	private String correo;
	private String clave;
	private Cuenta cuenta;
	Date myDate = new Date();

	@PostConstruct
	public void init() {
		administrador = new Administrador();
		cliente = new Cliente();
		credito = new JefeCredito();
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

	public List<JefeCredito> getCreditos() {
		return creditos;
	}

	public void setCreditos(List<JefeCredito> creditos) {
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

	/**
	 * Este metodo 	Guarda los clientes en la cual una vez creada la cuenta 
	 * enviara un mensaje al correo con la que se registro con una clave de acceso
	 * @param administrador
	 * @return
	 * @throws Exception
	 */
	
	public String guardarCliente(Administrador administrador) throws Exception {
		
		
		System.out.println(cliente.toString());
		System.out.println(administrador.getNombre());
		cliente.setTipo("Cliente");
		cuenta.setNumero(adminON.generarCuenta());
		cuenta.setSaldo(0.0);
		cuenta.setCliente(cliente);
		clientes.add(cliente);
		cliente.setCuenta(cuenta);
		cliente.setClave(adminON.generarContraseña());
		administrador.setClientes(clientes);
		adminON.update(administrador);
		String mensaje="Bienvenid@ a nuestro sistema "+cliente.getNombre()+" \n"+"Su correo es = "+cliente.getCorreo()+" \n"+"Su clave es = "+cliente.getClave()+" \n"+"Su numero de cuenta es = "+cuenta.getNumero()+"\n ";
		clieOn.enviarCorreo(cliente.getCorreo(),"Cuenta Cliente Creada", mensaje+" Creado el "+new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(myDate));
		cliente = new Cliente();
		clientes.clear();
		return "inicioAdmin?faces-redirect=true";

	}

	/**
	 * Este metodo guarda usuarios que sean de tipo Cajero 
	 * que sera solo autorizado por el administrador para poder 
	 * dar los roles respectivos, asi mismo se podra modificar por el 
	 * adminitrador
	 * @return listaCajeros
	 * @throws Exception
	 */
	public String guardarCajero() throws Exception {
		cajero.setTipo("Cajero");
		cajeros.add(cajero);
		administrador.setCajeros(cajeros);
		adminON.update(administrador);
		cajero = new Cajero();
		cajeros.clear();

		return "listaCajeros";

	}

	/**
	 * Este metodo guarda los creditos  aprobados por el cajero 
	 * @return listaCreditos muestra lista de creditos existentes
	 * @throws Exception
	 */
	public String guardarCredito() throws Exception {
		credito.setTipo("Credito");
		creditos.add(credito);
		administrador.setCreditos(creditos);
		adminON.update(administrador);
		credito = new JefeCredito();
		creditos.clear();

		return "listaCreditos";
	}

	
	public List<Transaccion> listarTransaccion(String cuenta){
		return null;
	}
	
	public List<Cliente> listarCliente() throws Exception {
		return clieOn.listar();
	}
	
	public List<JefeCredito> listarCredito() throws Exception {
		return crediON.listar();
	}

	public List<Cajero> listarCajero() throws Exception {
		return cajeON.listar();
	}
	
	/**Editar   cliente mediante la cedula 
	 * @param cedula
	 * @return
	 */
	public String editarAjaxCliente(String cedula) {
		System.out.println(cedula);
		try {
			cliente = clieOn.buscar(cedula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**Edita los datos del cajero mediante la cedula 
	 * @param cedula
	 * @return
	 */
	public String editarAjaxCajero(String cedula) {
		System.out.println(cedula);
		try {
			cajero = cajeON.buscar(cedula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
	/**Edita las cuenta de creditos del usuario, en la cual se busca mediante el identificativo de la
	 * cedula 
	 * @param cedula id  del usuario para editar el credito
	 * @return
	 */
	public String editarAjaxCredito(String cedula) {
		System.out.println(cedula);
		try {
			credito = crediON.buscar(cedula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**Metodo editar cliente 
	 * @return
	 * @throws Exception
	 */
	public String ediatrCliente() throws Exception {
		clieOn.editar(cliente);
		return null;
	}

	/*
	 * Metodo  editaar cajero
	 * @return
	 * @throws Exception
	 */
	
	public String ediatrCajero() throws Exception {
		cajeON.editar(cajero);
		return null;
	}

	/**
	 * Editar credito  
	 * @return
	 * @throws Exception
	 */
	public String ediatrCredito() throws Exception {
		crediON.editar(credito);
		return null;
	}

	/**
	 * @param cedula
	 * @return
	 * @throws Exception
	 */
	public String eliminarCliente(String cedula) throws Exception {
		System.out.println("=======================  " + cedula);
		clieOn.eliminar(cedula);
		return null;
	}


	/**
	 * Metodo Eliminar credito  mediante a cedula del usaurio 
	 * @param cedula
	 * @return
	 * @throws Exception
	 */
	public String eliminarCredito(String cedula) throws Exception {
		System.out.println("=======================  " + cedula);
		crediON.eliminar(cedula);
		return null;
	}

	/**
	 * este metodo eliminar  cajero  son pruebas para verifcar su funcionamiento sin interfaz web
	 * @return
	 * @throws Exception
	 */
	public String eliminarCajero(String cedula) throws Exception {
		System.out.println("=======================  " + cedula);
		cajeON.eliminar(cedula);
		return null;
	}
	
	public long contarUser() {
		long c = cajeON.contar() + crediON.contar();
		
		return c;
	}
	
	public long contarClient() {
		long c = clieOn.contar();
		
		return c;
	}
}
