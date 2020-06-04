package ec.edu.ups.ON;

import java.sql.SQLException;
import java.util.ArrayList;
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

	private Transferencia trasferencia;
	private List<Transferencia> trasferencias;

	/**
	 * Guarda los  clientesON de la entidad adminstradorDAO
	 * @param cliente objeto de Tipo Cliente
	 * @return cliente 
	 * @throws Exception
	 */
	public boolean guardar(Cliente cliente) throws Exception {
		return pdao.guardar(cliente);

	}

	/**
	 * Metodo de login, valida crdenciales de cliente
	 * @param correo
	 * @param clave
	 * @return Cliente que cumpla con las credenciales
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
	 * @throws Exception
	 */
	public void eliminar(String cedula) throws Exception {
		pdao.eliminar(cedula);

	}

	/**
	 * Metodo que busca una cuenta bancaria
	 * @param numero de cuenta 
	 * @return Objeto de tipo cuenta
	 * @throws Exception
	 */
	public Cuenta buscarCuenta(String numero) throws Exception {
		return pdao.buscarCuenta(numero);
	}

	/**
	 * Este metodo realiza una trasferencia 
	 * @param cliente de session oh duenio de la cuenta
	 * @param cuentaDestino numero de cuenta del beneficiario
	 * @param monto de la operacion doalres
	 * @return un objeto Trasferencia con los parametros anteriores
	 * @throws Exception control de excepciones
	 */
	public Transferencia trasferencia(Cliente cliente, String cuentaDestino, Double monto) throws Exception {
		trasferencia = new Transferencia();

		Cuenta cuentaD = pdao.buscarCuenta(cuentaDestino);
		Cuenta cuentaO = cliente.getCuenta();
		Double saldoDestino = cuentaD.getSaldo() + monto;
		Double salgoOrigen = cuentaO.getSaldo() - monto;
		trasferencia.setMonto(monto);
		trasferencia.setNumeroCuenta(cuentaDestino);
		cuentaD.setSaldo(saldoDestino);
		pdao.editarCuenta(cuentaD);
		pdao.editarCuenta(cuentaO);

		return trasferencia;
	}
	
	public Cliente buscarCorreo(String correo) {
		return pdao.buscarCorreo(correo);
	}
	
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
	
	public void controlAccesos() {
		
	}
}
