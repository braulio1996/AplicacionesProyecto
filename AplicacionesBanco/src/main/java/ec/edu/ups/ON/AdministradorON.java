package ec.edu.ups.ON;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.DAO.AdministradorDAO;
import ec.edu.ups.Modelo.Administrador;


@Stateless
public class AdministradorON {
	
	@Inject
	private AdministradorDAO pdao;
	
	
	
	
	public void registrar(Administrador administrador) throws Exception {
		pdao.registrar(administrador);
		
	}
	public void update(Administrador administrador) throws Exception {
		pdao.update(administrador);
		
	}

	public Administrador loginC(String correo,String clave) throws Exception{
		
		return pdao.login(correo,clave);
	}
	


}
