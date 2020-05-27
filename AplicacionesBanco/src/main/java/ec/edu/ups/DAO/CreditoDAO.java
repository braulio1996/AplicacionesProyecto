package ec.edu.ups.DAO;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Credito;
import ec.edu.ups.Modelo.Credito;






@Stateless
public class CreditoDAO {
	@PersistenceContext(name="AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	public boolean guardar(Credito credito) throws Exception {
		
        try {  
        	em.persist(credito);
        	
        } catch (Exception e) {
        	throw new Exception(e.toString());
        	
        } 	
        return true;
	}
	
	public Credito login(String correo,String clave) throws Exception {
		try {
		String jpql = "SELECT a FROM Credito a WHERE a.correo = :correo and a.clave=:clave";
		Query query = em.createQuery(jpql, Credito.class);
		query.setParameter("correo", correo);
		query.setParameter("clave", clave);
		Credito cre = (Credito) query.getSingleResult();
		return cre;
    
}catch(Exception e) {
	throw new Exception(e.toString());
}

	}

public List<Credito>listar() throws Exception {
		
		try {
			String jpql = "SELECT l FROM Credito l";
			Query query = em.createQuery(jpql, Credito.class);
			List<Credito> creditoz = query.getResultList();   
		    return creditoz;
		}catch(Exception e) {
			throw new Exception(e.toString());
		}
		
	}	
public void editar(Credito credito) throws Exception {
	
    try {  
    	em.merge(credito);
    	
    } catch (Exception e) {
    	throw new Exception(e.toString());
    	
    } 	
}
public Credito buscar(String cedula) throws Exception {
	try {
		String jpql = "SELECT a FROM Credito a WHERE a.cedula = :cedula";
		Query query = em.createQuery(jpql, Credito.class);
		query.setParameter("cedula", cedula);
		Credito c = (Credito) query.getSingleResult();
		return c;

}catch(Exception e) {
throw new Exception(e.toString());
}
}
public Credito read(String cedula) {
	return em.find(Credito.class, cedula);
}

public void eliminar(String cedula) throws Exception {
	Credito c = buscar(cedula);
	em.remove(c);
}
}