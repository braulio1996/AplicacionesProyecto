package ec.edu.ups.ON;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import ec.edu.ups.DAO.CajeroDAO;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.Transaccion;
import ec.edu.ups.Modelo.Transferencia;

/**
 * @author Marcela
 *
 */
@Stateless
public class CajeroON {

	@Inject
	private CajeroDAO pdao;
	@Inject
	private ClienteON clienteON;
	@Inject
	private CuentaON cON;
	private String mensaje;
	private Transaccion t;
	LocalDate myDate = LocalDate.now();
	

	/**
	 * Guarda el objeto de tipo cajero
	 * @param cajero  objeto cajero que se va guardar
	 * @return cajero  si este existiera
	 * @throws Exception control de Excepciones
	 */
	public boolean guardar(Cajero cajero) throws Exception {
		return pdao.guardar(cajero);

	}
	
	/**
	 * Autentificacion de Cajero mediante las credenciales corro y clave
	 * @param correo identificador del cajero 
	 * @param clave identificadro de clave de cajero
	 * @return correo renorna los valores si estas existen
	 * @return clave renorna los valores si estas existen
	 * @throws Exception control de Excepciones
	 */
	public Cajero loginC(String correo, String clave) throws Exception {

		return pdao.login(correo, clave);
	}

	/**Lista los cajeros
	 * @return lista los datos del objeto Cajero
	 * @throws Exception control de Excepciones
	 */
	public List<Cajero> listar() throws Exception {
		return pdao.listar();

	}


	/**
	 * Modificar el objeto de tipo Cajero
	 * @param cajero el objeto que se va actualizar
	 * @throws Exception  control de Excepciones
	 */
	public void editar(Cajero cajero) throws Exception {
		pdao.editar(cajero);
	}

	/**
	 * Busca los usuarios mediante la cedula de identificacion
	 * @param cedula identificador unico del objeto de tipo Persona
	 * @return cedula retorna el valor si esta existe
	 * @throws Exception control de Excepciones
	 */
	public Cajero buscar(String cedula) throws Exception {
		return pdao.buscar(cedula);
	}

	/**
	 * Permite leer los datos al buscar al usuario mendiante la cedula
	 * @param cedula identificador del objeto Cajero de Tipo Persona
	 * @return cedula retorna el valor si esta exite 
	 */
	public Cajero read(String cedula) {
		return pdao.read(cedula);
	}

	/**
	 * Elimina al usuario mediante la cedula 
	 * @param cedula identificador que elimina el objeto Cajero
	 * @throws Exception control de Excepciones
	 */
	public void eliminar(String cedula) throws Exception {
		pdao.eliminar(cedula);

	}
	
	/**
	 * @return 
	 */
	public long contar() {
		return pdao.contar();
	}
	public void retiro(String cajeroID, String cedula, Double monto) {
		try {
			List<Transaccion>transacciones = new ArrayList<Transaccion>();
			Cajero cajero=pdao.buscar(cajeroID);
			Cliente cliente=clienteON.buscar(cedula);
			Double saldo = cliente.getCuenta().getSaldo();
			
			if(saldo < monto) {
				this.mensaje = "ERROR. El monto es mayor al saldo ("+saldo+")";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR", this.mensaje ); 
				System.out.println(this.mensaje);
				//return null;
			}else {
				Double total = saldo - monto;
				Cuenta cuenta= cON.buscarCuenta(cliente.getCuenta().getNumero());
				cuenta.setSaldo(total);
				cliente.setCuenta(cuenta);
				t.setTipo("Retiro");
				t.setDepositante("NaN");
				t.setCliente(cliente);
				t.setCajero(cajero);
				t.setFecha(myDate);
				t.setMonto(monto);
				transacciones.add(t);
				cliente.setTransacciones(transacciones);
				cajero.setTransacciones(transacciones);
				clienteON.editar(cliente);
				//cjON.editar(cajero);
//				t=new Transaccion();
//				cuenta=new Cuenta();
//				cajero =new Cajero();
//				cliente=new Cliente();
//				
//				cedula="";
//				transacciones.clear();
				
				this.mensaje = "Retiro realizado correctamente";
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto", this.mensaje );
				
				System.out.println(this.mensaje);
				//return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			this.mensaje = e.getMessage();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR", this.mensaje ); 
			System.out.println(this.mensaje);
			//return null;
		}

	}
	public void depositosC(String cajeroID, String cedula, Double monto) {
	
		try {
			List<Transaccion>transacciones = new ArrayList<>();
			Cajero cajero=pdao.buscar(cajeroID);
			Cliente cliente=clienteON.buscar(cedula);
			Double saldo = cliente.getCuenta().getSaldo();
			double total = saldo + monto;
			Cuenta cuenta= cON.buscarCuenta(cliente.getCuenta().getNumero());
			cuenta.setSaldo(total);
			cliente.setCuenta(cuenta);
			t.setSaldoCuenta(total);
			t.setTipo("Deposito");
			t.setCliente(cliente);
			t.setCajero(cajero);
			t.setFecha(myDate);
			t.setMonto(monto);
			t.setDepositante(t.getDepositante());
			transacciones.add(t);
			
			cliente.setTransacciones(transacciones);
			cajero.setTransacciones(transacciones);
			clienteON.editar(cliente);
//			t=new Transaccion();
//			cuenta=new Cuenta();
//			cajero =new Cajero();
//			cliente=new Cliente();
//			cedula="";
//			transacciones.clear();
			System.out.println("-------------------------------  "+transacciones);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//return null;
	}

}
