package eu.ubitech.ubicropper.gitproxy.services;

import eu.ubitech.ubicropper.gitproxy.git.GitService;
import eu.ubitech.ubicropper.gitproxy.git.GitServiceFactory;
import eu.ubitech.ubicropper.gitproxy.logger.LoggerFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;
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
    
    public ArrayList<String> getAllRevisions(String token) {
        LOGGER.info("invoked");
        ArrayList ret = new ArrayList();
        GitService gitservice = GitServiceFactory.getInstance();
        Iterable<RevCommit> revs = gitservice.getRevisions();
        for (Iterator<RevCommit> it = revs.iterator(); it.hasNext();) {
            RevCommit revCommit = it.next();
            ret.add(revCommit.getName());
        }
        return ret;
    }//EoM       
    
    public String getLatestRevision(String token)  {
        LOGGER.info("invoked");
        String str = null;
        GitService gitservice = GitServiceFactory.getInstance();
        Iterable<RevCommit> revs = gitservice.getRevisions();
        for (Iterator<RevCommit> it = revs.iterator(); it.hasNext();) {
            RevCommit revCommit = it.next();
            //get the First only
            str = revCommit.getName();
            break;
        }
        return str;
    }//EoM        
    
    public ArrayList<String> getFilesOfCommit(String token,String revision)  {
        LOGGER.info("invoked");
        ArrayList ret = new ArrayList();
        GitService gitservice = GitServiceFactory.getInstance();
        ret = gitservice.getFilesOfCommit(revision);
        return ret;
    }//EoM      
    
    
    
    
}//EoC