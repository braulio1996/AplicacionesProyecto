package ec.edu.ups.Vista;

import java.io.Console;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.servlet.http.Part;

import ec.edu.ups.Modelo.Amortizacion;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.CreditoAprobado;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.SolicitudCredito;
import ec.edu.ups.Modelo.Transferencia;
import ec.edu.ups.ON.ClienteON;
import ec.edu.ups.ON.SolicitudON;
import ec.edu.ups.Services.TransferenciaTemporal;

/**
 * Esta Clase define los ManagedBean
 * 
 * @version: 01/05/2020
 * @author Braulio Castro
 *
 */
@ManagedBean
@ApplicationScoped
public class ClienteMB {
	@Inject
	private ClienteON cON;
	
	@Inject
	private SolicitudON sON;
	
	Date myDate = new Date();

	private Cliente cliente;
	private Transferencia t;
	private List<Transferencia> transferencias;
	private SolicitudCredito s;
	private CreditoAprobado c;
	private Amortizacion m;
	private String cuentaDestino;
	private Double monto;
	private Cuenta cuenta;
	private String correo;
	private Part file;
	private Part file1;
	private Part file2;
	private Part file3;
	private int codSolbus;
	private TransferenciaTemporal tt;
	
	@PostConstruct
	public void init() {
		cliente = new Cliente();
		t = new Transferencia();
		s = new SolicitudCredito();
		c= new CreditoAprobado();
		m=new Amortizacion();
		transferencias = new ArrayList<>();
		tt = new TransferenciaTemporal();
		
		try {
			sON.debitoCreditoVencido();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public int getCodSolbus() {
		return codSolbus;
	}



	public void setCodSolbus(int codSolbus) {
		this.codSolbus = codSolbus;
	}



	public Amortizacion getM() {
		return m;
	}


	public void setM(Amortizacion m) {
		this.m = m;
	}


	public CreditoAprobado getC() {
		return c;
	}


	public void setC(CreditoAprobado c) {
		this.c = c;
	}


	public SolicitudCredito getS() {
		return s;
	}


	public void setS(SolicitudCredito s) {
		this.s = s;
	}


	public Part getFile() {
		return file;
	}


	public void setFile(Part file) {
		this.file = file;
	}


	public Part getFile1() {
		return file1;
	}


	public void setFile1(Part file1) {
		this.file1 = file1;
	}


	public Part getFile2() {
		return file2;
	}


	public void setFile2(Part file2) {
		this.file2 = file2;
	}


	public Part getFile3() {
		return file3;
	}


	public void setFile3(Part file3) {
		this.file3 = file3;
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

	
	public TransferenciaTemporal getTt() {
		return tt;
	}

	public void setTt(TransferenciaTemporal tt) {
		this.tt = tt;
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

	/**
	 * 
	 * El metodeo transferencai se obtiene el id cliente es decir cuenta origen y
	 * cuenta destino y el monto en la cual incluira la fecha de transacion
	 * 
	 * @return
	 */
	
	public String trasferencia() {
		try {
			return cON.transferencia(tt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Este metodo genra cuenta del del usuario
	 */
	public void generarCuenta() {
		Date d = new Date();
		String numero = d.getDate() + "" + d.getHours() + d.getSeconds() + System.currentTimeMillis();
		cuenta.setNumero(numero);
	}

	/**
	 * Metodo para cambiar la contraseña y/o generar nuevas contraseñas de forma
	 * aleatoria antes se tendra que verificar si este usuario esta registrado para
	 * poder enviar la clave generada al correo personal
	 * 
	 * @return
	 */
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
//Metodo para crear solicitudes 
	public String solicitudCredito(Cliente cliente) throws Exception {
		try {
			if (file != null || file1 != null || file2 != null || file3 != null) {
				int size = (int) file.getSize();
				int size1 = (int) file1.getSize();
				int size2 = (int) file2.getSize();
				int size3 = (int) file3.getSize();
				byte[] cedulaF;
				byte[] cedulaT;
				byte[] plantilla;
				byte[] rol;
				if (size > 0 || size1 > 0 || size2 > 0 || size3 > 0) {
					cedulaF = new byte[size];
					cedulaT = new byte[size1];
					plantilla = new byte[size2];
					rol = new byte[size3];
					
					file.getInputStream().read(cedulaF);
					file1.getInputStream().read(cedulaT);
					file2.getInputStream().read(plantilla);
					file3.getInputStream().read(rol);
					s.setFotoCedulaT(cedulaT);
					s.setFotoCedulaF(cedulaF);
					s.setFotoPlantilla(plantilla);
					s.setFotoRolPagos(rol);
					s.setObservaciones("Pendiente");
					s.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
				}
				
				cON.solicitudCredito(cliente, s);
				s = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "solicitudCredito?faces-redirect=true";
	}
	public List<SolicitudCredito>listarSolicitud(int cliente){
		try {
			return cON.listarSoli(cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<SolicitudCredito>allSolicitud(){
		try {
			return cON.allSoli();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void buscarSolicitud(int codigo) {
		this.s =  sON.buscarSolicitud(codigo);
	}
	
	public List<SolicitudCredito>listarSolicitudTipo(String tipo){
		try {
			return cON.listarSolicitudTipos(tipo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//Realizar debito desde el cliente web
//crear un boton en la lista de amortizacion y pagar 
 public String debitoCredito() {
	 sON.debitoCredito(m, monto);
	
	 return "inicioCliente?faces-redirect=true"; 
 }
 
 
// public void getData(SolicitudCredito soli) {
//	System.out.println("Datoooos: " + soli);	
// }

}
