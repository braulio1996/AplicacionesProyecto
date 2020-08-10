package ec.edu.ups.Services;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.Modelo.Acceso;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;

import ec.edu.ups.Modelo.Transaccion;

import ec.edu.ups.Modelo.SolicitudCredito;

import ec.edu.ups.ON.AdministradorON;
import ec.edu.ups.ON.CajeroON;
import ec.edu.ups.ON.ClienteON;
import ec.edu.ups.ON.CreditoON;
import ec.edu.ups.ON.SolicitudON;

@Path("/users")
public class ClienteServiceRest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ClienteON con;
	@Inject
	private CajeroON caon;
	@Inject
	private CreditoON crediON;
	@Inject
	private AdministradorON adminON;
	
	@Inject
	private SolicitudON s;

	Date myDate = new Date();

	private LocalDate fechaDesde;
	private LocalDate fechaHasta;
	private String buscarTipo;
	private String estadoAcceso;

	@POST
	@Path("/transferencia")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Respuesta transferencia2(TransferenciaTemporal t) {
		Respuesta r = new Respuesta();
		try {
			r.setCodigo(1);
			r.setMensaje(con.transferencia(t));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			r.setCodigo(0);
			r.setMensaje(e.getMessage());
		}
		return r;

	}

	@POST
	@Path("/Retiro")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Respuesta retiro(@QueryParam("cajero") String cajero, @QueryParam("cliente") String cliente,
			@QueryParam("monto") Double monto) {
		Respuesta r = new Respuesta();
		try {
			r.setCodigo(1);
			r.setMensaje(caon.retiro(cajero, cliente, monto));
		} catch (Exception e) {
			r.setCodigo(0);
			r.setMensaje("Error " + e.getMessage());
			e.printStackTrace();
		}

		return r;
	}

	@POST
	@Path("/Deposito")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Respuesta deposito(@QueryParam("cajero") String cajero, @QueryParam("cliente") String cliente,
			@QueryParam("monto") Double monto, @QueryParam("depositante") String depositante) {
		Respuesta r = new Respuesta();
		try {
			r.setCodigo(1);
			r.setMensaje(caon.depositosC(cajero, cliente, monto, depositante));
		} catch (Exception e) {
			r.setCodigo(0);
			r.setMensaje("Error " + e.getMessage());
			e.printStackTrace();
		}
		return r;

	}

	@GET
	@Path("/Login")
	@Produces(MediaType.APPLICATION_JSON)
	public Respuesta login(@QueryParam("correo") String correo, @QueryParam("clave") String clave) throws Exception {
		System.out.println("Entro metodo service inicio");

		boolean client = false;
		Acceso acceso = new Acceso();
		List<Acceso> accesos = new ArrayList<Acceso>();
		Cliente cliente;
		String mensaje;
		Respuesta r = new Respuesta();
			if (con.buscarCorreo(correo) != null) {
				System.out.println("Entro metodo service");
				if (con.loginC(correo, clave) != null) {
					client = true;
					cliente = con.loginC(correo, clave);
					// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",
					// client);

					con.enviarCorreo(correo, "Acceso a la cuenta", "Acceso correcto a la cuenta");
					acceso.setClave(clave);
					acceso.setEstado("Correcto");
					acceso.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
					acceso.setHora(new SimpleDateFormat("HH:mm:ss").format(myDate));
					acceso.setCliente(cliente);
					accesos.add(acceso);
					cliente.setAccesos(accesos);
					con.editar(cliente);

					acceso = new Acceso();
					accesos.clear();

					this.fechaHasta = LocalDate.now();
					this.fechaDesde = con.restarFecha(this.fechaHasta);
					this.buscarTipo = "Todos";
					this.estadoAcceso = "Todos";
					this.buscarTipo = "Todos";
					mensaje = "Ingreso Exitoso";
					r.setCodigo(1);
					r.setMensaje(cliente.getCuenta().getNumero());
//					return r;
				} else {
					System.out.println("contraseña incorrecta");
					mensaje = "ERROR. Usuario Incorrecto";
					
					con.enviarCorreo(correo, "Acceso a la cuenta",
							"Su intento ha sido fallido, con contraseña: " + clave);
					cliente = con.loginC(correo, clave);
					acceso.setClave(clave);
					acceso.setEstado("Fallido");
					acceso.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
					acceso.setHora(new SimpleDateFormat("HH:mm:ss").format(myDate));
					acceso.setCliente(cliente);
					accesos.add(acceso);
					cliente.setAccesos(accesos);
					con.editar(cliente);
					acceso = new Acceso();
					accesos.clear();
					r.setCodigo(2);
					r.setMensaje("Error intento Fallido");
					return r;
				} // Fin if (con.loginC(this.correo, this.clave) != null)
			}else {
				System.out.println("correo incorrecto");
				mensaje = "ERROR. Usuario Incorrecto";
				r.setCodigo(99);
				r.setMensaje(mensaje);

				return r;
			} // Fin if (con.buscarCorreo(this.correo) != null)
		 // FIn try-catch

		return null;

	}// Fin metodo login

	@GET
	@Path("/BuscarCliente")
	@Produces(MediaType.APPLICATION_JSON)
	public ClienteTemporal buscarCliente(@QueryParam("cedula") String cedula) throws Exception {
		ClienteTemporal c = new ClienteTemporal();
		try {
			c.setCodigo(con.buscar(cedula).getCodigo());
			c.setCedula(con.buscar(cedula).getCedula());
			c.setNombre(con.buscar(cedula).getNombre());
			c.setCorreo(con.buscar(cedula).getCorreo());
			c.setCuenta(con.buscar(cedula).getCuenta().getNumero());

			return c;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//
//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Respuesta getModels(@PathParam("id") String id) {
//		Respuesta r = new Respuesta();
//		System.out.println("-----------------------------------------------------------");
//		r.setCodigo(1);
//		r.setMensaje(id);
//		return r;
//	}
//
//	@GET
//	@Path("listarCliente")
//	@Produces("application/json")
//	public List<Cliente> listar() throws Exception {
//	System.out.println("-----------------------------------------------------------");
//			return con.listar();
//		
//	}
	
	@GET
	@Path("/Password")
	@Produces(MediaType.APPLICATION_JSON)
	public Respuesta cambioPassword(@QueryParam("correo") String correo) throws Exception {
		return con.generarContraseña(correo);
		
	}
	
//	@GET
//	@Path("/ahorro")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Transaccion> transCliente(@QueryParam("cliente") int c, @QueryParam("desde") LocalDate fechaDesde, @QueryParam("hasta") LocalDate fechaHasta, @QueryParam("tipo") String tipo) throws Exception {
//		return con.transCli(c, fechaDesde, fechaHasta, tipo);
//	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Respuesta getModels123(@PathParam("id") String id) {
		Respuesta r = new Respuesta();
		System.out.println("-----------------------------------------------------------");
		r.setCodigo(1);
		r.setMensaje(id);
		return r;
	}
	@GET
	@Path("/BuscarCliente")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SolicitudCredito>solicitudes() throws Exception{
		return s.listarSoli();
		
	}
}