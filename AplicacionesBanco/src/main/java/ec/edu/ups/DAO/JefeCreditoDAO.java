package ec.edu.ups.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.JefeCredito;
import ec.edu.ups.Modelo.JefeCredito;

@Stateless
public class JefeCreditoDAO {
	@PersistenceContext(name = "AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	/**
	 * @param credito define el credito del usuario
	 * @return el objeto credito
	 * @throws Exception
	 */
	public boolean guardar(JefeCredito credito) throws Exception {
		try {
			em.persist(credito);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
		return true;
	}

	/**
	 * Este metodo valida las credenciales de usuario
	 * 
	 * @param correo define el email del cajero
	 * @param clave  define la contrase o clave de acceso
	 * @return credito, retorna un Objeto de tipo Credito que con la identificacion
	 *         de usuario
	 * @throws Exception
	 */
	public JefeCredito login(String correo, String clave) throws Exception {
		try {
			String jpql = "SELECT a FROM JefeCredito a WHERE a.correo = :correo and a.clave=:clave";
			Query query = em.createQuery(jpql, JefeCredito.class);
			query.setParameter("correo", correo);
			query.setParameter("clave", clave);
			JefeCredito cre = (JefeCredito) query.getSingleResult();
			return cre;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * *Listas los creditos de los usuarios
	 * 
	 * @return retorna un objeto de tipo credito
	 * @throws Exception
	 */
	public List<JefeCredito> listar() throws Exception {

		try {
			String jpql = "SELECT l FROM JefeCredito l";
			Query query = em.createQuery(jpql, JefeCredito.class);
			List<JefeCredito> creditoz = query.getResultList();
			return creditoz;
		} catch (Exception e) {
			throw new Exception(e.toString());
		}

	}

	/**Editar creditos  usario
	 * @param credito 
	 * @throws Exception
	 */
	public void editar(JefeCredito credito) throws Exception {

		try {
			em.merge(credito);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
	}

	/**
	 * Este metodo busca mediante la cedula al usuario
	 * @param cedula identificaicion del usuario
	 * @return credito retorna el objeto de tipo Credito 
	 * @throws Exception
	 */
	public JefeCredito buscar(String cedula) throws Exception {
		try {
			String jpql = "SELECT a FROM Credito a WHERE a.cedula = :cedula";
			Query query = em.createQuery(jpql, JefeCredito.class);
			query.setParameter("cedula", cedula);
			JefeCredito c = (JefeCredito) query.getSingleResult();
			return c;

		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}

	/**
	 * Este metodo define la busqueda de un Objeto Persona tipo cliente mediante la cedula 
	 * @param cedula es un id principal para la busqueda del usuario
	 * @return cedula si esta existe 
	 */
	public JefeCredito read(String cedula) {
		return em.find(JefeCredito.class, cedula);
	}

	/**
	 * Objeto credito que se requiere eliminar
	 * @param cedula
	 * @throws Exception
	 */
	public void eliminar(String cedula) throws Exception {
		JefeCredito c = buscar(cedula);
		em.remove(c);
	}
	
	public long contar() {
		String jpql = "SELECT COUNT(jc) FROM JefeCredito jc";
		Query query = em.createQuery(jpql, Long.class);		
		
		long c = (Long) query.getSingleResult();
		return c;

	}
}