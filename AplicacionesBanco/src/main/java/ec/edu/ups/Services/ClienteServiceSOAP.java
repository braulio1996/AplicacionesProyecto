package ec.edu.ups.Services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import ec.edu.ups.Modelo.Acceso;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Cuenta;
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

@WebMethod
public Respuesta transferencia2(String cuentaOrigen, String cuentaDestino,Double monto, String nombre,String correo) {
	Respuesta r = new Respuesta();
	try {
		r.setCodigo(1);
		r.setMensaje(con.transferencia(cuentaOrigen, cuentaDestino, monto,nombre,correo));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		r.setCodigo(0);
		r.setMensaje(e.getMessage());
	}
	return r;
	
}
@WebMethod
public Respuesta retiro(String cajero, Cuenta cuenta, Double monto) {
	Respuesta r = new Respuesta();
	try {
		r.setCodigo(1);
		r.setMensaje(caon.retiro(cajero, cuenta,monto));
	}catch (Exception e) {
		r.setCodigo(0);
		r.setMensaje("Error "+e.getMessage());
		e.printStackTrace();
	}

return r;	
}

@WebMethod
public Respuesta deposito(String cajero, Cuenta cuenta, Double monto,String depositante) {
	Respuesta r = new Respuesta();
	try {
		r.setCodigo(1);
		r.setMensaje(caon.depositosC(cajero, cuenta,monto,depositante));
	}catch (Exception e) {
		r.setCodigo(0);
		r.setMensaje("Error "+e.getMessage());
		e.printStackTrace();
	}
	return r;

}


@WebMethod


public Respuesta login(String correo,String clave) throws Exception {

	
	boolean client = false;
	Acceso acceso = new Acceso();
	List<Acceso>accesos=new ArrayList<Acceso>();
	Cliente cliente= new Cliente();
	String mensaje;
	Respuesta r=new  Respuesta();
	try {
		//if(caon.loginC(correo,clave)!=null) {
			if (caon.loginC(correo,clave).getTipo().equalsIgnoreCase("cajero")) {
				client = true;
				Cajero cajero = caon.loginC(correo,clave);
				//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
				mensaje="cajero";
	            r.setCodigo(0);
	            r.setMensaje(cajero.getCedula());
				return r;
			} // Fin if (caon.loginC(this.correo,
				// this.clave).getTipo().equalsIgnoreCase("cajero"))
		//}
		

	} catch (Exception e) {
		if (con.buscarCorreo(correo) != null) {
			if (con.loginC(correo,clave) != null) {
				client = true;
				cliente = con.loginC(correo,clave);
				//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", client);
				con.enviarCorreo(correo, "Acceso a la cuenta", "Acceso correcto a la cuenta");
				mensaje="cliente";
				//int c=Integer.parseInt();
	            r.setCodigo(1);
	            r.setMensaje(cliente.getCuenta().getNumero());
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
				
				return r;
			} else {
				mensaje= "ERROR. Usuario Incorrecto";
	            r.setCodigo(1);
	            r.setMensaje(mensaje);
				con.enviarCorreo(correo, "Acceso a la cuenta",
						"Su intento ha sido fallido, con contraseña: " + clave);
				//cliente = con.loginC(correo,clave);
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
@WebMethod
public Cuenta buscarCuenta(String numero) {
	
	try {
		return con.buscarCuenta(numero);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
		
}	
}
