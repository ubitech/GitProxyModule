
package eu.ubitech.ubicropper.gitproxy.test;

import eu.ubitech.ubicropper.gitproxy.service.GitSOAPService;
import eu.ubitech.ubicropper.gitproxy.service.GitSOAPServiceService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author pgouvas
 */
public class Tester {
    
    public static void main(String[] args) {
       Tester tester = new Tester();
       tester.invoke();
    }//EoM
    
    public void invoke(){
        String endpoint = "http://127.0.0.1:9090/SoapContext/SoapPort";
        String wsdl = endpoint+"?wsdl";
        GitSOAPServiceService service;
        try {
            service = new GitSOAPServiceService(new URL(wsdl) , new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "GitSOAPServiceService") );
            GitSOAPService port = service.getGitSOAPServicePort();
            BindingProvider bp = (BindingProvider)port;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint ); 
            //1
            System.out.println("echo:"+port.echo("test") );
            //2
            ArrayList revisions = (ArrayList) port.getAllRevisions("token");
            System.out.println("size(): "+revisions.size());
            //3
            String latest = port.getLatestRevision("Argentina");
            System.out.println("latest:" +latest);
            //4
            ArrayList<String> ret = (ArrayList<String>) port.getFilesOfCommit("test", latest);
            for (String file : ret) {
                System.out.println("file:"+file);
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//EoM
    
}//EoC
