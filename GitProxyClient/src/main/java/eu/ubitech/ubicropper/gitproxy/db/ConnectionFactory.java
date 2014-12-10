package eu.ubitech.ubicropper.gitproxy.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import eu.ubitech.ubicropper.gitproxy.config.Configuration;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Panagiotis Gouvas
 */
public class ConnectionFactory {

    private static volatile Connection instance = null;

    private ConnectionFactory() {
    }//EoC

    public static Connection getInstance() {
        try {
            if (instance == null || instance.isClosed()) {
                synchronized (ConnectionFactory.class) {
                    if (instance == null || instance.isClosed()) {
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            instance = DriverManager.getConnection("jdbc:mysql://" + Configuration.dbip + ":" + Configuration.dbport + "/" + Configuration.dbname + "?autoReconnect=true&failOverReadOnly=false&maxReconnects=10", Configuration.username, Configuration.password);

//                            ComboPooledDataSource cpds = new ComboPooledDataSource();
//                            cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver            
//                            cpds.setJdbcUrl("jdbc:mysql://" + Configuration.dbip + ":" + Configuration.dbport + "/" + Configuration.dbname);
//                            cpds.setUser(Configuration.username);
//                            cpds.setPassword(Configuration.password);
//                            
//                            instance = cpds.getConnection(); 
                        } catch (SQLException ee) {
                            System.out.println("Connection Error");
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instance;
    }//EoM

}//EoC
