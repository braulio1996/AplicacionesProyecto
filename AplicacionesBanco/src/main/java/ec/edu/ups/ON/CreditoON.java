package ec.edu.ups.ON;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.DAO.CreditoDAO;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Credito;


@Stateless
public class CreditoON {
	
	@Inject
	private CreditoDAO pdao;
	
	
	
	
	public boolean guardar(Credito credito) throws Exception {
		return pdao.guardar(credito);
		
	}

	public Credito loginC(String correo,String clave) throws Exception{
		
		return pdao.login(correo,clave);
	}
	
	public List<Credito>listar() throws Exception{
		return pdao.listar();
	
}
	public void editar(Credito credito) throws Exception {
		pdao.editar(credito);
	}
	
	public Credito buscar(String cedula) throws Exception {
		return pdao.buscar(cedula);
	}
	public Credito read(String cedula) {
		return pdao.read(cedula);
	}
	public void eliminar(String cedula) throws Exception {
		pdao.eliminar(cedula);
		
	}
}
