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
public class CuentaDAO {
	@PersistenceContext(name = "AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	public boolean guardar(Cuenta cuenta) throws Exception {

		try {
			em.persist(cuenta);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
		return true;
	}


	public List<Cuenta> listar() throws Exception {

		try {
			String jpql = "SELECT l FROM Cuenta l";
			Query query = em.createQuery(jpql, Cuenta.class);
			List<Cuenta> cuentaz = query.getResultList();
			return cuentaz;
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
	
	
}
