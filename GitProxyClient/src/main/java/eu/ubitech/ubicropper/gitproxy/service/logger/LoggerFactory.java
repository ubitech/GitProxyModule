package eu.ubitech.ubicropper.gitproxy.service.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Panagiotis Gouvas
 */
public class LoggerFactory {
    private static volatile Logger instance = null;
    
    private LoggerFactory() { 
    }//EoC
    
    public static Logger getLogger() {
        if (instance == null) {
            synchronized (LoggerFactory.class) {
                if (instance == null) {                   
                    instance = Logger.getLogger("GitProxyLogger");
                    try{
                        FileHandler fh =  new FileHandler("../standalone/log/gitproxy.log", true);
                        instance.addHandler(fh);
                        SimpleFormatter formatter = new SimpleFormatter();  
                        fh.setFormatter(formatter);         
                    } catch (IOException ex) {  
                        //LOGGER.severe(ex.getMessage());
                    } catch (SecurityException ex) {
                        //LOGGER.severe(ex.getMessage());
                    }                       
                }
            }
        }
        return instance;
    }//EoM    
}//EoC
