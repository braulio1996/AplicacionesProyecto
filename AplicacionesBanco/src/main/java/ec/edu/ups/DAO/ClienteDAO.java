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
import ec.edu.ups.Modelo.Transaccion;

@Stateless
public class ClienteDAO {
	@PersistenceContext(name = "AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	
	
	/**
	 * Guarda los datos del cliente tipo persona
	 * @param cliente define el objeto  cliente
	 * @return
	 * @throws Exception
	 */
	public boolean guardar(Cliente cliente) throws Exception {

		try {
			em.persist(cliente);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
		return true;
	}

	/**
	 * Valida las credenciales para inicio de sesion  del Cliente tipo Persona
	 * @param correo identifica las credenciales del cliente para el acceso
	 * @param clave identifica las credencailes del cliente para el acceso
	 * @return clie retorna el objeto Cliente que cumpla con las credenciales de identificacion
	 * @throws Exception
	 */
	public Cliente login(String correo, String clave) throws Exception {
		try {
			String jpql = "SELECT a FROM Cliente a WHERE a.correo = :correo and a.clave=:clave";
			Query query = em.createQuery(jpql, Cliente.class);
			query.setParameter("correo", correo);
			query.setParameter("clave", clave);
			Cliente clie = (Cliente) query.getSingleResult();
			return clie;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Lista las personas Clientes
	 * @return clientes retorna el objeto de tipo Cliente
	 * @throws Exception
	 */
	public List<Cliente> listar() throws Exception {
		try {
			String jpql = "SELECT l FROM Cliente l WHERE l.cedula >= 0";
			Query query = em.createQuery(jpql, Cliente.class);
			List<Cliente> clientez = query.getResultList();
			return clientez;
		} catch (Exception e) {
			throw new Exception(e.toString());
		}

	}

	/**
	 * Edita las datos de Cliente tipo persona
	 * @param cliente  define el objeto cliente
	 * @throws Exception
	 */
	public void editar(Cliente cliente) throws Exception {
		try {
			em.merge(cliente);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}

	/**
	 * Metodo permite modificar las cuentas del cliente
	 * @param cuenta objeto de tipo cuenta
	 * @throws Exception
	 */
	public void editarCuenta(Cuenta cuenta) throws Exception {

		try {
			em.merge(cuenta);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
	}

	/**
	 * Permite buscar al Cliente mediante la cedula
	 * @param cedula  identificacion del usuario
	 * @return clie retorna el objeto cliente
	 * @throws Exception
	 */
	public Cliente buscar(String correo) throws Exception {
		try {
			String jpql = "SELECT a FROM Cliente a WHERE a.correo = :correo";
			Query query = em.createQuery(jpql, Cliente.class);
			query.setParameter("correo", correo);
			Cliente clie = (Cliente) query.getSingleResult();
			return clie;

		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}

	/**
	 * Permite leer los datos del cliente mediante el id
	 * @param id clave unica del cliente
	 * @return id 
	 */
	public Cliente read(int id) {
		return em.find(Cliente.class, id);
	}

	/**
	 * Elimina  al cliente mediante la cedual de identificacion
	 * @param cedula 
	 * @throws Exception
	 */
	public void eliminar(String cedula) throws Exception {
		Cliente c = buscar(cedula);
		em.remove(c);
	}

	/**
	 * Metodo par abuscar Cuenta del Cliente 
	 * @param numero identifiacdor para buscar las respectivas cuentas
	 * @return c retorno el objeto Cuenta
	 * @throws Exception
	 */
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
	
	/**
	 * Metodo Busca al Cliente mediante el correo
	 * @param correo  identificador del cliente con el uso de las credenciales
	 * @return c retorna un objeto de tipo cliente
	 */
	public Cliente buscarCorreo(String correo) {
		String jpql = "SELECT c FROM Cliente c WHERE c.correo = :correo";
		Query query = em.createQuery(jpql, Cliente.class);
		query.setParameter("correo", correo);
		Cliente c = (Cliente) query.getSingleResult();
		
		return c;
	}
	
	public long contar() {
		String jpql = "SELECT COUNT(c) FROM Cliente c WHERE c.cedula >=0";
		Query query = em.createQuery(jpql, Long.class);		
		
		long c = (Long) query.getSingleResult();
		return c;

	}
	
	public Cuenta cuentaCliente(Cliente cliente) {
		String jpql = "SELECT  FROM Cuenta c WHERE c.cliente = :cliente";
		Query query = em.createQuery(jpql, Cliente.class);
		query.setParameter("cliente", cliente);
		Cuenta c = (Cuenta) query.getSingleResult();
		
		return c;
	}
	
	public List<Transaccion> transCliente(Cliente cliente) {
		String jpql = "SELECT t FROM transaccion t WHERE t.cliente = :cliente";
		Query query = em.createQuery(jpql, Transaccion.class);
		query.setParameter("cliente", cliente);
		return query.getResultList();
	}
	
}
