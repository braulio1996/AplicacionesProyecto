
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
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2020-06-22T18:05:52.565-05:00
 * Generated source version: 2.7.11
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
        System.out.println("Depositos");
        java.lang.String _deposito_arg0 = "0106220471";
        java.lang.String _deposito_arg1 = "0106220478";
        java.lang.Double _deposito_arg2 = (double) 100;
        port.deposito(_deposito_arg0, _deposito_arg1, _deposito_arg2);


        }
        {
        System.out.println("Retiro");
        java.lang.String _retiro_arg0 = "0106220471";
        java.lang.String _retiro_arg1 = "0106220478";
        java.lang.Double _retiro_arg2 = (double) 10;
        port.retiro(_retiro_arg0, _retiro_arg1, _retiro_arg2);


       }
        {
        System.out.println("Invoking transferencia...");
        java.lang.String _transferencia_arg0 = "4529867291";
        java.lang.String _transferencia_arg1 = "1651877268";
        java.lang.Double _transferencia_arg2 = 50.0;
        port.transferencia(_transferencia_arg0, _transferencia_arg1, _transferencia_arg2);

        }

        System.exit(0);
    }

}
