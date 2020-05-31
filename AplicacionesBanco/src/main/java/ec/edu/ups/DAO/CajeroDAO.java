package ec.edu.ups.DAO;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;


@Stateless
public class CajeroDAO {
	@PersistenceContext(name="AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	public boolean guardar(Cajero cajero) throws Exception {
		
        try {  
        	em.persist(cajero);
        	
        } catch (Exception e) {
        	throw new Exception(e.toString());
        	
        	
        }
		return true; 	
	}
	
	public Cajero login(String correo,String clave) throws Exception {
		try {
		String jpql = "SELECT a FROM Cajero a WHERE a.correo = :correo and a.clave=:clave";
		Query query = em.createQuery(jpql, Cajero.class);
		query.setParameter("correo", correo);
		query.setParameter("clave", clave);
		Cajero cre = (Cajero) query.getSingleResult();
		return cre;
    
}catch(Exception e) {
	//throw new Exception(e.toString());
}
		return null;

	}
public List<Cajero>listar() throws Exception {
		
		try {
			String jpql = "SELECT l FROM Cajero l";
			Query query = em.createQuery(jpql, Cajero.class);
			List<Cajero> cajeroz = query.getResultList();   
		    return cajeroz;
		}catch(Exception e) {
			throw new Exception(e.toString());
		}
		
	}
public void editar(Cajero cajero) throws Exception {
	
    try {  
    	em.merge(cajero);
    	
    } catch (Exception e) {
    	throw new Exception(e.toString());
    	
    } 	
}
public Cajero buscar(String cedula) throws Exception {
	try {
		String jpql = "SELECT a FROM Cajero a WHERE a.cedula = :cedula";
		Query query = em.createQuery(jpql, Cajero.class);
		query.setParameter("cedula", cedula);
		Cajero c = (Cajero) query.getSingleResult();
		return c;

}catch(Exception e) {
throw new Exception(e.toString());
}

}
public Cajero read(String cedula) {
	return em.find(Cajero.class, cedula);
}

public void eliminar(String cedula) throws Exception {
	Cajero c = buscar(cedula);
	em.remove(c);
}

		
}
