package eu.ubitech.ubicropper.gitproxy.server;

import org.eclipse.jetty.server.Server;

/**
 *
 * @author Panagiotis Gouvas
 */

public class ServerFactory {
    private static volatile Server instance = null;
    
    private ServerFactory() { 
    }//EoC
    
    public static Server getInstance() {
        if (instance == null) {
            synchronized (ServerFactory.class) {
                if (instance == null) {
                    instance = new Server();
                }
            }
        }
        return instance;
    }//EoM
}//EoC