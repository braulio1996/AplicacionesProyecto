package ec.edu.ups.ON;


import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ec.edu.ups.DAO.ClienteDAO;
import ec.edu.ups.DAO.CuentaDAO;
import ec.edu.ups.DAO.SolicitudDAO;
import ec.edu.ups.Modelo.Amortizacion;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.CreditoAprobado;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.SolicitudCredito;
import ec.edu.ups.Modelo.Transaccion;
import ec.edu.ups.Services.Respuesta;
import ec.edu.ups.Services.TransferenciaTemporal;
import ec.edu.ups.Modelo.Transferencia;



/**
 * Esta Clase define los metodos de Objetos de Negocio
 * @version: 31/05/2020
 * @author Braulio Castro
 *
 */
@Stateless
public class SolicitudON {

	@Inject
	private SolicitudDAO pdao;
	
	@Inject
	private CuentaDAO cdao;
	LocalDate myDate = LocalDate.now();
	
	@Inject
	private ClienteON clieOn;
	

	
	public List<SolicitudCredito> listarSoli() throws Exception {
		return pdao.listSolicitud();
	}
    
	public SolicitudCredito buscarSolicitud(int codigo) {
		return pdao.buscarSolicitud(codigo);
	}
	
	/**
	 * Actualiza el objeto de tipo Solicitud
	 * @param cliente el objeto que se va actualizar
	 * @throws Exception  control de Excepciones
	 */
	public void editar(SolicitudCredito solicitud) throws Exception {
		pdao.editar(solicitud);
	}

	public int generarNum() {
		String NUMEROS = "0123456789";
		
		String pswd = "";

		String key = NUMEROS;

		for (int i = 0; i < 5; i++) {
			pswd += (key.charAt((int) (Math.random() * key.length())));
		}
		
		int num = Integer.parseInt(pswd);
		return num;
	}
	
	public void aprobarSolicitud(SolicitudCredito solicitud, CreditoAprobado credito) throws Exception {
		List<Amortizacion>aa= new ArrayList<>();
		
		Cuenta cuenta =solicitud.getCliente().getCuenta();
		cuenta.setSaldo(cuenta.getSaldo()+solicitud.getMonto());
		cdao.editarCuenta(cuenta);
		
		Double interes=0.12*solicitud.getMeses();
		Double total=solicitud.getMonto()*interes;
		Double capital=solicitud.getMonto();
		Double pago=(total/solicitud.getMeses());
		System.out.println("--------------------- "+pago);
		Double pp=solicitud.getIngreso()-solicitud.getEgreso();
		String mensaje;
		if(pago>pp) {
			mensaje="Cliente no Valido para prestamo TASA DE PAGO = "+pago+"  Excede a la TASA de pago valida ="+pp;
			System.out.println(mensaje);
			clieOn.enviarCorreo(solicitud.getCliente().getCorreo(), "Credito Rechazado", mensaje);
		}else {
			
		
//		 Calendar fecha = Calendar.getInstance();
//	        int año = fecha.get(Calendar.YEAR);
//	        int mes = fecha.get(Calendar.MONTH) + 1;
//	        int dia = fecha.get(Calendar.DAY_OF_MONTH);
	        java.util.Date fecha = new Date();
	        int dia=fecha.getDay();
	        int año=fecha.getYear();
	        int mes=fecha.getMonth();
	      
		for (int i = 0; i < solicitud.getMeses(); i++) {
			Amortizacion a = new Amortizacion();
			if(mes==13) {
				año=año+1;	
				mes=1;
			}
			a.setNumeroPago(i);
			a.setFechaVencimiento(dia+"/"+mes+"/"+año);
			a.setCapital(capital);
			a.setInteres(interes);
			a.setPago(pago);
			a.setSaldo(pago);
			a.setEstado("Pendiente");
			aa.add(a);
			credito.setAmortizacion(aa);
			mes++;
			pdao.crearAmortizacion(a);
		}
		solicitud.setEstado("Aprobado");
		pdao.editar(solicitud);
		
		pdao.amortizacion(credito);
		clieOn.enviarCorreo(solicitud.getCliente().getCorreo(), "Credito Aprovado", ""+aa.toString());
	}
	}
		public void debitoCreditoVencido() throws Exception {
			Transaccion t= new Transaccion();
			List<Transaccion>transacciones = new ArrayList<>();
			List<CreditoAprobado> creditos=pdao.listaCreditosAprobado();
			Amortizacion a;
			
			for(CreditoAprobado c:creditos) {
				for(Amortizacion amortizacion:c.getAmortizacion()) {
					if(amortizacion.getEstado().equalsIgnoreCase("Vencido")) {
				Double saldo=c.getCliente().getCuenta().getSaldo();		
				Cuenta cuenta=c.getCliente().getCuenta();
				Cliente cliente =c.getCliente();
				Double pago=amortizacion.getPago();
				Double saldoCredito=amortizacion.getSaldo();
				Double saldoT=saldo-pago;
				Double saldoCreditoT=saldoCredito-saldo;
				if(saldoT<0) {
					cuenta.setSaldo(0.0);
				}else {
					cuenta.setSaldo(saldoT);
				}
				if(saldoCreditoT<0) {
					amortizacion.setSaldo(0);
					amortizacion.setEstado("Pagado");
				}else {
					amortizacion.setSaldo(saldoCreditoT);
				}
				
				cliente.setCuenta(cuenta);
				t.setSaldoCuenta(saldoT);
				t.setTipo("DebitoCredito");
				t.setCliente(cliente);
				t.setFecha(myDate);
				t.setMonto(saldoCreditoT);
				t.setDepositante("NaN");
				transacciones.add(t);
				cliente.setTransacciones(transacciones);
				pdao.upAmortizacion(amortizacion);
				clieOn.editar(cliente);
					}
			}
			}
		}
		
	public void negarSoli(SolicitudCredito solicitud) throws Exception {
		solicitud.setEstado("Negado");
		
		
		pdao.editar(solicitud);
	}
	
	public void debitoCredito(Cliente cliente, Amortizacion amortizacion,Double monto) {
		Transaccion t= new Transaccion();
		List<Transaccion>transacciones = new ArrayList<>();
		Amortizacion a;
		try {	
		a=amortizacion;
	
		Cuenta cuenta=cliente.getCuenta();
		Double pago=amortizacion.getPago();
		Double saldoCredito=amortizacion.getSaldo();
		Double saldoT=monto-pago;
		Double saldoCreditoT=saldoCredito-monto;
		if(saldoT<0) {
			cuenta.setSaldo(0.0);
		}else {
			cuenta.setSaldo(saldoT);
		}
		if(saldoCreditoT<0) {
			a.setSaldo(0);
			a.setEstado("Pagado");
		}else {
			a.setSaldo(saldoCreditoT);
		}
		t.setSaldoCuenta(saldoT);
		t.setTipo("DebitoCredito");
		t.setCliente(cliente);
		t.setFecha(myDate);
		t.setMonto(saldoCreditoT);
		t.setDepositante(cliente.getNombre());
		transacciones.add(t);
		cliente.setTransacciones(transacciones);
		pdao.upAmortizacion(a);
		clieOn.editar(cliente);
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	
			
		
	}
	public void fechavencida() throws Exception {
		List<Amortizacion> creditos=pdao.listaCreditosT();
		Amortizacion a;
		
		for(Amortizacion amortizacion:creditos) {
			a=amortizacion;
			Date fechaactual = new Date(System.currentTimeMillis());
			String fechaInicio =a.getFechaVencimiento();
			SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaInicioDate = date.parse(fechaInicio);  //String a date
	
			 //comprueba si es que inicio esta después que fecha actual       
			if(fechaInicioDate.after(fechaactual)){
			    System.out.println("Fecha inicio mayor");
			    a.setEstado("Vencido");
			}else{
				 a.setEstado("Pendiente");
			    
			}
			pdao.upAmortizacion(a);
		}
	}
	
	public List<SolicitudCredito> solPendientes() throws Exception {
		return pdao.solPendientes();
	}
	
	public List<SolicitudCredito> solRespuesta() throws Exception {
		return pdao.solRespuesta();
	}
	
	public List<SolicitudCredito> buscarSol(String estado) throws Exception {
		return pdao.buscarSol(estado);
	}
	
	public SolicitudCredito verDetalle(int codigo) {
		return pdao.verDetalle(codigo);
	}
}