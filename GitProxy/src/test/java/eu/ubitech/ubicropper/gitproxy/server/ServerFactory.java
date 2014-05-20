package eu.ubitech.ubicropper.gitproxy.server;

import org.eclipse.jetty.server.Server;

/**
 *
 * @author pgouvas
 */

public class ServerFactory {
    private static volatile Server instance = null;
    
    private ServerFactory() { 
    }
    
    public static Server getInstance() {
        if (instance == null) {
            synchronized (ServerFactory.class) {
                if (instance == null) {
                    instance = new Server(9090);
                }
            }
        }
        return instance;
    }
    
}//EoC