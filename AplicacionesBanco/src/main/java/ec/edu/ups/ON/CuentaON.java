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

import ec.edu.ups.DAO.CuentaDAO;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.Transferencia;

/**
 * Esta Clase define los metodos de Objetos de Negocio
 * @version: 31/05/2020
 * @author Braulio Castro
 *
 */
@Stateless
public class CuentaON {

	@Inject
	private CuentaDAO pdao;

	private Transferencia trasferencia;
	private List<Transferencia> trasferencias;

	/**
	 * @param Cuenta
	 * @return
	 * @throws Exception
	 */
	public boolean guardar(Cuenta cuenta) throws Exception {
		return pdao.guardar(cuenta);

	}


	/**
	 * Metodo para listar Cuentas
	 * @return Lista de Cuentas
	 * @throws Exception Control de excepciones
	 */
	public List<Cuenta> listar() throws Exception {
		return pdao.listar();

	}

	public void editar(Cuenta cuenta) throws Exception {
		pdao.editarCuenta(cuenta);
	}
	
	public Cuenta buscarCuenta(String numero) {
		try {
			return pdao.buscarCuenta(numero);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
