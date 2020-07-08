package ec.edu.ups.vista;

import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.logging.LoggingFeature;

import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.ClienteTemporal;
import ec.edu.ups.Modelo.Respuesta;
import ec.edu.ups.Modelo.TransferenciaTemporal;

public class Rest {

	public String urlString1 = "http://localhost:18080/AplicacionesBanco/services/users/BuscarCliente";
	public String urlString2 = "http://localhost:18080/AplicacionesBanco/services/users/Deposito";
	public String urlString3 = "http://localhost:18080/AplicacionesBanco/services/users/Login";
	public String urlString4 = "http://localhost:18080/AplicacionesBanco/services/users/Retiro";
	public String urlString5 = "http://localhost:18080/AplicacionesBanco/services/users/transferencia";
	public String urlString6 = "http://localhost:18080/AplicacionesBanco/services/users/listarCliente";

	public ClienteTemporal buscarCliente(String cedula) {
		Client client = ClientBuilder.newBuilder()
				.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_ANY)
				.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO").build();

		WebTarget webTarget = client.target(urlString1);

		webTarget = webTarget.queryParam("cedula", cedula);

		final Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		final Response response = invocationBuilder.get();
		ClienteTemporal c= response.readEntity(ClienteTemporal.class);;

		return c;
	}

	public List<Cliente> listar() {

		Client client = ClientBuilder.newBuilder()
				.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_ANY)
				.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO").build();
		WebTarget target = client.target(urlString6);

		List<Cliente> c = target.request().get(new GenericType<List<Cliente>>() {
		});

		client.close();

		return c;
	}

	public Respuesta transferencia(TransferenciaTemporal t) {

		Client client = ClientBuilder.newBuilder()
				.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_ANY)
				.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO").build();
		WebTarget target = client.target(urlString5);
		Respuesta respuesta = target.request().post(Entity.json(t), Respuesta.class);

		return respuesta;
	}

	public Respuesta retiro(String cajero, String cliente, Double monto) {
		Client client = ClientBuilder.newBuilder()
				.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_ANY)
				.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO").build();
		WebTarget webTarget = client.target(urlString4);
		
		webTarget = webTarget.queryParam("cajero", cajero).queryParam("cliente", cliente)
				.queryParam("monto", monto);

		final Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        final Response response = invocationBuilder.method(HttpMethod.POST, null, Response.class);

		Respuesta respuesta = response.readEntity(Respuesta.class);

		return respuesta;
	}

	public Respuesta desposito(String cajero, String cliente, Double monto, String depositante) {

		Client client = ClientBuilder.newBuilder()
				.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_ANY)
				.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO").build();
		WebTarget webTarget = client.target(urlString2);
		webTarget = webTarget.queryParam("cajero", cajero).queryParam("cliente", cliente).queryParam("monto", monto)
				.queryParam("depositante", depositante);

		final Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        final Response response = invocationBuilder.method(HttpMethod.POST,null, Response.class);

		Respuesta respuesta = response.readEntity(Respuesta.class);

		return respuesta;
	}

	public Respuesta login(String correo, String clave) {
		Client client = ClientBuilder.newBuilder()
				.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_ANY)
				.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO").build();
		WebTarget webTarget = client.target(urlString3);

		webTarget = webTarget.queryParam("correo", correo).queryParam("clave", clave);
		;

		final Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		final Response response = invocationBuilder.get();
		Respuesta respuesta = response.readEntity(Respuesta.class);
		return respuesta;
	}

}