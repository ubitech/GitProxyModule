package eu.ubitech.ubicropper.gitproxy.services;

import eu.ubitech.ubicropper.gitproxy.git.GitService;
import eu.ubitech.ubicropper.gitproxy.git.ThreadsGroup;
import eu.ubitech.ubicropper.gitproxy.git.ThreadsGroupFactory;
import eu.ubitech.ubicropper.gitproxy.logger.LoggerFactory;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 *
 * @author Panagiotis Gouvas
 */
@WebService()
public class GitSOAPService {

    private final static Logger LOGGER = LoggerFactory.getLogger();

    //for testing purposes only    
    public String echo(String test) {
        return test;
    }//EoM    

    public ArrayList<String> getAllRevisions(String token, String serviceid) {
        LOGGER.info("invoked");
        ArrayList ret = new ArrayList();
        //GitService gitservice = GitServiceFactory.getInstance();
        ThreadsGroup tg = ThreadsGroupFactory.getThreadGroup();
        ConcurrentHashMap cmap = tg.getThreadMap();
        GitService gitservice = (GitService) cmap.get(serviceid);

        Iterable<RevCommit> revs = gitservice.getRevisions();
        for (Iterator<RevCommit> it = revs.iterator(); it.hasNext();) {
            RevCommit revCommit = it.next();
            ret.add(revCommit.getName());
        }
        return ret;
    }//EoM       

    public String getLatestRevision(String token, String serviceid) {
        LOGGER.info("invoked");
        String str = null;
        //GitService gitservice = GitServiceFactory.getInstance();
        ThreadsGroup tg = ThreadsGroupFactory.getThreadGroup();
        ConcurrentHashMap cmap = tg.getThreadMap();
        GitService gitservice = (GitService) cmap.get(serviceid);

        Iterable<RevCommit> revs = gitservice.getRevisions();
        for (Iterator<RevCommit> it = revs.iterator(); it.hasNext();) {
            RevCommit revCommit = it.next();
            //get the First only
            str = revCommit.getName();
            break;
        }
        return str;
    }//EoM        

    public ArrayList<String> getFilesOfCommit(String token, String revision, String serviceid) {
        LOGGER.info("invoked");
        ArrayList ret = new ArrayList();
        //GitService gitservice = GitServiceFactory.getInstance();
        ThreadsGroup tg = ThreadsGroupFactory.getThreadGroup();
        ConcurrentHashMap cmap = tg.getThreadMap();
        GitService gitservice = (GitService) cmap.get(serviceid);

        ret = gitservice.getFilesOfCommit(revision);
        return ret;
    }//EoM      

    public ArrayList<String> getFilesBetweenTwoCommits(String token, String revision1, String revision2, String serviceid) {
        LOGGER.info("invoked");
        ArrayList ret = new ArrayList();
        //GitService gitservice = GitServiceFactory.getInstance();
        ThreadsGroup tg = ThreadsGroupFactory.getThreadGroup();
        ConcurrentHashMap cmap = tg.getThreadMap();
        GitService gitservice = (GitService) cmap.get(serviceid);

        ret = gitservice.getFilesBetweenTwoCommits(revision1, revision2);
        return ret;
    }//EoM 

    public boolean saveImageToCERIFBook(String token, String newImagePathURIFolderPath, byte[] imageBytes, String filepath) {
        try {

            File newImagePathURIFolder = new File(newImagePathURIFolderPath);

            if (!newImagePathURIFolder.exists()) {
                try {
                    boolean newCERIFBOOK = newImagePathURIFolder.mkdirs();
                    if (!newCERIFBOOK) {
                        throw new EmptyStackException();
                    }
                } catch (EmptyStackException e) {
                    System.out.println("Avise el Administrador sobre los terminos de almacenamiento de los nuevos CERIs.");
                    return false;
                }

            }

            FileOutputStream fos = new FileOutputStream(filepath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
            outputStream.write(imageBytes);
            outputStream.close();

            System.out.println("Save Image to filepath: " + filepath);

        } catch (IOException ex) {
            Logger.getLogger(GitSOAPService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean deleteImage(String token, String imageLocation) {

        File CERIToDelete = new File(imageLocation);
        if (!CERIToDelete.delete()) {
            System.out.println("Could not delete Image : " + imageLocation);
            return false;
        }

        return true;
    }

    public boolean manageTmpFolder(String token, String tempFolderURI) {

        File tempFolder = new File(tempFolderURI);
        if (tempFolder.exists()) {

            for (File file : tempFolder.listFiles()) {
                file.delete();
            }

        } else if (!tempFolder.exists()) {
            try {
                boolean newtempFolder = tempFolder.mkdirs();
                //Runtime.getRuntime().exec("chmod -R 777 " + "pathToFolderToCreate");
                if (!newtempFolder) {
                    throw new EmptyStackException();
                }
            } catch (EmptyStackException e) {
                System.out.println("Avise el Administrador sobre los terminos de almacenamiento del carpeta Temporal de UBICropper:" + tempFolderURI);
                return false;
            }

        }

        return true;
    }

    public byte[] getImage(String token, String filepath) {

        System.out.println("Sending file: " + filepath);

        File file = new File(filepath);
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);

            BufferedInputStream inputStream = new BufferedInputStream(fis);
            byte[] fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);
            inputStream.close();

            return fileBytes;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GitSOAPService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GitSOAPService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public boolean initializeSIF4Province(String token, String SIFFolderURI, String idprovince, String provinciaName) {

        File SIFfolder = new File(SIFFolderURI + "/" + provinciaName);

        if (!SIFfolder.exists()) {

            try {
                boolean newSIFFolder = SIFfolder.mkdirs();

                if (!newSIFFolder) {
                    return false;
                }

                String path = new java.io.File(".").getCanonicalPath();
                File propertiesFolder = new File(path + "/conf");

                String fileName = provinciaName;
                File propertiesFile = new File(propertiesFolder, fileName + ".properties");
                if (!propertiesFile.exists()) {
                    boolean iscreatedPropertiesfile = propertiesFile.createNewFile();
                    if (!iscreatedPropertiesfile) {
                        return false;
                    }
                }

                String content = "ScannedFolder=" + SIFFolderURI + "\n"
                        + "provinceid=" + idprovince;

                FileWriter fw = new FileWriter(propertiesFile.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();

                System.out.println("New properties files has been created :" + propertiesFile.getName());

            } catch (IOException ex) {
                Logger.getLogger(GitSOAPService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EmptyStackException e) {
                System.out.println("Avise el Administrador sobre los terminos de almacenamiento del carpeta Temporal de UBICropper:" + SIFFolderURI);
                return false;
            }
        }
        return true;
    }

    public boolean saveImage(String token, byte[] imageBytes, String filepath) {
        try {

            FileOutputStream fos = new FileOutputStream(filepath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
            outputStream.write(imageBytes);
            outputStream.close();

            System.out.println("Save Image to filepath: " + filepath);

        } catch (IOException ex) {
            Logger.getLogger(GitSOAPService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

}//EoC
