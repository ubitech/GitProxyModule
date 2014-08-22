package eu.ubitech.ubicropper.gitproxy.git;

/**
 *
 * @author Panagiotis Gouvas
 */
public class ThreadsGroupFactory {
    private static volatile ThreadsGroup instance = null;
    
    public ThreadsGroupFactory() { 
    }//EoC
    
    public static ThreadsGroup getThreadGroup() {
        if (instance == null) {
            synchronized (ThreadsGroupFactory.class) {
                if (instance == null) {                   
                    instance = new ThreadsGroup();                                          
                }
            }
        }
        return instance;
    }//EoM    
}//EoC