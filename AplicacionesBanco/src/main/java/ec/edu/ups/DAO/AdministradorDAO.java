package ec.edu.ups.DAO;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.Modelo.Administrador;
import ec.edu.ups.Modelo.Administrador;





@Stateless
public class AdministradorDAO {
	@PersistenceContext(name="AplacacionesBancoPersistenceUnit")
	private EntityManager em;

	public void registrar(Administrador administrador) throws Exception {
		
        try {  
        	administrador.setTipo("admin");
        	em.persist(administrador);
        	
        } catch (Exception e) {
        	throw new Exception(e.toString());
        	
        } 	
	}
	
	public Administrador login(String correo,String clave) throws Exception {
		try {
		String jpql = "SELECT a FROM Administrador a WHERE a.correo = :correo and a.clave=:clave";
		Query query = em.createQuery(jpql, Administrador.class);
		query.setParameter("correo", correo);
		query.setParameter("clave", clave);
		Administrador admin = (Administrador) query.getSingleResult();
		System.out.println("------------------------------------"+clave+"   "+correo);
		return admin;
    
}catch(Exception e) {
	throw new Exception(e.toString());
}

	}

		
}
