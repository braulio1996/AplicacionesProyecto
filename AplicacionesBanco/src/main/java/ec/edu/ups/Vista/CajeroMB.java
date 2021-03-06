package ec.edu.ups.Vista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.SolicitudCredito;
import ec.edu.ups.Modelo.Transaccion;
import ec.edu.ups.ON.CajeroON;
import ec.edu.ups.ON.ClienteON;
import ec.edu.ups.ON.CuentaON;

/**
 * Esta Clase define los ManagedBean
 * 
 * @version: 01/05/2020
 * @author Christian
 *
 */
@ManagedBean
@ApplicationScoped
public class CajeroMB {
	@Inject
	private CuentaON cON;

	@Inject
	private ClienteON clienteON;

	@Inject
	private CajeroON cjON;

	LocalDate myDate = LocalDate.now();

	private Cliente cliente;
	private Cuenta cuenta;
	private Cajero cajero;
	private Transaccion t;
	private List<Transaccion> transacciones;
	private String cedula;
	private String mensaje;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		cajero = new Cajero();
		t = new Transaccion();
		transacciones = new ArrayList<>();
		cedula = "";
		cuenta = new Cuenta();
		mensaje = "";
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cajero getCajero() {
		return cajero;
	}

	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}

	public Transaccion getT() {
		return t;
	}

	public void setT(Transaccion t) {
		this.t = t;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

	/**
	 * El metodo retiro es obtener el numero de cuenta, me develve el saldo menos la
	 * cantidad a retirar, el tipo de retiro que se realiza y la fecha en la que se
	 * realiza esa transaccion editar: Permite modificar los datos en el caso de
	 * estar erroneo
	 * 
	 * @return
	 */
	public String retiro(Cajero cajero) {
		try {
			cliente = clienteON.buscar(cedula);
			Double saldo = cliente.getCuenta().getSaldo();

			if (saldo < t.getMonto()) {
				this.mensaje = "ERROR. El monto es mayor al saldo (" + saldo + ")";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", this.mensaje);
				System.out.println(this.mensaje);
				return null;
			} else {
				Double total = saldo - t.getMonto();
				Cuenta cuenta = cON.buscarCuenta(cliente.getCuenta().getNumero());
				cuenta.setSaldo(total);
				cliente.setCuenta(cuenta);
				t.setTipo("Retiro");
				t.setDepositante("NaN");
				t.setCliente(cliente);
				t.setCajero(cajero);
				t.setFecha(myDate);
				transacciones.add(t);
				cliente.setTransacciones(transacciones);
				cajero.setTransacciones(transacciones);
				clienteON.editar(cliente);
				// cjON.editar(cajero);
				t = new Transaccion();
				cuenta = new Cuenta();
				cajero = new Cajero();
				cliente = new Cliente();

				cedula = "";
				transacciones.clear();

				this.mensaje = "Retiro realizado correctamente";

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", this.mensaje);

				System.out.println(this.mensaje);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();

			this.mensaje = e.getMessage();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", this.mensaje);
			System.out.println(this.mensaje);
			return null;
		}

	}

	/**
	 * Este metodp busca la cliente donde regresa una cuenta en la que se va realiar
	 * el deposito, en la cual se sumara el monto anterior con el monto actual, y la
	 * fecha que se realiza la transacion
	 * 
	 * @return
	 */
	public String buscarCliente() {
		try {
			cliente = clienteON.buscar(this.cedula);
			cuenta = cON.buscarCuenta(cliente.getCuenta().getNumero());
			System.out.println(cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String depositosC(Cajero cajero) {
		System.out.println("Metodo Cajero");
		try {
			Double saldo = cliente.getCuenta().getSaldo();
			Double total = saldo + t.getMonto();

			cuenta.setSaldo(total);
			cliente.setCuenta(cuenta);
			t.setSaldoCuenta(total);
			t.setTipo("Deposito");
			t.setCliente(cliente);
			t.setCajero(cajero);
			t.setFecha(myDate);
			t.setDepositante(t.getDepositante());
			transacciones.add(t);
			cliente.setTransacciones(transacciones);
			cajero.setTransacciones(transacciones);
			clienteON.editar(cliente);
			t = new Transaccion();
			cuenta = new Cuenta();
			cajero = new Cajero();
			cliente = new Cliente();
			cedula = "";
			transacciones.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}