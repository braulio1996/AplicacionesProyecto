package ec.edu.ups.ON;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.Transaccion;
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

	
	private List<Transferencia> trasferencias;


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

	public Cliente buscar(String correo) throws Exception {
		return pdao.buscar(correo);
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
	public String transferencia(String cuentaO, String cuentaDestino, Double monto,String nombre,String correo)  {
		Transferencia trasferencia = new Transferencia();
        List<Transferencia>transferencias = new ArrayList<>();
        String mensaje="";
        try {
        	Cuenta cuentaOrigen = pdao.buscarCuenta(cuentaO);
        	Cuenta cuentaD = pdao.buscarCuenta(cuentaDestino);
        	if(cuentaD==null) {
        		mensaje="No existe la Cuenta";
        	}else if(cuentaOrigen.getSaldo()<monto) {
        		
        	mensaje="ERROR. El monto es mayor al saldo ("+monto+")";
        	}else {
    		Double saldoDestino = cuentaD.getSaldo() + monto;
    		Double salgoOrigen = cuentaOrigen.getSaldo() - monto;
    		trasferencia.setMonto(monto);
    		trasferencia.setNumeroCuenta(cuentaDestino);
    		trasferencia.setNombre(nombre);
    		trasferencia.setCorreo(correo);
    		trasferencia.setTipoCuenta("Directa");
    		trasferencia.setConcepto("NaN");
    		cuentaD.setSaldo(saldoDestino);
    		cuentaOrigen.setSaldo(salgoOrigen);
    		transferencias.add(trasferencia);
    		cuentaOrigen.setTransferencias(transferencias);
    		pdao.editarCuenta(cuentaD);
    		pdao.editarCuenta(cuentaOrigen);
    		mensaje="Transferencia Exitosa";
        }
        }catch (Exception e) {
			mensaje=e.getMessage();
		}
		return mensaje;
	
	}
	
	
	/**
	 * Busca al cliente mediante el correo
	 * @param correo identificador del objeto Cliente que se va buscar
	 * @return  correo retorna los valores si existe
	 */
	public Cliente buscarCorreo(String correo) {
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
}
