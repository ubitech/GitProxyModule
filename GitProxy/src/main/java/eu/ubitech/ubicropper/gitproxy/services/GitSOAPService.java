package eu.ubitech.ubicropper.gitproxy.services;


import eu.ubitech.ubicropper.gitproxy.git.GitService;
import eu.ubitech.ubicropper.gitproxy.git.ThreadsGroup;
import eu.ubitech.ubicropper.gitproxy.git.ThreadsGroupFactory;
import eu.ubitech.ubicropper.gitproxy.logger.LoggerFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
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
    
    public ArrayList<String> getAllRevisions(String token,String serviceid) {
        LOGGER.info("invoked");
        ArrayList ret = new ArrayList();
        //GitService gitservice = GitServiceFactory.getInstance();
        ThreadsGroup tg= ThreadsGroupFactory.getThreadGroup();
        ConcurrentHashMap cmap = tg.getThreadMap();
        GitService gitservice = (GitService)cmap.get( serviceid );
                
        Iterable<RevCommit> revs = gitservice.getRevisions();
        for (Iterator<RevCommit> it = revs.iterator(); it.hasNext();) {
            RevCommit revCommit = it.next();
            ret.add(revCommit.getName());
        }
        return ret;
    }//EoM       
    
    public String getLatestRevision(String token, String serviceid)  {
        LOGGER.info("invoked");
        String str = null;
        //GitService gitservice = GitServiceFactory.getInstance();
        ThreadsGroup tg= ThreadsGroupFactory.getThreadGroup();
        ConcurrentHashMap cmap = tg.getThreadMap();
        GitService gitservice = (GitService)cmap.get( serviceid );                
        
        Iterable<RevCommit> revs = gitservice.getRevisions();
        for (Iterator<RevCommit> it = revs.iterator(); it.hasNext();) {
            RevCommit revCommit = it.next();
            //get the First only
            str = revCommit.getName();
            break;
        }
        return str;
    }//EoM        
    
    public ArrayList<String> getFilesOfCommit(String token,String revision,String serviceid)  {
        LOGGER.info("invoked");
        ArrayList ret = new ArrayList();
        //GitService gitservice = GitServiceFactory.getInstance();
        ThreadsGroup tg= ThreadsGroupFactory.getThreadGroup();
        ConcurrentHashMap cmap = tg.getThreadMap();
        GitService gitservice = (GitService)cmap.get( serviceid );
        
        ret = gitservice.getFilesOfCommit(revision);
        return ret;
    }//EoM      
    
    
    public ArrayList<String> getFilesBetweenTwoCommits(String token,String revision1,String revision2,String serviceid)  {
        LOGGER.info("invoked");
        ArrayList ret = new ArrayList();
        //GitService gitservice = GitServiceFactory.getInstance();
        ThreadsGroup tg= ThreadsGroupFactory.getThreadGroup();
        ConcurrentHashMap cmap = tg.getThreadMap();
        GitService gitservice = (GitService)cmap.get(serviceid);        
        
        ret = gitservice.getFilesBetweenTwoCommits(revision1,revision2);
        return ret;
    }//EoM 
    
    
  
    
}//EoC