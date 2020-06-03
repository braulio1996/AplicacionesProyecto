package ec.edu.ups.Vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import ec.edu.ups.Modelo.Acceso;
import ec.edu.ups.Modelo.Administrador;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.Transaccion;
import ec.edu.ups.Modelo.Transferencia;
import ec.edu.ups.ON.CajeroON;
import ec.edu.ups.ON.ClienteON;
import ec.edu.ups.ON.CuentaON;

@ManagedBean
@ApplicationScoped
public class CajeroMB {
	@Inject
	private CuentaON cON;
	
	@Inject
	private CajeroON cjON;
	
	Date myDate = new Date();

	private Cliente cliente;
	private Cajero cajero;
	private Transaccion t;
	private List<Transaccion> transacciones;
	
	

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		cajero =new Cajero();
		t = new Transaccion();
		transacciones = new ArrayList<>();
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



	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}



	public String retiro() {
		try {
			Cuenta cuenta=cON.buscarCuenta(t.getCuenta().getNumero());
			Double saldo=cuenta.getSaldo();
			Double total=saldo-t.getMonto();
			t.setTipo("Retiro");
			t.setCuenta(cuenta);
			t.setCajero(cajero);
			t.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
			transacciones.add(t);
			cuenta.setTransacciones(transacciones);
			cajero.setTransacciones(transacciones);
			cON.editar(cuenta);
			cjON.editar(cajero);
			cajero =new Cajero();
			cuenta = new Cuenta();
			transacciones.clear();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	
	public String deposito() {
		try {
			Cuenta cuenta=cON.buscarCuenta(t.getCuenta().getNumero());
			Double saldo=cuenta.getSaldo();
			Double total=saldo+t.getMonto();
			t.setTipo("Retiro");
			t.setCuenta(cuenta);
			t.setCajero(cajero);
			t.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
			transacciones.add(t);
			cuenta.setTransacciones(transacciones);
			cajero.setTransacciones(transacciones);
			cON.editar(cuenta);
			cjON.editar(cajero);
			cajero =new Cajero();
			cuenta = new Cuenta();
			transacciones.clear();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	public String deposito(Double monto,Cliente cliente) {
		
		return null;
		
	}

}