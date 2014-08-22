package eu.ubitech.ubicropper.gitproxy.git;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author eleni
 */
public class ThreadsGroup {
    
    private ConcurrentHashMap threadmap = new ConcurrentHashMap();
    
    public synchronized ConcurrentHashMap  getThreadMap(){
        return this.threadmap;    
    }
    
    public synchronized void  updateThreadMap(ConcurrentHashMap map){
        this.threadmap = map;
    }
    
    public synchronized void  addToThreadMap(String provinceid, GitService gitservice){
        this.threadmap.put(provinceid, gitservice);
    }
    
    public synchronized void  removeFromThreadMap(String provinceid){
        this.threadmap.remove(provinceid);
    }
    
    
    
}//
