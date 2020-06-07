package ec.edu.ups.ON;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.DAO.CajeroDAO;
import ec.edu.ups.Modelo.Cajero;

/**
 * @author Marcela
 *
 */
@Stateless
public class CajeroON {

	@Inject
	private CajeroDAO pdao;

	/**
	 * Guarda el objeto de tipo cajero
	 * @param cajero  objeto cajero que se va guardar
	 * @return cajero  si este existiera
	 * @throws Exception control de Excepciones
	 */
	public boolean guardar(Cajero cajero) throws Exception {
		return pdao.guardar(cajero);

	}
	
	/**
	 * Autentificacion de Cajero mediante las credenciales corro y clave
	 * @param correo identificador del cajero 
	 * @param clave identificadro de clave de cajero
	 * @return correo renorna los valores si estas existen
	 * @return clave renorna los valores si estas existen
	 * @throws Exception control de Excepciones
	 */
	public Cajero loginC(String correo, String clave) throws Exception {

		return pdao.login(correo, clave);
	}

	/**Lista los cajeros
	 * @return lista los datos del objeto Cajero
	 * @throws Exception control de Excepciones
	 */
	public List<Cajero> listar() throws Exception {
		return pdao.listar();

	}


	/**
	 * Modificar el objeto de tipo Cajero
	 * @param cajero el objeto que se va actualizar
	 * @throws Exception  control de Excepciones
	 */
	public void editar(Cajero cajero) throws Exception {
		pdao.editar(cajero);
	}

	/**
	 * Busca los usuarios mediante la cedula de identificacion
	 * @param cedula identificador unico del objeto de tipo Persona
	 * @return cedula retorna el valor si esta existe
	 * @throws Exception control de Excepciones
	 */
	public Cajero buscar(String cedula) throws Exception {
		return pdao.buscar(cedula);
	}

	/**
	 * Permite leer los datos al buscar al usuario mendiante la cedula
	 * @param cedula identificador del objeto Cajero de Tipo Persona
	 * @return cedula retorna el valor si esta exite 
	 */
	public Cajero read(String cedula) {
		return pdao.read(cedula);
	}

	/**
	 * Elimina al usuario mediante la cedula 
	 * @param cedula identificador que elimina el objeto Cajero
	 * @throws Exception control de Excepciones
	 */
	public void eliminar(String cedula) throws Exception {
		pdao.eliminar(cedula);

	}
	
	/**
	 * @return 
	 */
	public long contar() {
		return pdao.contar();
	}
}
