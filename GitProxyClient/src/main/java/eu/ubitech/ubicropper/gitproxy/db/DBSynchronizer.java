package eu.ubitech.ubicropper.gitproxy.db;

import eu.ubitech.ubicropper.gitproxy.config.Configuration;
import eu.ubitech.ubicropper.gitproxy.service.GitSOAPService;
import eu.ubitech.ubicropper.gitproxy.service.GitSOAPServiceService;
import eu.ubitech.ubicropper.gitproxy.test.Tester;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author Panagiotis Gouvas
 */
public class DBSynchronizer {

    Connection connection;

    public DBSynchronizer() {
        connection = ConnectionFactory.getInstance();
    }//Constructor

    /*
     * Fetch newest commit first
     */
    private String getLatestLocalCommit(String provinceid) {
        String ret = null;
        try {
            String query = "SELECT  commitid FROM sif WHERE idprovincia=? ORDER BY commitdate desc LIMIT 1;";
            
            System.out.println("query"+query +" ----- "+ provinceid);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, provinceid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ret = rs.getString("commitid");
                break;
            }
        } //EoM
        catch (SQLException ex) {
            Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }//EoM      

    /*
     * Fetch newest commit first
     */
    private boolean checkPathExists(String path) {
        boolean ret = false;
        try {
            String query = "SELECT path FROM sif where path=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, path);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                rs.getString("path");
                ret = true;
                break;
            }
        } //EoM
        catch (SQLException ex) {
            Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }//EoM     

    /*
     * Fetch newest commit first
     */
    private int checkFilenameExists(String path, String filename) {
        int ret = -1;
        try {
            String query = "SELECT idsif,path,filename FROM sif where path=? and filename=? and missing=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, path);
            preparedStatement.setString(2, filename);
            preparedStatement.setBoolean(3, false);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ret = rs.getInt("idsif");
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }//EoM      

    private void batchNormalizeParents(String commitid) {
        try {
            connection.setAutoCommit(false);

            String query = "SELECT path,filename FROM sif where missing=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, false);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String path = rs.getString("path");
                String filename = rs.getString("filename");
                //process path to get expected path and filename
                //only if separator exists e.g. BuenosAires/SIF
                if (path.contains(Configuration.remoteseparator)) {
                    int index = path.lastIndexOf(Configuration.remoteseparator);
                    String exppath, expfilename = "";
                    if (index != -1) {
                        exppath = path.substring(0, index);
                        expfilename = path.substring(index + 1, path.length());
                        int refid = checkFilenameExists(exppath, expfilename);
                        if (refid != -1) { // i have found it so update
                            updateParent(path, filename, refid);
                        } else { // i have not found it so i have to create it
                            boolean isfolder = !expfilename.contains(".");
                            refid = insertItem(exppath, expfilename, commitid, false, false, isfolder);
                            updateParent(path, filename, refid);
                        }
                    }//if
                }//if
            }//while           

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//EoM

    /*
     * Updates an Item
     */
    public void updateParent(String path, String filename, int sifparentID_idsif) {
        try {
            String query = "update sif set sifparentID_idsif=? where path=? and filename=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, sifparentID_idsif);
            preparedStatement.setString(2, path);
            preparedStatement.setString(3, filename);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//EoM updateParent    

    /*
     * Adds an Item
     */
    public int insertItem(String path, String filename, String commitid, boolean missing, boolean inuse, boolean isfolder) {
        int ret = -1;
        try {
            String query = "Insert into sif "
                    + " (path, filename, creationDate, commitid, commitdate, checksum,missing,inuse,type,idprovincia) "
                    + " values (?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            //get Date
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            //System.out.println( dateFormat.format(date) );
            preparedStatement.setString(1, path);
            preparedStatement.setString(2, filename);
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.setString(4, commitid);
            preparedStatement.setTimestamp(5, timestamp);
            preparedStatement.setString(6, "check");
            preparedStatement.setBoolean(7, missing);
            preparedStatement.setBoolean(8, inuse);
            preparedStatement.setBoolean(9, isfolder);
            preparedStatement.setInt(10, Configuration.provinceid);
            preparedStatement.executeUpdate();
            //get inserted id
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                ret = rs.getInt(1);
            }

        } //EoM
        catch (SQLException ex) {
            Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }//EoM insertItem

    /*
     * Deletes an Item
     */
    public void deleteItem(String path, String filename, String commitid) {
        try {
            String query = "update sif set commitid=?,missing=?,commitdate=? where path=? and filename=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //get Date
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            //
            preparedStatement.setString(1, commitid);
            preparedStatement.setBoolean(2, true);
            //preparedStatement.setBoolean(3, false);
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.setString(4, path);
            preparedStatement.setString(5, filename);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//EoM deletetItem

    public void insertRemoteFilesToLocalDB(String remotecommitid, ArrayList<String> remotefiles) {
        ArrayList<String> tobeinserted = new ArrayList();
        try {
            for (String file : remotefiles) {
                System.out.println("file: " + file);
                //only files that match docroot will be processed
                //if (file.startsWith(Configuration.docroot)) {
                    String[] args = file.split(Configuration.remoteseparator);
                    if (args.length > 1) { //ignore if only root
                        int index = 0;
                        String temp = "";
                        for (String str : args) {
                            if (index == 0) {
                                temp = str;
                            } else {
                                temp = temp + Configuration.remoteseparator + str;
                            }
                            //System.out.println("Raw-Files:"+ temp);
                            if ((index > 0) && (!tobeinserted.contains(temp))) {
                                tobeinserted.add(temp);
                            }
                            index++;
                        }//for                                            
                    }
                //}//if
            }//for

            for (String object : tobeinserted) {
                System.out.println("To-be-Inserted:" + object);
            }
            //Invoke batch insert
            batchInsert(tobeinserted, remotecommitid);
            //TODO add dependency checking / normalization

        } catch (Exception ex) {
            Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//EoM insertRemoteFilesToLocalDB

    private void batchInsert(ArrayList<String> list, String commit) {
        try {
            connection.setAutoCommit(false);
            for (String str : list) {
                boolean isfolder = !str.contains(".");
                int index = str.lastIndexOf(Configuration.remoteseparator);
                String file1, file2 = "";
                if (index != -1) {
                    file1 = str.substring(0, index);
                    file2 = str.substring(index + 1, str.length());
                    System.out.println("SQL insert: " + file1 + " " + file2 + " " + isfolder);
                    //Transactionally Insert
                    insertItem(file1, file2, commit, false, false, isfolder);
                }//if
            }//for
            connection.commit();
        } catch (Exception ex) {
            try {
                connection.rollback(); // Do we need this?
                Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//finally
    }//EoM

    /*
     *   DELETE_BuenosAires/SIF/IDLIBRO3/test8.jpg.txt_/dev/null
     *   ADD_/dev/null_BuenosAires/SIF/IDLIBRO3/test10.jpg.txt
     */
    private void batchUpdate(ArrayList<String> diflist, String commit) {
        try {
            connection.setAutoCommit(false);
            for (String str : diflist) {
                String[] args = str.split("_");
                String operation = args[0];
                String operand1 = args[1];
                String operand2 = args[2];
                System.out.println("operation: " + operation + " operand1: " + operand1 + " operand2: " + operand2);
                switch (operation) {
                    case "DELETE": {
                        //operand1=BuenosAires/SIF/IDLIBRO3/test8.jpg.txt
                        int index = operand1.lastIndexOf(Configuration.remoteseparator);
                        String file1 = "", file2 = "";
                        if (index != -1) {
                            file1 = operand1.substring(0, index);
                            file2 = operand1.substring(index + 1, operand1.length());
                        }
                        //Invoke delete
                        System.out.println("deleteItem: " + file1 + " " + file2 + " " + commit);
                        deleteItem(file1, file2, commit);
                    }
                    break;
                    case "ADD": {
                        //operand2=BuenosAires/SIF/IDLIBRO3/test10.jpg.txt
                        int index = operand2.lastIndexOf(Configuration.remoteseparator);
                        String file1 = "", file2 = "";
                        if (index != -1) {
                            file1 = operand2.substring(0, index);
                            file2 = operand2.substring(index + 1, operand2.length());
                        }
                        //Invoke insert
                        System.out.println("insertItem: " + file1 + " " + file2 + " " + commit);
                        boolean isfolder = !file2.contains(".");
                        insertItem(file1, file2, commit, false, false, isfolder);
                        //TODO invoke Normalization at the end
                    }
                    break;

                } //switch
            }//for
            connection.commit();
        } catch (Exception ex) {
            try {
                connection.rollback(); // Do we need this?
                Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//finally
    }//EoM    

    //initial Sync
    public Map SynchronizeDatabase(String provinceid) {
        Map logMap = new HashMap<>();
        try {
            //get latest Commit If Existing  
            String latestlocalcommit = getLatestLocalCommit(provinceid);

            System.out.println("getLatestLocalCommit: " + latestlocalcommit);
            logMap.put("LatestLocalCommit", "LatestLocalCommit:  " + latestlocalcommit + "<br/>");

            // add & commit only files start with docroot
            //TODO
            
            
            if (latestlocalcommit == null) { //if null get remote latest commit
                String remotecommitid = getLatestRemoteCommit(provinceid);
                if (remotecommitid != null) { //if such a remote commit exists fetch it with contents
                    System.out.println("Insert for the first time: " + remotecommitid);
                    logMap.put("Insert for the first time: ", " Insert for the first time: " + remotecommitid + "<br/>");
                    ArrayList remotefiles = getFilesOfLatestCommit(provinceid);
                    //batch SQL insert
                    insertRemoteFilesToLocalDB(remotecommitid, remotefiles);
                    //normalize
                    batchNormalizeParents(remotecommitid);
                }
            } else { //latest local commit exist                
                System.out.println("Fetching latest Remote Commit");
                logMap.put("Remote Commit", " Fetching latest Remote Commit <br/>");

                String remoteid = DBSynchronizer.getLatestRemoteCommit(provinceid);
                //only if commits differ
                if (!remoteid.equalsIgnoreCase(latestlocalcommit)) {
                    System.out.println("getLatestRemoteCommit:" + remoteid);
                    logMap.put("getLatestRemoteCommit:", " getLatestRemoteCommit: " + remoteid + "<br/>");

                    ArrayList<String> diffs = getFilesBetweenTwoCommits(latestlocalcommit, remoteid,provinceid);
//                    for (String dif : diffs) {
//                        System.out.println("dif:" + dif);
//                        String[] ops = dif.split("_");
//                    }//for
                    //batch SQL insert
                    batchUpdate(diffs, remoteid);
                    //normalize
                    batchNormalizeParents(remoteid);
                }
            }//elseif
        } catch (Exception ex) {
            Logger.getLogger(DBSynchronizer.class.getName()).log(Level.SEVERE, null, ex);
            logMap.put("SEVER Exception", DBSynchronizer.class.getName() + " SEVERE " + ex + "<br/>");
            return logMap;
        }
        return logMap;
    }//EoM

    public static String getLatestRemoteCommit(String provinceid) {
        String ret = null;
        String endpoint = Configuration.wsendpoint;
        String wsdl = endpoint + "?wsdl";
        GitSOAPServiceService service;
        try {
            service = new GitSOAPServiceService(new URL(wsdl), new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "GitSOAPServiceService"));
            GitSOAPService port = service.getGitSOAPServicePort();
            System.out.println("port"+ port.toString());
            BindingProvider bp = (BindingProvider) port;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
            //1 - get latest revision
            System.out.println(Configuration.wstoken+"--"+provinceid);
            ret = port.getLatestRevision(Configuration.wstoken,provinceid);

        } catch (MalformedURLException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }//EoM    

    public static ArrayList<String> getFilesOfLatestCommit(String provinceid) {
        ArrayList<String> ret = new ArrayList();
        String endpoint = Configuration.wsendpoint;
        String wsdl = endpoint + "?wsdl";
        GitSOAPServiceService service;
        try {
            service = new GitSOAPServiceService(new URL(wsdl), new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "GitSOAPServiceService"));
            GitSOAPService port = service.getGitSOAPServicePort();
            BindingProvider bp = (BindingProvider) port;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
            //1 - get latest revision
            String latest = port.getLatestRevision(Configuration.wstoken,provinceid);
            System.out.println("latest:" + latest);
            //2 - get Files of 
            ret = (ArrayList<String>) port.getFilesOfCommit(Configuration.wstoken, latest,provinceid);
            for (String file : ret) {
                System.out.println("file:" + file);
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }//EoM

    public static ArrayList<String> getFilesBetweenTwoCommits(String oldcommit, String newcommit,String provinceid) {
        ArrayList<String> ret = new ArrayList();
        String endpoint = Configuration.wsendpoint;
        String wsdl = endpoint + "?wsdl";
        GitSOAPServiceService service;
        try {
            service = new GitSOAPServiceService(new URL(wsdl), new QName("http://services.gitproxy.ubicropper.ubitech.eu/", "GitSOAPServiceService"));
            GitSOAPService port = service.getGitSOAPServicePort();
            BindingProvider bp = (BindingProvider) port;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
            //1 - Web-Service Invocation 
            ret = (ArrayList<String>) port.getFilesBetweenTwoCommits(Configuration.wstoken, oldcommit, newcommit,provinceid);
            for (String file : ret) {
                System.out.println("diff-file:" + file);
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }//EoM    

}//EoC
