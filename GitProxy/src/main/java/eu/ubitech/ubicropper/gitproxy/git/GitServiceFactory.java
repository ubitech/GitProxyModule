package eu.ubitech.ubicropper.gitproxy.git;

/**
 *
 * @author Panagiotis Gouvas
 */
public class GitServiceFactory {
   private static volatile GitService instance = null;
    
    private GitServiceFactory() { 
    }//EoC
    
    public static GitService getInstance() {
        if (instance == null) {
            synchronized (GitServiceFactory.class) {
                if (instance == null) {
                    instance = new GitService();
                }
            }
        }
        return instance;
    }//EoM
}