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
 *Esta clase Define los metodos de Objetos de acceso a Datos
 * @version: 31/05/2020
 * @author: Braulio Castro
 *
 */
@Stateless
public class CajeroDAO {
	@PersistenceContext(name = "AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	/**
	 * Guarda los datos de cajero de tipo persona en la persistencia
	 * @param cajero define el objeto Cajero que se va guardar
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
	 * Hace la consulta de los Datos o credenciales del Cajero de Tipo persona para poder verificar si esta es
	 * o no el cajero que quiere ingresar
	 * @param correo identificacion  de la cuenta del cajero e identifica las credenciales
	 * @param clave  define el acceso al Cajero
	 * @return cre retorna el objeto Cajero que cumpla con las credenciales de identificacion
	 * @throws Exception control de Excepciones
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
	 * Lista  a personas del  rol de Cajero
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
	 * Actualiza la informacion del objeto cajero en la persistencia
	 * @param cajero objeto de tipo Cajero que se debe actualizar
	 * @throws Exception control de Excepciones
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
	 * @throws Exception control de Excepciones
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
	 * @param cedula identificador de Objeto Cajero tipo Persona
	 * @return cedula retorna si esta exite en el registro
	 */
	public Cajero read(String cedula) {
		return em.find(Cajero.class, cedula);
	}

	/**
	 * Elimina el rol de Cajero mediante la cedula de identifacacion
	 * @param cedula identificador para eliminar al cajero
	 * @throws Exception
	 */
	public void eliminar(String cedula) throws Exception {
		Cajero c = buscar(cedula);
		em.remove(c);
	}
	
	/**
	 *Consulta de numero de veces que un cliente entre session 
	 * sea esta fallidas o exitosas
	 * @return c
	 */
	public long contar() {
		String jpql = "SELECT COUNT(a) FROM Cajero a";
		Query query = em.createQuery(jpql, Long.class);		
		
		long c = (Long) query.getSingleResult();
		return c;

	}

}
