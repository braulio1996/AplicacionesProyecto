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

	
	public List<SolicitudCredito>listSolicitud(){
		//String jpql="SELECT s FROM SolicitudCredito WHERE s.cliente = :cliente";
		String jpql="SELECT s FROM SolicitudCredito s";
		Query query = em.createQuery(jpql, SolicitudCredito.class);
		//query.setParameter("cliente", cliente);
		
		return query.getResultList();
		
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
		String jpql="SELECT s FROM Amortizacion WHERE s.estado = :estado";	
		Query query = em.createQuery(jpql, SolicitudCredito.class);
		query.setParameter("estado", estado);
		return query.getResultList();
		
	}
	
	public void upAmortizacion(Amortizacion a) throws Exception {
		try {
			em.merge(a);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}
}
