package ec.edu.ups.Services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.ON.CajeroON;
import ec.edu.ups.ON.ClienteON;


@WebService
public class ClienteServiceSOAP {
	
@Inject
private ClienteON con;
@Inject
private CajeroON caon;

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
public void transferencia(String cliente, String cuentaDestino,Double monto) {
	try {
		con.trasferencia(cliente, cuentaDestino, monto);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
@WebMethod
public void retiro(String cajeroID, String cedula, Double monto) {
caon.retiro(cajeroID, cedula,monto);	
}

@WebMethod
public void deposito(String cajeroID, String cedula, Double monto) {
caon.depositosC(cajeroID, cedula,monto);
}
}
