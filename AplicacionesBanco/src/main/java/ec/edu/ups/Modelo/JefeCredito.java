package ec.edu.ups.Modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 *Esta clase hija hereda  los atributos de la entidad Persona               
 * @version: 27/05/2020
 * @author: Braulio Castro
 * 
 */

@Entity
public class JefeCredito extends Persona {
	@OneToMany(cascade = CascadeType.ALL)
	private List<SolicitudCredito> solicitudesCredito;

	public List<SolicitudCredito> getSolicitudesCredito() {
		return solicitudesCredito;
	}

	public void setSolicitudesCredito(List<SolicitudCredito> solicitudesCredito) {
		this.solicitudesCredito = solicitudesCredito;
	}

}
