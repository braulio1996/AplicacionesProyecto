package ec.edu.ups.ON;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.DAO.AccesoDAO;
import ec.edu.ups.Modelo.Acceso;


/**
 * Esta clase Define los Objetos de Negocio de Acceso               
 * @version: 30/05/2020
 * @author: Braulio Castro
 * 
 */


@Stateless
public class AccesoON {

	@Inject
	private AccesoDAO pdao;

	/**
	 * Guarda los accesos  de usuarios
	 * @param acceso objeto de la clase Acceso
	 * @return acceso
	 * @throws Exception
	 */
	public boolean guardar(Acceso acceso) throws Exception {
		return pdao.guardar(acceso);

	}



	/**
	 * Lista los accesos de la clase AccesosON
	 * @return listar retorna listar  de AccesoON
	 * @throws Exception
	 */
	public List<Acceso> listar() throws Exception {
		return pdao.listar();

	}

	/**Edita las acciones de la clase AccesoON
	 * @param Acceso  objeto de tipo acceso
	 * @throws Exception
	 */
	public void editar(Acceso Acceso) throws Exception {
		pdao.editar(Acceso);
	}

	/**
	 * Lista lo accesoso con el cliente y estado 
	 * @param cliente Objeto de tipo Cliente
	 * @param estado estado de la cuenta de usuario
	 * @return cliente, estado 
	 * @throws Exception
	 */
	public Acceso listarAccesos(int cliente, String estado) throws Exception {
		return pdao.listaAccesos(cliente, estado);
	}

	/**
	 * Metodo que permite leer los accesos de usaurios
	 * @param cliente
	 * @return cliente retorna cliente de tipo AdministradorDAO
	 */
	public Acceso read(int cliente) {
		return pdao.read(cliente);
	}


	
	/**
	 * @return
	 */
	public long contar() {
		return pdao.contar();
	}
}
