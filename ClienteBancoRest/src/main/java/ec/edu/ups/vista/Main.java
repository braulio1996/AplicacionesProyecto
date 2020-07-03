package ec.edu.ups.vista;

import java.util.List;

import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.ClienteTemporal;
import ec.edu.ups.Modelo.Respuesta;
import ec.edu.ups.Modelo.TransferenciaTemporal;



public class Main {

	public static void main(String args[]) {
		
		Rest r= new Rest();
		

		ClienteTemporal ct = r.buscarCliente("");
		System.out.println(ct.toString());
		
		//Cosumiendo WS-REST de tipo GET que retorna una colección de objeto 
		List<Cliente> clientes = r.listar();
		System.out.println(clientes.toString());
		
		//Consumiento un WS-REST de tipo POST enviando una entidad como parametro
		Respuesta respuesta = r.retiro("cajero", "cliente", 10.0);
		System.out.println(respuesta);
		
		Respuesta respuesta2 = r.desposito("cajero", "cliente", 10.0,"Depositante");
		System.out.println(respuesta2);
		
		
		//TRansferencia
		
		 TransferenciaTemporal t =new TransferenciaTemporal();
	        t.setTipo("Externa");
			t.setInstitucion("JEP");
			t.setTipoCuenta("Ahorros");
			t.setNumeroCuenta("9435322713");
			t.setMonto(10.0);
			t.setIdentificacion("0106220478");
			t.setNombre("Angel");
			t.setCorreo("angel@gmail.com");
			t.setConcepto("Pago de Matricula");
			
			Respuesta respuesta3 = r.transferencia(t);
			System.out.println(respuesta3);
	}
}