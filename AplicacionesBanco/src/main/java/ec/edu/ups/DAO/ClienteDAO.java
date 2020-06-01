package ec.edu.ups.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Cuenta;

@Stateless
public class ClienteDAO {
	@PersistenceContext(name = "AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	public boolean guardar(Cliente cliente) throws Exception {

		try {
			em.persist(cliente);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
		return true;
	}

	public Cliente login(String correo, String clave) throws Exception {
		try {
			String jpql = "SELECT a FROM Cliente a WHERE a.correo = :correo and a.clave=:clave";
			Query query = em.createQuery(jpql, Cliente.class);
			query.setParameter("correo", correo);
			query.setParameter("clave", clave);
			Cliente clie = (Cliente) query.getSingleResult();
			return clie;

		} catch (Exception e) {
			// throw new Exception(e.toString());
		}
		return null;

	}

	public List<Cliente> listar() throws Exception {

		try {
			String jpql = "SELECT l FROM Cliente l";
			Query query = em.createQuery(jpql, Cliente.class);
			List<Cliente> clientez = query.getResultList();
			return clientez;
		} catch (Exception e) {
			throw new Exception(e.toString());
		}

	}

	public void editar(Cliente cliente) throws Exception {

		try {
			em.merge(cliente);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
	}

	public void editarCuenta(Cuenta cuenta) throws Exception {

		try {
			em.merge(cuenta);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
	}

	public Cliente buscar(String cedula) throws Exception {
		try {
			String jpql = "SELECT a FROM Cliente a WHERE a.cedula = :cedula";
			Query query = em.createQuery(jpql, Cliente.class);
			query.setParameter("cedula", cedula);
			Cliente clie = (Cliente) query.getSingleResult();
			return clie;

		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}

	public Cliente read(int id) {
		return em.find(Cliente.class, id);
	}

	public void eliminar(String cedula) throws Exception {
		Cliente c = buscar(cedula);
		em.remove(c);
	}

	public Cuenta buscarCuenta(String numero) throws Exception {
		try {
			String jpql = "SELECT a FROM Cuenta a WHERE a.numero = :numero";
			Query query = em.createQuery(jpql, Cuenta.class);
			query.setParameter("numero", numero);
			Cuenta c = (Cuenta) query.getSingleResult();
			return c;

		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}
	
	public boolean buscarCorreo(String correo) {
		String jpql = "SELECT c FROM Cliente c WHERE c.correo = :correo";
		Query query = em.createQuery(jpql, Cuenta.class);
		query.setParameter("correo", correo);
		Cliente c = (Cliente) query.getSingleResult();
		
		if (c !=null) {
			return true;
		}else {
			return false;
		}
		
	}
}
