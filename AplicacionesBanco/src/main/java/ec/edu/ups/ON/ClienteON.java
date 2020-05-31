package ec.edu.ups.ON;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.DAO.ClienteDAO;
import ec.edu.ups.Modelo.Cajero;
import ec.edu.ups.Modelo.Cliente;
import ec.edu.ups.Modelo.Cuenta;
import ec.edu.ups.Modelo.Transferencia;


@Stateless
public class ClienteON {
	
	@Inject
	private ClienteDAO pdao;
	
	private Transferencia trasferencia;
	private List<Transferencia>trasferencias;
	
	
	
	
	public boolean guardar(Cliente cliente) throws Exception {
		return pdao.guardar(cliente);
		
	}

	public Cliente loginC(String correo,String clave) throws Exception{
		
		return pdao.login(correo,clave);
	}
	
	public List<Cliente>listar() throws Exception{
		return pdao.listar();
	
}
	public void editar(Cliente cliente) throws Exception {
		pdao.editar(cliente);
	}
	
	public Cliente buscar(String cedula) throws Exception {
		return pdao.buscar(cedula);
	}
	public Cliente read(String cedula) {
		return pdao.read(cedula);
	}
	public void eliminar(String cedula) throws Exception {
		pdao.eliminar(cedula);
		
	}
	public Cuenta buscarCuenta(String numero) throws Exception {
		return pdao.buscarCuenta(numero);
	}
	public Transferencia trasferencia(Cliente cliente ,String cuentaDestino,Double monto) throws Exception {
		trasferencia= new Transferencia();
	
		Cuenta cuentaD=pdao.buscarCuenta(cuentaDestino);
		Cuenta cuentaO=cliente.getCuenta();
		Double saldoDestino= cuentaD.getSaldo()+monto;
		Double salgoOrigen=cuentaO.getSaldo()-monto;
		trasferencia.setMonto(monto);
		trasferencia.setNumeroCuenta(cuentaDestino);
		cuentaD.setSaldo(saldoDestino);
		pdao.editarCuenta(cuentaD);
		pdao.editarCuenta(cuentaO);
		
		
	return trasferencia;	
	}
}
