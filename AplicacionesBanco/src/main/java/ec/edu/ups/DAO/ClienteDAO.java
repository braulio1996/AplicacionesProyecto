package ec.edu.ups.DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.Modelo.Acceso;
import ec.edu.ups.Modelo.Amortizacion;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.CreditoAprobado;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.SolicitudCredito;
import ec.edu.ups.Modelo.Transaccion;

@Stateless
public class ClienteDAO {
	@PersistenceContext(name = "AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	/**
	 * Guarda los datos del cliente tipo persona en la persistencia
	 * 
	 * @param cliente define el objeto cliente que se va guardar
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
	 * Consulta las credenciales para inicio de sesion del Cliente tipo Persona
	 * 
	 * @param correo identifica las credenciales del cliente para el acceso
	 * @param clave  identifica las credencailes del cliente para el acceso
	 * @return clie retorna el objeto Cliente que cumpla con las credenciales de
	 *         identificacion
	 * @throws Exception Control de Excepciones
	 */
	public Cliente login(String correo, String clave) throws Exception {
		try {
			String jpql = "SELECT a FROM Cliente a WHERE a.correo = :correo and a.clave=:clave";
			Query query = em.createQuery(jpql, Cliente.class);
			query.setParameter("correo", correo);
			query.setParameter("clave", clave);
			Cliente clie = (Cliente) query.getSingleResult();
			for (int i = 0; i < clie.getTransacciones().size(); i++) {
				clie.getTransacciones().get(i).getCodigo();
			}
			System.out.println("+++++++++++++++++++  " + clie.toString());
			return clie;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Lista las personas Clientes
	 * 
	 * @return clientes retorna el objeto de tipo Cliente, devuelve una lista
	 *         clientez
	 * @throws Exception control de Excepciones
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> listar() throws SQLException {
		try {
			String jpql = "SELECT l FROM Cliente l";
			// JOIN FETCH l.accesos
			Query query = em.createQuery(jpql, Cliente.class);
			Cliente cliente = (Cliente) query.getSingleResult();
			List<Cliente> clientes = query.getResultList();
			List<Acceso> accesos = new ArrayList<>();
			List<Transaccion>tt= new ArrayList<>();
			for (Acceso acceso : cliente.getAccesos()) {
				accesos.add(acceso);
			}
			for (Transaccion t : cliente.getTransacciones()) {
				tt.add(t);
			}
			
			
			return clientes;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;

	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listar2(int codigo) throws SQLException {
		try {
			String jpql = "SELECT l FROM Cliente l a WHERE l.codigo = :codigo";
			Query query = em.createQuery(jpql, Cliente.class);
			query.setParameter("codigo", codigo);
			Cliente cliente = (Cliente) query.getSingleResult();
			List<Cliente> clientes = query.getResultList();
			List<Acceso> accesos = new ArrayList<>();
			List<Transaccion>tt= new ArrayList<>();
			List<SolicitudCredito>ss= new ArrayList<>();
			for (Acceso acceso : cliente.getAccesos()) {
				accesos.add(acceso);
			}
			for (Transaccion t : cliente.getTransacciones()) {
				tt.add(t);
			}
			for (SolicitudCredito s: cliente.getSolicitudesCredito()) {
				ss.add(s);
			}
			
			
			return clientes;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;

	}

	/**
	 * Actualiza los datos de Cliente tipo persona en la persistecia
	 * 
	 * @param cliente define el objeto cliente que se va actualizar
	 * @throws Exception control de Excepciones
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
	 * 
	 * @param cuenta objeto de tipo cuenta
	 * @throws Exception control de Excepciones
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
	 * 
	 * @param cedula identificacion del usuario
	 * @return clie retorna el objeto cliente que se esta buscando
	 * @throws Exception control de Excepciones
	 */
	public Cliente buscar(String cedula) throws Exception {
		try {
			String jpql = "SELECT a FROM Cliente a WHERE a.cedula = :cedula";
			Query query = em.createQuery(jpql, Cliente.class);
			query.setParameter("cedula", cedula);
			Cliente clie = (Cliente) query.getSingleResult();
			return clie;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Permite leer los datos del cliente mediante el id
	 * 
	 * @param id clave unica del cliente que se va buscar
	 * @return id el objeto solicitado si existiera
	 */
	public Cliente read(int id) {
		return em.find(Cliente.class, id);
	}

	/**
	 * Elimina al cliente mediante la cedual de identificacion
	 * 
	 * @param cedula identificador del objeto que se desea eliminar
	 * @throws Exception control de Excepciones
	 */
	public void eliminar(String cedula) throws Exception {
		Cliente c = buscar(cedula);
		em.remove(c);
	}

	/**
	 * Consulta para buscar Cuenta del Cliente
	 * 
	 * @param numero identifiacdor para buscar la respectiva cuenta
	 * @return c retorno el objeto Cuenta que se busca
	 * @throws Exception control de Excepciones
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
	 * Consular para buscar al Cliente mediante el correo
	 * 
	 * @param correo identificador del cliente con el uso de las credenciales
	 * @return c retorna un objeto de tipo cliente
	 */
	public Cliente buscarCorreo(String correo) {
		
		System.out.println("Entro a buscar correo");
		String jpql = "SELECT c FROM Cliente c WHERE c.correo = '"+correo+"'";
		Query query = em.createQuery(jpql, Cliente.class);
//		query.setParameter("correo", correo);

		try {
			Cliente c = (Cliente) query.getSingleResult();

			System.out.println("============");
			System.out.println(c);
			System.out.println("============");
			
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
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

	public List<Transaccion> transCliente(int c, LocalDate fechaDesde, LocalDate fechaHasta, String tipo)
			throws Exception {

		if (tipo.equals("Todos")) {
			String jpql = "SELECT t " + "FROM Transaccion t " + "WHERE t.cliente = " + c + "AND "
					+ "t.fecha BETWEEN Date('" + fechaDesde + "') AND Date('" + fechaHasta + "')";
			Query query = em.createQuery(jpql, Transaccion.class);

			return query.getResultList();
		} else {
			String jpql = "SELECT t " + "FROM Transaccion t " + "WHERE t.cliente = " + c + "AND " + "t.fecha BETWEEN '"
					+ fechaDesde + "' AND '" + fechaHasta + "' AND " + "t.tipo LIKE '" + tipo + "'";

			Query query = em.createQuery(jpql, Transaccion.class);

			return query.getResultList();
		}
	}
	public List<SolicitudCredito>listSolicitud(int cliente, String estado){
		System.out.println("Buscar estado: " + estado);
		
		String jpql = "";
		
		if(estado.equals("") || estado.equals("Todos")) {
			jpql="SELECT s FROM SolicitudCredito s WHERE s.cliente = " + cliente;			
		}else {
			jpql="SELECT s FROM SolicitudCredito s WHERE s.cliente = " + cliente + " AND s.estado = '"+estado+"'";		
		}
		
		Query query = em.createQuery(jpql, SolicitudCredito.class);

		return query.getResultList();
		
	}
	
	public List<SolicitudCredito>allSolicitud(){
		//String jpql="SELECT s FROM SolicitudCredito WHERE s.cliente = :cliente";
		String jpql="SELECT s FROM SolicitudCredito s";
		Query query = em.createQuery(jpql, SolicitudCredito.class);
		//query.setParameter("cliente", cliente);
		
		return query.getResultList();
		
	}
	

	
	public List<SolicitudCredito>listSolicitudTipo(String tipo){
		if (tipo.equals("Todos")) {
		String jpql="SELECT s FROM SolicitudCredito s";
		Query query = em.createQuery(jpql, SolicitudCredito.class);
		return query.getResultList();
		}else {
			String jpql="SELECT s FROM SolicitudCredito s"+"WHERE s.estado LIKE "+tipo;
			Query query = em.createQuery(jpql, SolicitudCredito.class);
			return query.getResultList();
			
		}
	}
	
	public List<CreditoAprobado> listCA(int cliente){
		//String jpql="SELECT s FROM SolicitudCredito WHERE s.cliente = :cliente";
		String jpql="SELECT s FROM CreditoAprobado s WHERE s.cliente = " + cliente;
		Query query = em.createQuery(jpql, CreditoAprobado.class);
		//query.setParameter("cliente", cliente);
		
		return query.getResultList();
		
	}
	
	public CreditoAprobado buscarCA(int cliente, int codigo){
		//String jpql="SELECT s FROM SolicitudCredito WHERE s.cliente = :cliente";
		String jpql="SELECT s FROM CreditoAprobado s WHERE s.cliente = " + cliente + " AND s.numero = " + codigo;
		Query query = em.createQuery(jpql, CreditoAprobado.class);
		//query.setParameter("cliente", cliente);
		
		return (CreditoAprobado) query.getSingleResult();
	}
	
	public double getSaldo() {
		String jpql="SELECT s FROM Amortizacion s";
		Query query = em.createQuery(jpql, Amortizacion.class);
		
		List<Amortizacion> a = query.getResultList();
		double sumSaldo = 0; 
		for (int i=0; i< a.size(); i++) {
			sumSaldo = sumSaldo + a.get(i).getSaldo();
		}
		return sumSaldo;
	}
	
	
	public List<CreditoAprobado> listaCreditosAprobado(int cliente){
		System.out.println("AAAAAAAAAAAAAAA " + cliente);
		String jpql="SELECT s FROM CreditoAprobado s WHERE s.cliente = " + cliente;	
		Query query = em.createQuery(jpql, CreditoAprobado.class);
		
		return query.getResultList();
	}
	
	public List<Amortizacion> listaAmort(int cliente, int numero){
		String jpql="SELECT s FROM CreditoAprobado s WHERE s.cliente = " + cliente + " AND s.numero = " + numero;	
		Query query = em.createQuery(jpql, CreditoAprobado.class);
		CreditoAprobado creditoAprobado = (CreditoAprobado) query.getSingleResult();
		
		List<Amortizacion> ss= new ArrayList<>();
		for (Amortizacion s: creditoAprobado.getAmortizacion()){
			ss.add(s);
		}
		return ss;
		
	}
}
