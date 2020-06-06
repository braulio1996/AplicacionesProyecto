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
	 * @param cajero
	 * @return
	 * @throws Exception
	 */
	public boolean guardar(Cajero cajero) throws Exception {
		return pdao.guardar(cajero);

	}

	/**
	 * Autentificacion de Cajero mediante las credenciales corro y clave
	 * @param correo identificador del cajero
	 * @param clave identificadro de clave de cajero
	 * @return correo
	 * @return clave
	 * @throws Exception
	 */
	public Cajero loginC(String correo, String clave) throws Exception {

		return pdao.login(correo, clave);
	}

	/**Lista los cajeros
	 * @return
	 * @throws Exception
	 */
	public List<Cajero> listar() throws Exception {
		return pdao.listar();

	}


	/**
	 * Modificar cajero 
	 * @param cajero
	 * @throws Exception
	 */
	public void editar(Cajero cajero) throws Exception {
		pdao.editar(cajero);
	}

	/**
	 * Busca los usuarios mediante la cedula de identificacion
	 * @param cedula
	 * @return
	 * @throws Exception
	 */
	public Cajero buscar(String cedula) throws Exception {
		return pdao.buscar(cedula);
	}

	/**
	 * Permite leer los datos al buscar al usuario mendiante la ceduula
	 * @param cedula
	 * @return cedula del DAO Administrador
	 */
	public Cajero read(String cedula) {
		return pdao.read(cedula);
	}

	/**
	 * Elimina al usaurio mediante la cedula
	 * @param cedula
	 * @throws Exception
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
