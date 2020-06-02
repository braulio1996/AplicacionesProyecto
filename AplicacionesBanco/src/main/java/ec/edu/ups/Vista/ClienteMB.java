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

import ec.edu.ups.Modelo.Administrador;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.Transferencia;
import ec.edu.ups.ON.ClienteON;

@ManagedBean
@ApplicationScoped
public class ClienteMB {
	@Inject
	private ClienteON cON;
	Date myDate = new Date();

	private Cliente cliente;
	private Transferencia t;
	private List<Transferencia> transferencias;
	private String cuentaDestino;
	private Double monto;
	private Cuenta cuenta;
	private String correo;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		t = new Transferencia();
		transferencias = new ArrayList<>();
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	public Transferencia getT() {
		return t;
	}

	public void setT(Transferencia t) {
		this.t = t;
	}

	public List<Transferencia> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(List<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}

	public String getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String trasferencia() {
		try {
			t = cON.trasferencia(cliente, cuentaDestino, monto);
			t.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(myDate));
			transferencias.add(t);
			System.out.println(t.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void generarCuenta() {
		Date d = new Date();
		String numero = d.getDate() + "" + d.getHours() + d.getSeconds() + System.currentTimeMillis();
		cuenta.setNumero(numero);
	}

	
	
	public String generarContraseña() {
		cliente = cON.buscarCorreo(this.correo);
		if (cliente != null) {
			try {
				String NUMEROS = "0123456789";
				String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

				String pswd = "";

				String key = NUMEROS + MAYUSCULAS + MINUSCULAS;

				for (int i = 0; i < 12; i++) {
					pswd += (key.charAt((int) (Math.random() * key.length())));
				}

				System.out.println(this.correo);
				this.cliente.setCorreo(this.correo);

				cON.enviarCorreo(this.correo, "Cambio de Contraseña", "Su nueva contraseña es: " + pswd);
				
				cliente.setClave(pswd);
				cON.editar(cliente);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		} else {
			System.out.println("No existe el correo");
			return null;
		}

	}
}
