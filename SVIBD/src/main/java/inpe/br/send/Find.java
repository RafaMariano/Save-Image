package inpe.br.send;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchKey;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.TimeoutException;

import org.apache.commons.collections4.BidiMap;

import inpe.br.queue.Send;
import nom.tam.fits.FitsException;


public class Find {
	
	private Finder finder;
	
	public Find() throws IOException, TimeoutException{
		finder = new Finder();
	}

	private class Finder extends SimpleFileVisitor<Path> {
		
		private DataDirectory data;
		private BidiMap<WatchKey, Path> map;
		
		public Finder() throws IOException, TimeoutException{
			this.data = new DataDirectory();
		}
		
		 @Override
		    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
		        try {
					data.saveBD(file);
				} catch (TimeoutException | FitsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        return CONTINUE;
		    }
	@Override
     public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        

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
	public void add(Path dir) throws IOException{
		Files.walkFileTree(dir, finder);
	}
}
