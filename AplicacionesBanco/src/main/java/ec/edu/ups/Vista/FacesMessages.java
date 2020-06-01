package ec.edu.ups.Vista;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


@ManagedBean
public class FacesMessages {
	    public void infoMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	    public void errorMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	    public void fatalMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	    public void warningMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
}
