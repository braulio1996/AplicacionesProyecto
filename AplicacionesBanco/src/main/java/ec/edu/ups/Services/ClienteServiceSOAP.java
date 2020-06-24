package ec.edu.ups.Services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import ec.edu.ups.Modelo.Acceso;
import ec.edu.ups.Modelo.Administrador;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.JefeCredito;
import ec.edu.ups.Modelo.Persona;
import ec.edu.ups.ON.AdministradorON;
import ec.edu.ups.ON.CajeroON;
import ec.edu.ups.ON.ClienteON;
import ec.edu.ups.ON.CreditoON;


@WebService
public class ClienteServiceSOAP {
	
@Inject
private ClienteON con;
@Inject
private CajeroON caon;
@Inject
private CreditoON crediON;
@Inject
private AdministradorON adminON;

Date myDate = new Date();

private LocalDate fechaDesde;
private LocalDate fechaHasta;
private String buscarTipo;
private String estadoAcceso;

//@WebMethod
//public String saludo(String nombre) {
//	return "Hola "+nombre;
//}
//
//@WebMethod
//public List<Cliente> ListarCliente(){
//	List<Cliente> listado= new ArrayList<Cliente>();
//	try {
//		listado =con.listar();
//		
//	}catch (Exception e) {
//		// TODO: handle exception
//		e.printStackTrace();
//	}
//	return listado;
//}
@WebMethod
public Respuesta transferencia(String cliente, String cuentaDestino,Double monto) {
	Respuesta r = new Respuesta();
	try {
		r.setCodigo(1);
		r.setMensaje(con.trasferencia(cliente, cuentaDestino, monto));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		r.setCodigo(0);
		r.setMensaje(e.getMessage());
	}
	return r;
	
}
@WebMethod
public Respuesta retiro(String cajero, String cedula, Double monto) {
	Respuesta r = new Respuesta();
	try {
		r.setCodigo(1);
		r.setMensaje(caon.retiro(cajero, cedula,monto));
	}catch (Exception e) {
		r.setCodigo(0);
		r.setMensaje("Error "+e.getMessage());
		e.printStackTrace();
	}

return r;	
}

@WebMethod
public Respuesta deposito(String cajero, String cedula, Double monto,String depositante) {
	Respuesta r = new Respuesta();
	try {
		r.setCodigo(1);
		r.setMensaje(caon.depositosC(cajero, cedula,monto,depositante));
	}catch (Exception e) {
		r.setCodigo(0);
		r.setMensaje("Error "+e.getMessage());
		e.printStackTrace();
	}
	return r;

}
public Respuesta login(String correo,String clave) throws Exception {
	
	boolean client = false;
	Acceso acceso = new Acceso();
	List<Acceso>accesos=new ArrayList<Acceso>();
	Cliente cliente;
	String mensaje="";
	Respuesta r=new  Respuesta();
	try {
		
		if (caon.loginC(correo,clave).getTipo().equalsIgnoreCase("cajero")) {
			client = true;
			Cajero cajero = caon.loginC(correo,clave);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
			mensaje="Ingreso Exitoso";
            r.setCodigo(Integer.parseInt(cajero.getCedula()));
            r.setMensaje(mensaje);
			return r;
		} // Fin if (caon.loginC(this.correo,
			// this.clave).getTipo().equalsIgnoreCase("cajero"))

	} catch (Exception e) {
		e.printStackTrace();

		if (con.buscarCorreo(correo) != null) {
			if (con.loginC(correo,clave) != null) {
				client = true;
				cliente = con.loginC(correo,clave);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
				

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
				mensaje="Ingreso Exitoso";
	            r.setCodigo(Integer.parseInt(cliente.getCuenta().getNumero()));
	            r.setMensaje(mensaje);
				return r;
			} else {
				mensaje= "ERROR. Usuario Incorrecto";
	            r.setCodigo(1);
	            r.setMensaje(mensaje);
				con.enviarCorreo(correo, "Acceso a la cuenta",
						"Su intento ha sido fallido, con contraseña: " + clave);
				cliente = con.loginC(correo,clave);
				acceso.setClave(clave);
				acceso.setEstado("Fallido");
				acceso.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
				acceso.setHora(new SimpleDateFormat("HH:mm:ss").format(myDate));
				acceso.setCliente(cliente);
				accesos.add(acceso);
				cliente.setAccesos(accesos);
				con.editar(cliente);


			} // Fin if (con.loginC(this.correo, this.clave) != null)
		} else {
			mensaje="Error";
            r.setCodigo(1);
            r.setMensaje(mensaje);
		} // Fin if (con.buscarCorreo(this.correo) != null)
	} // FIn try-catch

	return null;
}// Fin metodo login
}
