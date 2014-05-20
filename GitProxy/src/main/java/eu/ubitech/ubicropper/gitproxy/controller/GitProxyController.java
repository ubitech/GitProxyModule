package eu.ubitech.ubicropper.gitproxy.controller;

import eu.ubitech.ubicropper.gitproxy.config.Configuration;
import eu.ubitech.ubicropper.gitproxy.git.GitService;
import eu.ubitech.ubicropper.gitproxy.git.GitServiceFactory;
import eu.ubitech.ubicropper.gitproxy.git.GitUtil;
import eu.ubitech.ubicropper.gitproxy.logger.LoggerFactory;
import eu.ubitech.ubicropper.gitproxy.server.ServerFactory;
import eu.ubitech.ubicropper.gitproxy.services.GitSOAPService;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Endpoint;
import org.eclipse.jetty.server.Server;

/**
 *
 * @author Panagiotis Gouvas
 */
public class GitProxyController {
    private final static Logger LOGGER = LoggerFactory.getLogger();
    private final Properties prop = new Properties();
    private InputStream input = null;
    //Git Service
    private GitService gitservice;
    //Jetty Server
    private Server server;    
    
    
    public GitProxyController(){
    }
    
    public static void main(String[] args) {        
        GitProxyController controller = new GitProxyController();
        LOGGER.info("GitProxyController initiated");
        controller.readProperties();
        if (controller.validateProperties()){
            controller.startGitService();
            controller.startJettyServer();           
        } else {
            LOGGER.severe("Indicated Directory is not Valid");
        }

    }//main

    private void readProperties() {
        try {
            String path = new java.io.File(".").getCanonicalPath();
            LOGGER.log(Level.INFO, "PATH:{0}", path);
            input = new FileInputStream("gitproxy.properties");
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            Configuration.port =        Integer.parseInt( prop.getProperty("port").trim() );
            Configuration.ScannedFolder = prop.getProperty("ScannedFolder").trim();
            Configuration.timeout =     Integer.parseInt( prop.getProperty("timeout").trim() );
            Configuration.token =       prop.getProperty("token").trim();            
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.severe(e.getMessage());
                }
            }
        }
    }//EoM readproperties
    
    private boolean validateProperties(){
        return GitUtil.checkThatExistsAndProperDirectory(Configuration.ScannedFolder);
    }
    
    private void startGitService(){
        gitservice = GitServiceFactory.getInstance();         
        Thread t = new Thread(gitservice);
        t.start();
    }//EoM
    
    private void stopGitService(){
        gitservice.terminate();
    }//EoM    
    
    private void startJettyServer(){
        try {
            //get Singleton
            server = ServerFactory.getInstance();
            Object implementor = new GitSOAPService();
            String address = "http://0.0.0.0:"+Configuration.port+"/SoapContext/SoapPort";
            Endpoint.publish(address, implementor);            
            //manage thread
            server.start();
            server.join();
        } 
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }//EoM
    
    private void stopJettyServer(){
        try {
            server.stop();
        } 
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }//EoM    
    
    
}//EoC
