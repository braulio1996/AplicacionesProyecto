package ec.edu.ups.ON;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.DAO.JefeCreditoDAO;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.JefeCredito;

@Stateless
public class CreditoON {

	@Inject
	private JefeCreditoDAO pdao;

	public boolean guardar(JefeCredito credito) throws Exception {
		return pdao.guardar(credito);

	}

	public JefeCredito loginC(String correo, String clave) throws Exception {

		return pdao.login(correo, clave);
	}

	public List<JefeCredito> listar() throws Exception {
		return pdao.listar();

	}

	public void editar(JefeCredito credito) throws Exception {
		pdao.editar(credito);
	}

	public JefeCredito buscar(String cedula) throws Exception {
		return pdao.buscar(cedula);
	}

	public JefeCredito read(String cedula) {
		return pdao.read(cedula);
	}

	public void eliminar(String cedula) throws Exception {
		pdao.eliminar(cedula);

	}
}
