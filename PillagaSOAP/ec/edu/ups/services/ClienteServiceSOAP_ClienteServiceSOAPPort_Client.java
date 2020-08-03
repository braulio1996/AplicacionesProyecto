
package ec.edu.ups.services;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.2
 * 2020-07-08T14:54:18.373-05:00
 * Generated source version: 2.7.2
 * 
 */
public final class ClienteServiceSOAP_ClienteServiceSOAPPort_Client {

    private static final QName SERVICE_NAME = new QName("http://Services.ups.edu.ec/", "ClienteServiceSOAPService");

    private ClienteServiceSOAP_ClienteServiceSOAPPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = ClienteServiceSOAPService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        ClienteServiceSOAPService ss = new ClienteServiceSOAPService(wsdlURL, SERVICE_NAME);
        ClienteServiceSOAP port = ss.getClienteServiceSOAPPort();  
        
        {
        System.out.println("Invoking deposito...");
        java.lang.String _deposito_arg0 = "";
        java.lang.String _deposito_arg1 = "";
        java.lang.Double _deposito_arg2 = null;
        java.lang.String _deposito_arg3 = "";
        ec.edu.ups.services.Respuesta _deposito__return = port.deposito(_deposito_arg0, _deposito_arg1, _deposito_arg2, _deposito_arg3);
        System.out.println("deposito.result=" + _deposito__return);


        }
        {
        System.out.println("Invoking retiro...");
        java.lang.String _retiro_arg0 = "";
        java.lang.String _retiro_arg1 = "";
        java.lang.Double _retiro_arg2 = null;
        ec.edu.ups.services.Respuesta _retiro__return = port.retiro(_retiro_arg0, _retiro_arg1, _retiro_arg2);
        System.out.println("retiro.result=" + _retiro__return);


        }
        {
        System.out.println("Invoking trans...");
        ec.edu.ups.services.TransferenciaTemporal _trans_arg0 = null;
        ec.edu.ups.services.Respuesta _trans__return = port.trans(_trans_arg0);
        System.out.println("trans.result=" + _trans__return);


        }
        {
        System.out.println("Invoking login...");
        java.lang.String _login_arg0 = "";
        java.lang.String _login_arg1 = "";
        try {
            ec.edu.ups.services.Respuesta _login__return = port.login(_login_arg0, _login_arg1);
            System.out.println("login.result=" + _login__return);

        } catch (Exception_Exception e) { 
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking buscarCliente...");
        java.lang.String _buscarCliente_arg0 = "";
        try {
            ec.edu.ups.services.ClienteTemporal _buscarCliente__return = port.buscarCliente(_buscarCliente_arg0);
            System.out.println("buscarCliente.result=" + _buscarCliente__return);

        } catch (Exception_Exception e) { 
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking listar...");
        java.util.List<ec.edu.ups.services.Cliente> _listar__return = port.listar();
        System.out.println("listar.result=" + _listar__return);


        }

        System.exit(0);
    }

}