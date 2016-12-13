package inpe.br.send;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchKey;

import org.apache.commons.collections4.BidiMap;


public class Find {
	
	private Finder finder;
	
	public Find(){
		finder = new Finder();
	}

	private class Finder extends SimpleFileVisitor<Path> {
		
		private BidiMap<WatchKey, Path> map;
		
		
	@Override
     public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        
//		if (map.containsValue(dir)){ 
//			//map.remove(dir);
//        	}
		System.out.println(dir.getFileName());
        return CONTINUE;
         
     }
	
	public void setMap(BidiMap<WatchKey, Path> map){
		this.map = map;
	}
	}
	
	public void remove(BidiMap<WatchKey, Path> map, Path directory) throws IOException{
		finder.setMap(map);
		Files.walkFileTree(directory, finder);
	}
}
