package eu.ubitech.ubicropper.gitproxy.config;

/**
 *
 * @author pgouvas
 */
public class ServerConfiguration {

    public int port;
    public int timeout;
    public String token;     
    
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}//EoC
