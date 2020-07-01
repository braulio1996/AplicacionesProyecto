package ec.edu.ups.vista;

import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.ClienteTemporal;
import ec.edu.ups.Modelo.Respuesta;
import ec.edu.ups.Modelo.TransferenciaTemporal;



public class Rest {

    final String urlString1 = "http://localhost:8080/AplicacionesBanco/rs/Cliente/BuscarCliente";
    final String urlString2 = "http://localhost:8080/AplicacionesBanco/rs/Cliente/Deposito";
    final String urlString3 = "http://localhost:8080/AplicacionesBanco/rs/Cliente/Login";
    final String urlString4 = "http://localhost:8080/AplicacionesBanco/rs/Cliente/Retiro";
    final String urlString5 = "http://localhost:8080/AplicacionesBanco/rs/Cliente/transferencia";
    final String urlString6 = "http://localhost:8080/AplicacionesBanco/rs/Cliente/listarCliente";
    Date myDate = new Date();
	
	public ClienteTemporal buscarCliente(String cedula) {
		Client client = ClientBuilder.newClient();
		
		
		WebTarget target = client.target(urlString1).queryParam("cedula", cedula);

		ClienteTemporal c= target.request().get(ClienteTemporal .class);
		
		client.close();
		
		return c;		
	}
	
	public List<Cliente> listar() {
		
		Client client = ClientBuilder.newClient();		
		WebTarget target = client.target(urlString6);

		List<Cliente> c = target.request().get(new GenericType<List<Cliente>>() {});
		
		client.close();
		
		return c;		
	}
	
	public Respuesta transferencia(TransferenciaTemporal t) {
		
		Client client = ClientBuilder.newClient();		
		WebTarget target = client.target(urlString5);
		Respuesta respuesta = target.request().post(Entity.json(t), Respuesta.class);
		
		
	
		return respuesta;
	}
	public Respuesta retiro(String cajero, String cliente, Double monto) {
		
		
		Client client = ClientBuilder.newClient();		
		WebTarget target = client.target(urlString4).queryParam("cajero", cajero).queryParam("cliente", cliente).queryParam("monto", monto);
		Respuesta respuesta = target.request().post(null,Respuesta.class);
		
		
	
		return respuesta;
	}
	
	public Respuesta desposito(String cajero, String cliente, Double monto,String depositante) {
		
		
		Client client = ClientBuilder.newClient();		
		WebTarget target = client.target(urlString2).queryParam("cajero", cajero).queryParam("cliente", cliente).queryParam("monto", monto).queryParam("depositante", depositante);
		Respuesta respuesta = target.request().post(null,Respuesta.class);
		
		
	
		return respuesta;
	}
	public Respuesta login(String correo,String clave) {
		Client client = ClientBuilder.newClient();		
		WebTarget target = client.target(urlString3).queryParam("correo", correo).queryParam("clave", clave);
		Respuesta respuesta = target.request().post(null,Respuesta.class);
		
		return respuesta;
	}
}