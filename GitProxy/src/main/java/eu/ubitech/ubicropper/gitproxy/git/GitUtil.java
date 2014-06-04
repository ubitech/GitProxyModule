package eu.ubitech.ubicropper.gitproxy.git;

import eu.ubitech.ubicropper.gitproxy.logger.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.DiffCommand;
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
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.errors.CorruptObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.TreeWalk;

/**
 *
 * @author Panagiotis Gouvas
 */
public class GitUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(); 
    
    public static boolean checkThatExistsAndProperDirectory(String sgitworkDir){
        File file = new File(sgitworkDir);
        return file.exists() && file.isDirectory();
    }//EoM    
    
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
    
    public static Iterable<RevCommit> getRevisions(Git git) {
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
    public static ArrayList getFilesOfCommit(Git git,String revision){
        ArrayList array = new ArrayList();
        RevCommit revCommit = null;
        try {
            Iterable<RevCommit> revs = GitUtil.getRevisions(git);
            for (Iterator<RevCommit> it = revs.iterator(); it.hasNext();) {
                revCommit = it.next();
                if (revCommit.getName().equalsIgnoreCase(revision)) break;
            }//for            
            if (revCommit!=null){
                RevTree tree = revCommit.getTree();
                LOGGER.info("Having tree: " + tree);            
                TreeWalk treeWalk = new TreeWalk(git.getRepository());
                treeWalk.addTree(tree);
                treeWalk.setRecursive(true);
                while (treeWalk.next()) {
                    LOGGER.info("found: " + treeWalk.getPathString());
                    array.add( treeWalk.getPathString() );
                }//while                
            }//if
        } //EoM
        catch (IncorrectObjectTypeException ex) {
            Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CorruptObjectException ex) {
            Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }//EoM
    
    //git diff 0da94be  59ff30c > my.patch
     public static ArrayList<String> getFilesBetweenTwoCommits(Git git, String oldrevision,String newrevision){
        ArrayList array = new ArrayList();
        DiffCommand diffcommand =  git.diff();
        RevTree oldtree = null;
        RevTree newtree = null;
        try {
            ObjectReader reader = git.getRepository().newObjectReader();
            //newid
            ObjectId newId = git.getRepository().resolve(newrevision + "^{tree}");
            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
            newTreeIter.reset(reader, newId);            
            //old tree
            ObjectId oldId = git.getRepository().resolve(oldrevision + "^{tree}");            
            CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
            oldTreeIter.reset(reader, oldId);
            //configure
            diffcommand.setOldTree(oldTreeIter);
            diffcommand.setNewTree(newTreeIter);
            //get dif entries
            List<DiffEntry> diffentries = diffcommand.call();
            for (DiffEntry diffEntry : diffentries) {
                String ret = diffEntry.getChangeType().toString()+"_"+diffEntry.getOldPath()+"_"+diffEntry.getNewPath(); 
                array.add(ret);
                LOGGER.info("diff: "+ret);
            }//for
        } catch (GitAPIException ex) {
            Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IncorrectObjectTypeException ex) {
            Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RevisionSyntaxException ex) {
            Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return array;
    }//EoM
     
}//EoC