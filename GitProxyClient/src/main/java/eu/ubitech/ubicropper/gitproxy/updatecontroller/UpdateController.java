package eu.ubitech.ubicropper.gitproxy.updatecontroller;

import eu.ubitech.ubicropper.gitproxy.config.Configuration;
import eu.ubitech.ubicropper.gitproxy.db.DBSynchronizer;
import eu.ubitech.ubicropper.gitproxy.service.logger.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Panagiotis Gouvas
 */
public class UpdateController {
    private final static Logger LOGGER = LoggerFactory.getLogger();
    private final Properties prop = new Properties();
    private InputStream input = null;
    
    public Map  synchronize(String dbport,String dbip,String username ,String password ,String dbname,String wsendpoint,String wstoken,String docroot, String remoteseparator ,String provinceid){
        UpdateController updatecontroller = new UpdateController();
        updatecontroller.readProperties(dbport,dbip, username , password , dbname, wsendpoint, wstoken, docroot,  remoteseparator , provinceid);
        DBSynchronizer dbsynchronizer  = new DBSynchronizer();
        Map logMap = dbsynchronizer.SynchronizeDatabase(provinceid);
        return logMap;
    }
    
     public  Map synchronize(){
        UpdateController updatecontroller = new UpdateController();
        updatecontroller.readProperties();
        DBSynchronizer dbsynchronizer  = new DBSynchronizer();
        Map logMap = dbsynchronizer.SynchronizeDatabase("1");
        return logMap;
    }
     
    public static void main(String[] args) {
        UpdateController updatecontroller = new UpdateController();
        updatecontroller.readProperties();
        DBSynchronizer dbsynchronizer  = new DBSynchronizer();
        dbsynchronizer.SynchronizeDatabase("3");
        //TODO create Thread updater
        //TODO handle delete Folders
        //TODO handle addition of nested folder
        //TODO replace System.out with Logger
        
        //dbsynchronizer.insertItem();
        //String remoteid = DBSynchronizer.getLatestRemoteCommit();
        //DBSynchronizer.getFilesBetweenTwoCommits("1d376535cf7aa0badc835fe5cb8ed2f5fafd74bc",remoteid);
    }//EoM
    
    
    private void readProperties() {
        try {
            String path = new java.io.File(".").getCanonicalPath();
            LOGGER.log(Level.INFO, "PATH:{0}", path); 
            input =  this.getClass().getResourceAsStream("/client.properties");
            
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            Configuration.dbport      =   Integer.parseInt( prop.getProperty("dbport").trim() );
            Configuration.dbip        =   prop.getProperty("dbip").trim();            
            Configuration.username    =   prop.getProperty("username").trim();
            Configuration.password    =   prop.getProperty("password").trim();
            Configuration.dbname      =   prop.getProperty("dbname").trim(); 
            Configuration.wsendpoint  =   prop.getProperty("wsendpoint").trim();
            Configuration.wstoken     =   prop.getProperty("wstoken").trim();            
            Configuration.docroot     =   prop.getProperty("docroot").trim();
            Configuration.remoteseparator     =   prop.getProperty("remoteseparator").trim();            
            Configuration.provinceid = Integer.parseInt( prop.getProperty("provinceid").trim() );

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
    
    public void readProperties(String dbport,
            String dbip,
            String username ,
            String password ,
            String dbname,
            String wsendpoint,
            String wstoken,
            String docroot, 
            String remoteseparator ,
            String provinceid) {
       
            // get the property value and print it out
            Configuration.dbport      =    Integer.parseInt(dbport);
            Configuration.dbip        =   dbip;            
            Configuration.username    =   username;
            Configuration.password    =   password;
            Configuration.dbname      =   dbname; 
            Configuration.wsendpoint  =   wsendpoint;
            Configuration.wstoken     =   wstoken;            
            Configuration.docroot     =   docroot;
            Configuration.remoteseparator     =   remoteseparator;            
            Configuration.provinceid =  Integer.parseInt(provinceid);

    }//EoM readproperties  
    
}//EoC
