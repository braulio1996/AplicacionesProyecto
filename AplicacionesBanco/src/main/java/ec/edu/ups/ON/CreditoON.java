package ec.edu.ups.ON;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.DAO.JefeCreditoDAO;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.JefeCredito;


/**
 * Esta Clase define los metodos de Objetos de Negocio
 * @version: 31/05/2020
 * @author Braulio Castro
 *
 */
@Stateless
public class CreditoON {

	@Inject
	private JefeCreditoDAO pdao;

	/**
	 * Metodo guarda los creditos  haciendo uso de los DAO JefeCredito
	 * @param credito objeto de tipo credito que se va guardar
	 * @return credito  valor devuelto 
	 * @throws Exception control de Excepciones
	 */
	public boolean guardar(JefeCredito credito) throws Exception {
		return pdao.guardar(credito);
	}

	/**
	 * Credenciales de inicio de secion de jefe de credito
	 * @param correo identificacion de las credenciales de JefeCredito
	 * @param clave identificacion de credenciales de JefeCredito
	 * @return correo   valor devuelto si este existe 
	 * @return clave valor devuelto si este es correcto
	 * @throws Exception  control de Excepciones
	 */
	public JefeCredito loginC(String correo, String clave) throws Exception {
		return pdao.login(correo, clave);
	}
    
	
	
	/**
	 * Lista Jefes de Creditos que tiene los permisos para aprobar creditos
	 * @return listar lista resultante de Jefe Credito
	 * @throws Exception control de Excepciones
	 */
	public List<JefeCredito> listar() throws Exception {
		return pdao.listar();

	}

	/**
	 * Actualiza  los creditos  del Objeto Credito
	 * @param credito objeto que se va actualizar
	 * @throws Exception control de Excepciones
	 */
	public void editar(JefeCredito credito) throws Exception {
		pdao.editar(credito);
	}

	/**
	 * Busca los creditos de usuarios por la identificacion de la cedula
	 * @param cedula parametro de la entidad de Persona que se va buscar
	 * @return cedula retorna el valor si este existe 
	 * @throws Exception control de Excepciones
	 */
	public JefeCredito buscar(String cedula) throws Exception {
		return pdao.buscar(cedula);
	}

	/**
	 * Metodo permite leer para encotrar la cedula del usuario
	 * @param cedula identificador unico del usuario
	 * @return cedula restorna el valor si existe
	 */
	public JefeCredito read(String cedula) {
		return pdao.read(cedula);
	}

	/**
	 * Elimina el credito mediante la cedula del usuario 
	 * @param cedula identificador unico del cliente  que se va eliminar
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
