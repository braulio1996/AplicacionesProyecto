package ec.edu.ups.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;

/**
 * @author Marcela
 *
 */
@Stateless
public class CajeroDAO {
	@PersistenceContext(name = "AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	/**
	 * guarda los datos de cajero de tipo persona
	 * @param cajero define el objeto Cajero
	 * @return 
	 * @throws Exception
	 */
	public boolean guardar(Cajero cajero) throws Exception {

		try {
			em.persist(cajero);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
		return true;
	}

	/**
	 * Datos o credenciales del Cajero de Tipo persona
	 * @param correo identificacion  de la cuenta del cajero e identifica las credenciales
	 * @param clave  define el acceso al Cajero
	 * @return cre retorna el objeto Cajero que cumpla con las credenciales de identificacion
	 * @throws Exception
	 */
	public Cajero login(String correo, String clave) throws Exception {
		try {
			String jpql = "SELECT a FROM Cajero a WHERE a.correo = :correo and a.clave=:clave";
			Query query = em.createQuery(jpql, Cajero.class);
			query.setParameter("correo", correo);
			query.setParameter("clave", clave);
			Cajero cre = (Cajero) query.getSingleResult();
			return cre;

		} catch (Exception e) {
			// throw new Exception(e.toString());
		}
		return null;

	}

	/**
	 * Lista  a personas que tiene el rol de Cajero
	 * @return cajeroz retorna un objeto de Tipo Cajero
	 * @throws Exception
	 */
	public List<Cajero> listar() throws Exception {

		try {
			String jpql = "SELECT l FROM Cajero l";
			Query query = em.createQuery(jpql, Cajero.class);
			List<Cajero> cajeroz = query.getResultList();
			return cajeroz;
		} catch (Exception e) {
			throw new Exception(e.toString());
		}

	}

	/**
	 * Edita al cajero mediante los roles
	 * @param cajero objeto de tipo Cajero
	 * @throws Exception
	 */
	public void editar(Cajero cajero) throws Exception {

		try {
			em.merge(cajero);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
	}

	/**
	 * Busca al usuario Cajero mediante la cedula
	 * @param cedula identificacion del ususrio Cajero
	 * @return c retorna Cajero si esta consta en el registro
	 * @throws Exception
	 */
	public Cajero buscar(String cedula) throws Exception {
		try {
			String jpql = "SELECT a FROM Cajero a WHERE a.cedula = :cedula";
			Query query = em.createQuery(jpql, Cajero.class);
			query.setParameter("cedula", cedula);
			Cajero c = (Cajero) query.getSingleResult();
			return c;

		} catch (Exception e) {
			throw new Exception(e.toString());
		}

	}

	/**
	 * Permite leer los datos a buscar mediante la cedula
	 * @param cedula
	 * @return cedula retorna si esta exite en el registro
	 */
	public Cajero read(String cedula) {
		return em.find(Cajero.class, cedula);
	}

	/**
	 * Elimina el rol de Cajero mediante la cedula de identifacacion
	 * @param cedula
	 * @throws Exception
	 */
	public void eliminar(String cedula) throws Exception {
		Cajero c = buscar(cedula);
		em.remove(c);
	}

}
