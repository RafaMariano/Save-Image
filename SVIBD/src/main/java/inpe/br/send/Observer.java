package inpe.br.send;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import java.util.concurrent.TimeoutException;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import nom.tam.fits.FitsException;

public class Observer {
	
	
    private final WatchService watcher;
    private final boolean recursive;
    private boolean trace = false;
    private BidiMap<WatchKey, Path> map;
    private WatchKey key;
    private Find find;


    
    public Observer(Path dir, boolean recursive) throws IOException, InterruptedException, TimeoutException, FitsException {
    	this.find = new Find();
    	this.watcher = FileSystems.getDefault().newWatchService();
        this.map = new DualHashBidiMap<WatchKey, Path>();
        this.recursive = recursive;
        
    	//Files.list(dir).
//    	if(Files.list(dir).count() != 0){
//    		 Find oldDirectory = new Find(dir);
//    		 oldDirectory.walkDirectory();
//    		 oldDirectory = null;
//    	}
    	
    	
        	
        register(dir);
        // enable trace after initial registration
        this.trace = true;
       
       
    }
    
	 @SuppressWarnings("unchecked")
	 private static <T> WatchEvent<T> cast(WatchEvent<?> event) {
	        return (WatchEvent<T>)event;
	    }
  
    private void registerAll(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
            {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
    
    private void register(Path dir) throws IOException {
    	
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        if (trace) {
            Path prev = map.get(key);
            if (prev == null) { 
                System.out.format("register: %s\n", dir);
            } else {
                if (!dir.equals(prev)) {             
                    System.out.format("update: %s -> %s\n", prev, dir);
                }
            }
        }
        map.put(key, dir);
    }

    
   public void processEvents() throws IOException {
     
	   while(true){

            try {             	
            	//Espera receber uma notificação de que a pasta foi modificada
                key = watcher.take();
                
            } catch (InterruptedException x) {
            	System.out.println("Erro: " + x);
            }
            
            //Pega o caminho do arquivo que foi criado
            Path dir = map.get(key); 
            
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }
            
          
            
            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                	System.out.println("Ramon é da zoeira");
                    continue;
                }
                
                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);
               

                System.out.format("%s: %s\n", event.kind().name(), child);

                if (kind == ENTRY_DELETE && map.containsValue(child)){

                	  map.removeValue(child);
                	
                }
               
                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                
                	find.add(child.toFile().getParentFile().toPath());
                	
                    if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                       registerAll(child);
            
                       //registerAll
					}
                }
            }
            key.reset();
        }
    }

    static void usage() {
        System.err.println("usage: java WatchDir [-r] dir");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException, FitsException {

        // register directory and process its events
        Path dir = Paths.get("/home/inpe/Imagens/");
        new Observer(dir, true).processEvents();
    }
}
