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
 * @author Braulio
 *
 */
/**
 * @author Marcela
 *
 */
@Stateless
public class AccesoDAO {
	@PersistenceContext(name = "AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	/**
	 * guarda los datos de Acceso 
	 * @param Acceso define el objeto Acceso
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
	 * Lista  accesos que tiene el rol de Acceso
	 * @return Accesoz retorna un objeto de Tipo Acceso
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
	 * Edita al Acceso mediante los roles
	 * @param Acceso objeto de tipo Acceso
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
	 * Busca al Acceso mediante el cliente
	 * @param cliente identificacion del Acceso
	 * @return c retorna Acceso si esta consta en el registro
	 * @throws Exception
	 */
	public List<Acceso> listaAccesos(int cliente, String estado) throws Exception {
		try {
			System.out.println("Cliente: "+cliente+ " estado: "+estado);
			
			if(estado.equals("Todos")) {
				System.out.println("Entro aqui");
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
	 * Permite leer los datos a buscar mediante la cliente
	 * @param cliente
	 * @return cedula retorna si esta exite en el registro
	 */
	public Acceso read(int cliente) {
		return em.find(Acceso.class, cliente);
	}

	
	
	/**Cuenta numero de acceso de usuarios ya sea fallidos o exitoso
	 * @return c
	 */
	public long contar() {
		String jpql = "SELECT COUNT(a) FROM Acceso a";
		Query query = em.createQuery(jpql, Long.class);		
		
		long c = (Long) query.getSingleResult();
		return c;

	}

}
