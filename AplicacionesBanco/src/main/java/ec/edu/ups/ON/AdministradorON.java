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

}
