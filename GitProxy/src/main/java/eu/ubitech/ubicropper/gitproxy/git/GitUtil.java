package eu.ubitech.ubicropper.gitproxy.git;

import eu.ubitech.ubicropper.gitproxy.logger.LoggerFactory;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.errors.UnmergedPathException;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 *
 * @author Panagiotis Gouvas
 */
public class GitUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(); 
    
    public static void createGitRepo(String sgitworkDir) {
        try {
            File gitworkDir = new File(sgitworkDir);
            InitCommand initCommand = Git.init();
            initCommand.setDirectory(gitworkDir);
            Git git = initCommand.call();
        } //EoM createGitRepo
        catch (GitAPIException ex) {
            Logger.getLogger(GitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//EoM    
    

    public static void addAndCommit(Git git, String message, String pathToAdd) {
	add(git, pathToAdd);
	commit(git, message);
    }
 
    private static void add(Git git, String pattern) {
	AddCommand add = git.add();
	try {
	    add.addFilepattern(pattern).call();            
	} catch (NoFilepatternException e) {
	    Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, e);
	} catch (GitAPIException ex) {
            Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//EoM      
    
    private static void commit(Git git, String message) {
	CommitCommand commit = git.commit();
        commit.setAll(true);
	try {
	    commit.setMessage(message).call();
	} catch (NoHeadException e) {
	    Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, e);
	} catch (NoMessageException e) {
	    Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, e);
	} catch (ConcurrentRefUpdateException e) {
	    Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, e);
	} catch (JGitInternalException e) {
	    Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, e);
	} catch (WrongRepositoryStateException e) {
	    Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, e);
	} catch (GitAPIException ex) {
            Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//EoM   
    
    public static boolean checkThatExistsAndProperDirectory(String sgitworkDir){
        File file = new File(sgitworkDir);
        return file.exists() && file.isDirectory();
    }//EoM
    
    public static Iterable<RevCommit> log(Git git) {
	Iterable<RevCommit> ret=null;
        LogCommand log = git.log();
	try {
	    ret = log.call();
	} catch (NoFilepatternException e) {
	    Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, e);
	} catch (GitAPIException ex) {
            Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }//EoM     
    
    //List all files of a Commit e.g.
    //git ls-tree -r --name-only 79813c0d381a9db2e8c2c609b790c7baa47d453f
    
    
}//EoC
