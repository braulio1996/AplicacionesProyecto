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

@Stateless
public class AdministradorON {

	@Inject
	private AdministradorDAO pdao;
	

	public void registrar(Administrador administrador) throws Exception {
		pdao.registrar(administrador);

	}

	public void update(Administrador administrador) throws Exception {
		pdao.update(administrador);

	}

	public Administrador loginC(String correo, String clave) throws Exception {
		
		return pdao.login(correo, clave);
	}
	
}
