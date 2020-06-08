package ec.edu.ups.ON;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ec.edu.ups.DAO.AdministradorDAO;
import ec.edu.ups.Modelo.Administrador;
import ec.edu.ups.Modelo.Cliente;

/**
 * En esta clase se define el Objeto de Negocio - AdministradorDAO
 * @author Marcela
 * @version 31/05/2020
 *
 */
@Stateless
public class AdministradorON {

	@Inject
	private AdministradorDAO pdao;
	

	/**
	 * Registra el administrador del metodo registrar de AdministradorON
	 * @param administrador de tipo Objeto  Adminstrador que se va registrar
	 * @throws Exception control de Excepciones
	 */
	public void registrar(Administrador administrador) throws Exception {
		pdao.registrar(administrador);

	}

	/**
	 * Actualiza el administrador, este metodO hace uso del AdministradorDAO
	 * @param administrador Objeto de Tipo Administrador
	 * @throws Exception control de Excepciones
	 */
	public void update(Administrador administrador) throws Exception {
		pdao.update(administrador);

	}

	/**
	 * verifica si el administrador cumple  con los respectivos credenciales 
	 * @param correo parametro de identificacion de  credenciales del administrador
	 * @param clave parametro de identificacion de credenciales del usuario
	 * @return correo  valor devuelto si este exiate
	 * @return clave valor devuelto si este existe
	 * @throws Exception control de Excepciones
	 */
	public Administrador loginC(String correo, String clave) throws Exception {
		
		return pdao.login(correo, clave);
	}
	
	/**
	 * Genera las cotraseñas de usuario de forma aleatroia con numeros, letras mayuculas y minusculas
	 * @return pswd  
	 */
	public String generarContraseña() {
		
		String NUMEROS = "0123456789";
		String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

		String pswd = "";

		String key = NUMEROS + MAYUSCULAS + MINUSCULAS;

		for (int i = 0; i < 12; i++) {
			pswd += (key.charAt((int) (Math.random() * key.length())));
		}
		
		return pswd;
	}
	
	/**
	 * Genera de manera aleatoria los id de cuentas 
	 * @return pswd
	 */
	public String generarCuenta() {
		
		String NUMEROS = "0123456789";
		

		String pswd = "";

		String key = NUMEROS;

		for (int i = 0; i < 10; i++) {
			pswd += (key.charAt((int) (Math.random() * key.length())));
		}
		
		return pswd;
	}
}
