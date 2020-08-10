package ec.edu.ups.Vista;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import javax.inject.Inject;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ec.edu.ups.Modelo.Administrador;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.CreditoAprobado;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.JefeCredito;
import ec.edu.ups.Modelo.SolicitudCredito;
import ec.edu.ups.ON.AdministradorON;
import ec.edu.ups.ON.CajeroON;
import ec.edu.ups.ON.ClienteON;
import ec.edu.ups.ON.CreditoON;
import ec.edu.ups.ON.SolicitudON;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

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
	@Inject
	private SolicitudON sON;

	private JefeCredito credito;
	private Cliente cliente;
	private Cajero cajero;
	private SolicitudCredito solicitud;
	private CreditoAprobado cap;
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
	private List<SolicitudCredito> solicitudes;
	private StreamedContent cedulaFrontal;
	private StreamedContent cedulaPosterior;
	private StreamedContent fotoPlanilla;
	private StreamedContent fotoRol;
	
	Date myDate = new Date();
	private String txtBuscar;
	
	private Byte[] cedulaF;
	private Byte[] cedulaT;
	private Byte[] planilla;
	private Byte[] rolPago;
	
	
	@PostConstruct
	public void init() {
		credito = new JefeCredito();
		cliente = new Cliente();
		cajero = new Cajero();
		cajeros = new ArrayList<>();
		creditos = new ArrayList<>();
		txtBuscar = "Todos";
		solicitudes = new ArrayList<>();
		solicitud= new SolicitudCredito();
		cap = new CreditoAprobado();

	}

	public CreditoAprobado getCap() {
		return cap;
	}

	public void setCap(CreditoAprobado cap) {
		this.cap = cap;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	
	public StreamedContent getCedulaFrontal() {
		return cedulaFrontal;
	}

	public void setCedulaFrontal(StreamedContent cedulaFrontal) {
		this.cedulaFrontal = cedulaFrontal;
	}

	public StreamedContent getCedulaPosterior() {
		return cedulaPosterior;
	}

	public void setCedulaPosterior(StreamedContent cedulaPosterior) {
		this.cedulaPosterior = cedulaPosterior;
	}

	public StreamedContent getFotoPlanilla() {
		return fotoPlanilla;
	}

	public void setFotoPlanilla(StreamedContent fotoPlanilla) {
		this.fotoPlanilla = fotoPlanilla;
	}

	public StreamedContent getFotoRol() {
		return fotoRol;
	}

	public void setFotoRol(StreamedContent fotoRol) {
		this.fotoRol = fotoRol;
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
	
	public CreditoAprobado buscarCA(int cliente, int codigo) throws Exception {
		return clieOn.buscarCA(cliente, codigo);
	}
	
	public List<SolicitudCredito> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<SolicitudCredito> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public String getTxtBuscar() {
		return txtBuscar;
	}

	public void setTxtBuscar(String txtBuscar) {
		this.txtBuscar = txtBuscar;
	}

	public SolicitudCredito getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudCredito solicitud) {
		this.solicitud = solicitud;
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

	public Byte[] getCedulaF() {
		return cedulaF;
	}

	public void setCedulaF(Byte[] cedulaF) {
		this.cedulaF = cedulaF;
	}

	public Byte[] getCedulaT() {
		return cedulaT;
	}

	public void setCedulaT(Byte[] cedulaT) {
		this.cedulaT = cedulaT;
	}

	public Byte[] getPlanilla() {
		return planilla;
	}

	public void setPlanilla(Byte[] planilla) {
		this.planilla = planilla;
	}

	public Byte[] getRolPago() {
		return rolPago;
	}

	public void setRolPago(Byte[] rolPago) {
		this.rolPago = rolPago;
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


	//crear un boton en la lista de Solicitudescreditos en el Objeto jefe de credito 
	//paso de parametros una Solicitud y un Credito Aprobado==llenar datos 
	public String aprobarCredito(Cliente c) throws Exception {
		cap.setNumero(sON.generarNum());
		cap.setTipo(solicitud.getTipo());
		cap.setFecha(new Date());
		cap.setMonto(solicitud.getMonto());
		//cap.setCliente(solicitud.getCliente());
		
		sON.aprobarSolicitud(c,solicitud, cap);
		
		return "inicioCredito?faces-redirect=true";
	}
	
	public String negarSoli() throws Exception {
		sON.negarSoli(solicitud);
		
		return "inicioCredito?faces-redirect=true";
	}
	
	public String verDetalle(int codigo) {
		this.solicitud = sON.verDetalle(codigo);
		//cliente = solicitud.getCliente();
		
		
		return "detalleSolicitud?faces-redirect=true";
	}
	
	
	public List<SolicitudCredito> solPendientes(){
		try {
			return sON.solPendientes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<SolicitudCredito> solRespuesta(){
		try {
			return sON.solRespuesta();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<SolicitudCredito> buscarSol(){
		try {
			return sON.buscarSol(txtBuscar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public SolicitudCredito buscarSolJC(int codigo) {
		System.out.println("Codigo: " + codigo);
		return sON.buscarSolJC(codigo);
	}
}
