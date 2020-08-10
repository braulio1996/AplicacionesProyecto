package ec.edu.ups.ON;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.ArrayList;
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
import ec.edu.ups.DAO.SolicitudDAO;
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
public class ClienteON {

	@Inject
	private ClienteDAO pdao;
	@Inject
	private SolicitudDAO sdao;
	Date myDate = new Date();
	
	




	/**
	 * Guarda los  objetos Cliente
	 * @param cliente objeto de Tipo Cliente que se va guardar
	 * @return cliente  retorna el valor 
	 * @throws Exception control de Excepciones
	 */
	public boolean guardar(Cliente cliente) throws Exception {
		return pdao.guardar(cliente);

	}

	/**
	 * Consulta verifica las credenciales del usuario sean las correctas
	 * @param correo identificador unico del objeto Persona de tipo Cliente
	 * @param clave identificador unico del Objeto 
	 * @return Cliente resnorna el valor si cumple con las credenciales
	 * @throws Exception control de Excepciones
	 */
	public Cliente loginC(String correo, String clave) throws Exception {

		return pdao.login(correo, clave);
	}

	/**
	 * Metodo para listar Clientes 
	 * @return Lista de clientes 
	 * @throws Exception Control de excepciones
	 */
	public List<Cliente> listar() throws Exception {
		return pdao.listar();
	}
	public List<Cliente> listar2(int codigo) throws Exception {
		return pdao.listar2(codigo);
	}
	public List<SolicitudCredito> listarSoli(int cliente, String estado) throws Exception {
		return pdao.listSolicitud(cliente, estado);
	}
    
	public List<SolicitudCredito> allSoli() throws Exception {
		return pdao.allSolicitud();
	}
    
	
	
	public List<SolicitudCredito> listarSolicitudTipos(String tipo) throws Exception {
		return pdao.listSolicitudTipo(tipo);
	}
	
	/**
	 * Actualiza el objeto de tipo Cliente
	 * @param cliente el objeto que se va actualizar
	 * @throws Exception  control de Excepciones
	 */
	public void editar(Cliente cliente) throws Exception {
		pdao.editar(cliente);
	}

	/**
	 * Metodo que busca un cliente por su cedula
	 * @param cedula del cliente o ruc
	 * @return un objeto persona de tipo cliente
	 * @throws Exception control de excepciones
	 */

	public Cliente buscar(String cedula) throws Exception {
		return pdao.buscar(cedula);
	}

	/** Metod que busca un cliente por su codigo o Id
	 * @param id o codigo de cliente
	 * @return un objeto persona de tipo cliente
	 */
	public Cliente read(int id) {
		return pdao.read(id);
	}

	/**
	 * Meto de Elimina una persona por su cedula
	 * @param cedula o ruc de la persona a eliminar
	 * @throws Exception control de Excepciones
	 */
	public void eliminar(String cedula) throws Exception {
		pdao.eliminar(cedula);

	}

	/**
	 * Metodo que busca una cuenta bancaria
	 * @param numero de cuenta 
	 * @return Objeto de tipo cuenta 
	 * @throws Exception control de Excepciones
	 */
	public Cuenta buscarCuenta(String numero) throws Exception {
		return pdao.buscarCuenta(numero);
	}

	
	public Cuenta cuentaCli(Cliente c) {
		return pdao.cuentaCliente(c);
	}
	

	public List<Transaccion> transCli(int c, LocalDate fechaDesde, LocalDate fechaHasta, String tipo) throws Exception {
		return pdao.transCliente(c, fechaDesde, fechaHasta, tipo);
	}
	
	/**
	 * Este metodo realiza una trasferencia 
	 * @param cliente de session oh duenio de la cuenta
	 * @param cuentaDestino numero de cuenta del beneficiario
	 * @param monto de la operacion doalres
	 * @return un objeto Trasferencia con los parametros anteriores
	 * @throws Exception control de excepciones
	 */

	
	public String transferencia(TransferenciaTemporal transferenciat)  {
		
        List<Transferencia>transferencias = new ArrayList<>();
        String mensaje;
        Transferencia t = new Transferencia();
        try {
        	Cuenta cuentaOrigen = pdao.buscarCuenta(transferenciat.getCuentaOrigen());
        	Cuenta cuentaD = pdao.buscarCuenta(transferenciat.getNumeroCuenta());
        	if(cuentaD==null) {
        		mensaje="No existe la Cuenta";
        	}else if(cuentaD==cuentaOrigen) {
        		mensaje="Error No se puede realizar una transferencia a su misma Cuenta";
        		
        	}else if(cuentaOrigen.getSaldo()<transferenciat.getMonto()) {
        		
        	mensaje="ERROR. El monto es mayor al saldo ("+transferenciat.getMonto()+")";
        	}else {
        		
    		Double saldoDestino = cuentaD.getSaldo() + transferenciat.getMonto();
    		Double salgoOrigen = cuentaOrigen.getSaldo() - transferenciat.getMonto();
    		cuentaD.setSaldo(saldoDestino);
    		cuentaOrigen.setSaldo(salgoOrigen);
    		transferenciat.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
    		t.setTipo(transferenciat.getTipo());
    		t.setFecha(transferenciat.getFecha());
    		t.setInstitucion(transferenciat.getInstitucion());
    		t.setTipoCuenta(transferenciat.getTipoCuenta());
    		t.setNumeroCuenta(transferenciat.getNumeroCuenta());
    		t.setMonto(transferenciat.getMonto());
    		t.setIdentificacion(transferenciat.getIdentificacion());
    		t.setNombre(transferenciat.getNombre());
    		t.setCorreo(transferenciat.getCorreo());
    		t.setConcepto(transferenciat.getConcepto());
    		transferencias.add(t);
    		cuentaOrigen.setTransferencias(transferencias);
    		pdao.editarCuenta(cuentaD);
    		pdao.editarCuenta(cuentaOrigen);
    		mensaje="Transferencia Exitosa";
        }
        }catch (Exception e) {
			mensaje=e.getMessage();
			System.out.println(e.toString());
		}
		return mensaje;
	
	}
	
	/**
	 * Busca al cliente mediante el correo
	 * @param correo identificador del objeto Cliente que se va buscar
	 * @return  correo retorna los valores si existe
	 */
	public Cliente buscarCorreo(String correo) {
		System.out.println("Entro cliente ON");
		return pdao.buscarCorreo(correo);
	}
	
	
	
	/**
	 * Enviar el correo , reconociendo el nombre del host, puerto, se requiere el correo y contraseña para conectarse para
	 * poder enviar al correo el asunto y mensaje
	 * @param correo identificador del usuario 
	 * @param asunto   el asunto a tratarse
	 * @param mensaje cualquie tipo de mensaje
	 */
	public void enviarCorreo(String correo, String asunto, String mensaje) {
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        
        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEnvia = "aplicacionesdistribuidas030@gmail.com";
        String contrasena = "distribuidas030";
     
        
        MimeMessage mail = new MimeMessage(sesion);
        try {
            mail.setFrom(new InternetAddress (correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (correo));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            
            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia,contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
            transportar.close();
            
            System.out.println( "Listo, revise su correo");
            
            
        } catch (AddressException ex) {
            System.out.println(ex);
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
	}
	public long contar() {
		return pdao.contar();
	}
	
	public LocalDate restarFecha(LocalDate hasta) {
		try {
			return hasta.minusDays(30);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
			return null;
	}
	public  Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
	public LocalDate convert(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}	

	
	
	public boolean solicitudCredito(Cliente cliente, SolicitudCredito s) throws Exception {
		List<SolicitudCredito> solicitudes = new ArrayList<SolicitudCredito>();
		try {
			System.out.println("------Solicitud-------"+s.getTipo());
			s.setEstado("Pendiente");
			solicitudes.add(s);
			cliente.setSolicitudesCredito(solicitudes);
			s.setCliente(cliente);
			//sdao.add(s);
			pdao.editar(cliente);
			s=new SolicitudCredito();
			solicitudes.clear();
		}catch (Exception e) {
			System.out.println("---Error Solicitud-------"+e.getMessage());
			throw new Exception(e.toString());
		}
		return true;
			
	}
	
	public Respuesta generarContraseña(String correo) {
		Respuesta r= new Respuesta();
		Cliente cliente = pdao.buscarCorreo(correo);
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
	
				
				cliente.setCorreo(correo);
				r.setCodigo(1);
				r.setMensaje("Su nueva contraseña es: " + pswd);
	
				enviarCorreo(correo, "Cambio de Contraseña", "Su nueva contraseña es: " + pswd);
	
				cliente.setClave(pswd);
				pdao.editar(cliente);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return r;
		} else {
			System.out.println("No existe el correo");
			r.setCodigo(0);
			r.setMensaje("No existe el correo");
			return r;
		}
	}
	
	public List<CreditoAprobado> listarCA(int cliente) throws Exception {
		return pdao.listCA(cliente);
	}

	public CreditoAprobado buscarCA(int cliente, int codigo) throws Exception {
		return pdao.buscarCA(cliente, codigo);
	}
	
	public List<Cliente> listarCliente(int cliente) throws Exception {
		return pdao.listar();
	}

}