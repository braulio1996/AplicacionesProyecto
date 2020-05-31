package ec.edu.ups.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.Modelo.Administrador;
import ec.edu.ups.Modelo.Administrador;

/**
 *Esta clase Define los metodos de Objetos de acceso a Datos
 * @version: 31/05/2020
 * @author: Braulio Castro
 *
 */
@Stateless
public class AdministradorDAO {
	@PersistenceContext(name = "AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	/**
	 * Este Metodo registra un Administrador
	 * @param administrador
	 * @throws Exception
	 */
	public void registrar(Administrador administrador) throws Exception {
		try {
			administrador.setTipo("Administrador");
			em.persist(administrador);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
	}

	/**
	 * Este metodo valida las credenciales de usuario
	 * @param correo define el email de un administrador
	 * @param clave define la clave de acceso
	 * @return Administrador, retorna un Objeto de tipo Administrador que cumpla con las credenciales
	 * @throws Exception manejo de erroes, no existe, o vacio 
	 */
	public Administrador login(String correo, String clave) throws Exception {
		try {
			String jpql = "SELECT a FROM Administrador a WHERE a.correo = :correo and a.clave=:clave";
			Query query = em.createQuery(jpql, Administrador.class);
			query.setParameter("correo", correo);
			query.setParameter("clave", clave);
			Administrador admin = (Administrador) query.getSingleResult();
			return admin;

		} catch (Exception e) {
			// throw new Exception(e.toString());
		}
		return null;

	}

	/**
	 * Este metodo define la busqueda de un Objeto Persona de Tipo administrador mediante su id o codigo 
	 * @param id define primary key
	 * @return Administrador retorna un objeto persona de tipo administrador
	 */
	public Administrador read(int id) {
		return em.find(Administrador.class, id);
	}

	/**
	 * Metodo que actuliza un objeto persona de tipo Administrador 
	 * @param administrador, objeto que se pretende actualizar
	 */
	public void update(Administrador administrador) {

		em.merge(administrador);
	}

}
