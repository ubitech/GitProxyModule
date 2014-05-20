package eu.ubitech.ubicropper.gitproxy.git;

import eu.ubitech.ubicropper.gitproxy.config.Configuration;
import eu.ubitech.ubicropper.gitproxy.logger.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.StatusCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 *
 * @author Panagiotis Gouvas
 */
public class GitService implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger();    
    private final String gitdir  = Configuration.ScannedFolder;
    private final String gitfile = gitdir+File.separatorChar+".git";
    private boolean isalive = true;
    private Git git;
    
    GitService() {
        try {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            //if git is not initialized - do it
            if (!new File(gitfile).exists()) {
                LOGGER.info("Git is not initialized. I will perform initialization");
                GitUtil.createGitRepo(gitdir);
            }
            //in any case open it
            git = Git.open(new File(gitdir));
        } catch (IOException ex) {
            Logger.getLogger(GitService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void run () {
        while (isalive){
            try {
                LOGGER.info("Thread Working.............");
                if (!new File(gitfile).exists()) {
                    LOGGER.info("Git is not initialized. I will perform initialization");
                    GitUtil.createGitRepo(gitdir);
                    git = Git.open(new File(gitdir));
                }
                StatusCommand status = git.status();
                org.eclipse.jgit.api.Status ret = status.call();
                LOGGER.info(
                    "isClean       :"+ret.isClean()               +"\n"+
                    "getAdded      :"+ret.getAdded().size()       +"\n"+
                    "getChanged    :"+ret.getChanged().size()     +"\n"+
                    "getConflicting:"+ret.getConflicting().size() +"\n"+ 
                    "getMissing    :"+ret.getMissing().size()     +"\n"+
                    "getModified   :"+ret.getModified().size()    +"\n"+
                    "getRemoved    :"+ret.getRemoved().size()     +"\n"+
                    "getUntracked  :"+ret.getUntracked().size()   +"\n"+
                    "getUntrackedFolders  :"+ret.getUntrackedFolders().size()   
                );
                //condition to add and Commit
                if ( !ret.getUntracked().isEmpty()          ||  //new File Added
                     //!ret.getUntrackedFolders().isEmpty()   ||  //new Folder Added --Folders should not be changed
                     !ret.getMissing().isEmpty()            ||  //File Deleted
                     !ret.getModified().isEmpty()             //File Modified
                   ) {
                    LOGGER.info("Performing Add and Commit");
                    GitUtil.addAndCommit( git , (new Date()).toString() , "." );
                    //test


                } else {
                    LOGGER.info("Nothing to Commit");
                }
                
                //Default Threading Policy
                Thread.sleep(Configuration.timeout);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GitService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GitService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GitAPIException ex) {
                Logger.getLogger(GitService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoWorkTreeException ex) {
                Logger.getLogger(GitService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//while
    }//EoM run
        
    public synchronized void terminate(){
        this.isalive = false;
    }//EoM
    
    public synchronized boolean isAlive(){
        return this.isalive;
    }
    
    public Iterable<RevCommit> getRevisions(){
        return GitUtil.log(git);
    }
    
}