package ec.edu.ups.DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
public class SolicitudDAO {
	@PersistenceContext(name = "AplacacionesBancoPersistenceUnit")
	private EntityManager em;


	public void editar(SolicitudCredito solicitud) throws Exception {
		try {
			em.merge(solicitud);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}
	public void add(SolicitudCredito solicitud) throws Exception {
		try {
			em.persist(solicitud);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}

	
	public List<SolicitudCredito>listSolicitud(){
		//String jpql="SELECT s FROM SolicitudCredito WHERE s.cliente = :cliente";
		String jpql="SELECT s FROM SolicitudCredito s";
		Query query = em.createQuery(jpql, SolicitudCredito.class);
		//query.setParameter("cliente", cliente);
		
		return query.getResultList();
		
	}
	
	public SolicitudCredito buscarSolicitud(int codigo){
		//String jpql="SELECT s FROM SolicitudCredito WHERE s.cliente = :cliente";
		String jpql="SELECT s FROM SolicitudCredito s WHERE s.codigo = "+ codigo;
		Query query = em.createQuery(jpql, SolicitudCredito.class);
		//query.setParameter("cliente", cliente);
		
		return (SolicitudCredito) query.getSingleResult();
		
	}
	public boolean crearAmortizacion(Amortizacion amortizacion) throws Exception {

		try {
			em.persist(amortizacion);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
		return true;
	}
	
	public boolean aprobado(CreditoAprobado credito) throws Exception {

		try {
			em.persist(credito);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
		return true;
	}
	
	public void amortizacion(CreditoAprobado credito) throws Exception {
		try {
			em.merge(credito);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}
	public List<Amortizacion>listaCreditos(String estado){
		String jpql="SELECT s FROM Amortizacion s WHERE s.estado = :estado";	
		Query query = em.createQuery(jpql, SolicitudCredito.class);
		query.setParameter("estado", estado);
		return query.getResultList();
		
	}
	public List<Amortizacion>listaCreditosT(){
		String jpql="SELECT s FROM Amortizacion s";	
		Query query = em.createQuery(jpql, Amortizacion.class);
		
		return query.getResultList();
		
	}
	public List<CreditoAprobado>listaCreditosAprobado(){
		String jpql="SELECT s FROM CreditoAprobado s";	
		Query query = em.createQuery(jpql, CreditoAprobado.class);
		//CreditoAprobado creditoAprobado = (CreditoAprobado) query.getSingleResult();
		List<Amortizacion>ss= new ArrayList<>();
//		for (Amortizacion s: creditoAprobado.getAmortizacion()){
//			ss.add(s);
//		}
		return query.getResultList();
		
	}
	
	public void upAmortizacion(Amortizacion a) throws Exception {
		try {
			em.merge(a);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}
	
	public SolicitudCredito verDetalle(int codigo) {
		String jpql="SELECT s FROM SolicitudCredito s WHERE s.codigo = :codigo";	
		Query query = em.createQuery(jpql, SolicitudCredito.class);
		query.setParameter("codigo", codigo);
		return (SolicitudCredito) query.getSingleResult();
	}
	
	public List<SolicitudCredito> solPendientes(){
		//String jpql="SELECT s FROM SolicitudCredito WHERE s.cliente = ";
		String jpql="SELECT s FROM SolicitudCredito s WHERE s.estado LIKE 'Pendiente'";
		Query query = em.createQuery(jpql, SolicitudCredito.class);
		//query.setParameter("cliente", cliente);
		
		return query.getResultList();
		
	}
	
	public List<SolicitudCredito> solRespuesta(){
		//String jpql="SELECT s FROM SolicitudCredito WHERE s.cliente = ";
		String jpql="SELECT s FROM SolicitudCredito s WHERE s.estado != 'Pendiente'";
		Query query = em.createQuery(jpql, SolicitudCredito.class);
		//query.setParameter("cliente", cliente);
		
		return query.getResultList();
		
	}
	
	public List<SolicitudCredito> buscarSol(String estado){

		String jpql = "";
		if(estado.equals("Todos") || estado == "Todos" || estado == "") {
			jpql="SELECT s FROM SolicitudCredito s WHERE s.estado != 'Pendiente'";
		}else {
			jpql="SELECT s FROM SolicitudCredito s WHERE s.estado LIKE '"+estado+"'";
		}
		
		System.out.println("SQL: " + jpql);
		Query query = em.createQuery(jpql, SolicitudCredito.class);
		
		return query.getResultList();
	}
	
	public SolicitudCredito buscarSolJC(int codigo){

		String jpql="SELECT s FROM SolicitudCredito s WHERE s.codigo LIKE '"+codigo+"'";
		
		Query query = em.createQuery(jpql, SolicitudCredito.class);
		
		return (SolicitudCredito) query.getSingleResult();
	}
}
