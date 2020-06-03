package ec.edu.ups.ON;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.DAO.AccesoDAO;
import ec.edu.ups.Modelo.Acceso;

@Stateless
public class AccesoON {

	@Inject
	private AccesoDAO pdao;

	public boolean guardar(Acceso acceso) throws Exception {
		return pdao.guardar(acceso);

	}



	public List<Acceso> listar() throws Exception {
		return pdao.listar();

	}

	public void editar(Acceso Acceso) throws Exception {
		pdao.editar(Acceso);
	}

	public Acceso listarAccesos(int cliente, String estado) throws Exception {
		return pdao.listaAccesos(cliente, estado);
	}

	public Acceso read(int cliente) {
		return pdao.read(cliente);
	}


	
	public long contar() {
		return pdao.contar();
	}
}
