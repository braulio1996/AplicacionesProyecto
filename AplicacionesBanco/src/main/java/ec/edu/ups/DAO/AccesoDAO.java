package ec.edu.ups.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.Modelo.Acceso;
import ec.edu.ups.Modelo.Cliente;

/**
 *
 * @author Braulio
 *
 */

@Stateless
public class AccesoDAO {
	@PersistenceContext(name = "AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	
	/**
	 * Almacena  el objeto de Accesos  en la persistencia
	 * @param acceso define el objeto acceso que se va guardar
	 * @return 
	 * @throws Exception
	 */
	public boolean guardar(Acceso acceso) throws Exception {

		try {
			em.persist(acceso);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
		return true;
	}

	


	/**
	 * Lista los accesos 
	 * La clase que desea consultar
	 * @return Accesoz retorna un objeto de Tipo Acceso (Lista resultante)
	 * @throws Exception
	 */
	public List<Acceso> listar() throws Exception {

		try {
			String jpql = "SELECT l FROM Acceso l";
			Query query = em.createQuery(jpql, Acceso.class);
			List<Acceso> accesoz = query.getResultList();
			return accesoz;
		} catch (Exception e) {
			throw new Exception(e.toString());
		}

	}

	/**
	 * Actualiza la informacion del objeto Acceso en la persistencia
	 * @param Acceso objeto que tiene la informacion que se desea actualizar
	 * @throws Exception
	 */
	public void editar(Acceso acceso) throws Exception {

		try {
			em.merge(acceso);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
	}

	/**
	 * La Consulta Busca al Acceso mediante el ID cliente
	 * @param cliente identificacion del Acceso que se desea consultar
	 * @param estado de los usuario por la que se va consultar
	 * @return c retorna Acceso si esta consta en el registro 
	 * @throws Exception
	 */
	public List<Acceso> listaAccesos(int cliente, String estado) throws Exception {
		try {
			
			if(estado.equals("Todos")) {
				String jpql = "SELECT a FROM Acceso a WHERE a.cliente = "+cliente + " ORDER BY a.codigo desc";
				Query query = em.createQuery(jpql, Acceso.class);

				return query.getResultList();
			}else {
				String jpql = "SELECT a FROM Acceso a WHERE a.cliente = "+ cliente + " AND a.estado LIKE '"+estado+"' ORDER BY a.codigo desc";
				Query query = em.createQuery(jpql, Acceso.class);
				return query.getResultList();
			}
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}
	
	/**
	 * Permite leer los datos a buscar mediante el cliente en la persistencia
	 * @param cliente  objeto que se va buscar
	 * @return cedula retorna si esta exite en el registro 
	 * Si no existe ninguna entidad con el identificador, se devuelve null
	 */
	public Acceso read(int cliente) {
		return em.find(Acceso.class, cliente);
	}

	

	/**Cosulta que enumera los acceso de usuarios ya sea fallidos o exitoso
	 * @return c valor devuelto es c
	 */
	public long contar() {
		String jpql = "SELECT COUNT(a) FROM Acceso a";
		Query query = em.createQuery(jpql, Long.class);		
		
		long c = (Long) query.getSingleResult();
		return c;

	}

}
