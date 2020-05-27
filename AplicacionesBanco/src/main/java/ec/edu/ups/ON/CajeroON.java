package ec.edu.ups.ON;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.DAO.CajeroDAO;
import ec.edu.ups.Modelo.Cajero;


@Stateless
public class CajeroON {
	
	@Inject
	private CajeroDAO pdao;
	
	
	
	
	public boolean guardar(Cajero cajero) throws Exception {
		return pdao.guardar(cajero);
		
	}

	public Cajero loginC(String correo,String clave) throws Exception{
		
		return pdao.login(correo,clave);
	}
	

	public List<Cajero>listar() throws Exception{
		return pdao.listar();
	
}
	public void editar(Cajero cajero) throws Exception {
		pdao.editar(cajero);
	}
	
	public Cajero buscar(String cedula) throws Exception {
		return pdao.buscar(cedula);
	}
	public Cajero read(String cedula) {
		return pdao.read(cedula);
	}
	public void eliminar(String cedula) throws Exception {
		pdao.eliminar(cedula);
		
	}
}
