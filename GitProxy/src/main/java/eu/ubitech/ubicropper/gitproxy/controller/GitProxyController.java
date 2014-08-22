package eu.ubitech.ubicropper.gitproxy.controller;

import eu.ubitech.ubicropper.gitproxy.config.ProvinceConfiguration;
import eu.ubitech.ubicropper.gitproxy.config.ServerConfiguration;
import eu.ubitech.ubicropper.gitproxy.git.GitService;
import eu.ubitech.ubicropper.gitproxy.git.GitUtil;
import eu.ubitech.ubicropper.gitproxy.git.ThreadsGroup;
import eu.ubitech.ubicropper.gitproxy.git.ThreadsGroupFactory;
import eu.ubitech.ubicropper.gitproxy.logger.LoggerFactory;
import eu.ubitech.ubicropper.gitproxy.server.ServerFactory;
import eu.ubitech.ubicropper.gitproxy.services.GitSOAPService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
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
    //Jetty Server
    private Server server;
    private ConcurrentHashMap threadmap = new ConcurrentHashMap();

    //private final Properties prop = new Properties();
    //private InputStream input = null;
    //Git Service
    //private GitService gitservice;
    public GitProxyController() {
    }

    public static void main(String[] args) {
        try {
            LOGGER.info("GitProxyController initiated");
            File[] propfiles = null;
            String path = new java.io.File(".").getCanonicalPath();
            LOGGER.log(Level.INFO, "PATH:{0}", path);

            File folder1 = new File(path + "/conf");
            propfiles = folder1.listFiles();

            for (File file : propfiles) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                } else {
                    LOGGER.info("Error Reading Property Files");
                    System.exit(0);
                }
            }

            /*
             for (final File fileEntry : folder.listFiles()) {
             if (fileEntry.isDirectory()) {
             propfiles = fileEntry.listFiles();//TODO add filter
             } else {
             LOGGER.info("Error Reading Property Files");
             System.exit(0);
             }
             }
             */
            if (propfiles != null) {
                for (int i = 0; i < propfiles.length; i++) {
                    File propfile = propfiles[i];
                    String propfilestr = propfile.getAbsolutePath();

                    if (!propfilestr.contains("server.properties")) {
                        GitProxyController controller = new GitProxyController();

                        ProvinceConfiguration provinceConfig = controller.readProvinceProperties(propfilestr);
                        if (controller.validateProperties(provinceConfig)) {
                            //TODO to test tomorrow morning  1 massive properties 2 separation 3 upon 24 threads pom.xml client 4 client serviceid
                            controller.startGitService(provinceConfig);
                            //controller.startJettyServer(serverConfig);
                        } else {
                            LOGGER.severe("Indicated Directory is not Valid");
                        }

                    }

                }//for

                GitProxyController controller4Jetty = new GitProxyController();
                ServerConfiguration serverConfig = controller4Jetty.readServerProperties(path + "/conf/server.properties");
                controller4Jetty.startJettyServer(serverConfig);

                //TODO print seeThreadMap
                             

            }

        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        }

//        GitProxyController controller = new GitProxyController();
//        controller.readProperties();
//        if (controller.validateProperties()){
//            controller.startGitService();
//            controller.startJettyServer();           
//        } else {
//            LOGGER.severe("Indicated Directory is not Valid");
//        }
    }//main

    private void updateMap() {
        ThreadsGroup tg = ThreadsGroupFactory.getThreadGroup();
        tg.updateThreadMap(threadmap);
    }

    private void addToThreadMap(String provinceid, GitService gitservice) {
        ThreadsGroup tg = ThreadsGroupFactory.getThreadGroup();
        tg.addToThreadMap(provinceid, gitservice);
    }

    private void removeFromThreadMap(String provinceid) {
        ThreadsGroup tg = ThreadsGroupFactory.getThreadGroup();
        tg.removeFromThreadMap(provinceid);
    }

    private ConcurrentHashMap seeThreadMap() {
        ThreadsGroup tg = ThreadsGroupFactory.getThreadGroup();
        return tg.getThreadMap();
    }

    private synchronized ServerConfiguration readServerProperties(String propfile) {
        ServerConfiguration conf = null;
        try {
            FileInputStream input = new FileInputStream(propfile);
            final Properties prop = new Properties();
            // load a properties file
            prop.load(input);

            conf = new ServerConfiguration();
            conf.port = Integer.parseInt(prop.getProperty("port").trim());
            conf.timeout = Integer.parseInt(prop.getProperty("timeout").trim());
            conf.token = prop.getProperty("token").trim();
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        }
        return conf;
    }//EoM readServerProperties

    private synchronized ProvinceConfiguration readProvinceProperties(String propfile) {
        ProvinceConfiguration conf = null;
        try {
            FileInputStream input = new FileInputStream(propfile);
            final Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            conf = new ProvinceConfiguration();
            conf.ScannedFolder = prop.getProperty("ScannedFolder").trim();
            conf.provinceid = prop.getProperty("provinceid").trim();
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        }
        return conf;
    }//EoM readProvinceProperties

    private boolean validateProperties(ProvinceConfiguration conf) {
        return GitUtil.checkThatExistsAndProperDirectory(conf.ScannedFolder);
    }

    private void startGitService(ProvinceConfiguration provinceConfig) {
        //geia sou facotry pattern
        //GitService gitservice = GitServiceFactory.getInstance(); 
        GitService gitservice = new GitService(provinceConfig.getScannedFolder());
        Thread t = new Thread(gitservice);

        t.setName(provinceConfig.getProvinceid());
        LOGGER.info("thread to string" + t.toString());
        threadmap.put(provinceConfig.getProvinceid(), gitservice);
        System.out.println("threadmap size" + threadmap.size());
        t.start();
        //updateMap();
        addToThreadMap(provinceConfig.getProvinceid(), gitservice);
    }//EoM

    private void stopGitService(String serviceid) {
        GitService gitservice = (GitService) threadmap.get(serviceid);
        if (gitservice != null) {
            gitservice.terminate();
            threadmap.remove(serviceid);
            //updateMap();
            removeFromThreadMap(serviceid);
        }//if

    }//EoM    

    private void startJettyServer(ServerConfiguration serverConfig) {
        try {
            //get Singleton
            server = ServerFactory.getInstance();
            Object implementor = new GitSOAPService();
//            String address = "http://0.0.0.0:"+Configuration.port+"/SoapContext/SoapPort"; //TODO
            String address = "http://0.0.0.0:" + serverConfig.port + "/SoapContext/SoapPort";
            Endpoint.publish(address, implementor);
            //manage thread
            server.start();
            server.join();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }//EoM

    private void stopJettyServer() {
        try {
            server.stop();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }//EoM    

}//EoC
