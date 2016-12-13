package inpe.br.send;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import static java.nio.file.FileVisitOption.*;
import java.util.*;
import java.util.concurrent.TimeoutException;

import org.apache.commons.collections4.BidiMap;

import nom.tam.fits.FitsException;


public class OldFind {
	
	private Finder finder;
	
	public OldFind(){
		this.finder = new Finder();
	}
	
	 private class Finder extends SimpleFileVisitor<Path> {
		 private BidiMap<WatchKey, Path> map;
	        //private final PathMatcher matcher;
	      //  private int numMatches;
	      //  private final String pattern  = "**";
	      //  private DataDirectory data;
	        
//	       public Finder() throws IOException, TimeoutException {
//	    	   	
//	            this.matcher = FileSystems.getDefault().getPathMatcher("glob:" + this.pattern);
//	            this.numMatches = 0;
//	       }

//	       private void find(Path file) throws TimeoutException, FitsException, IOException {
//	    	  
////	            if (file.getFileName().toString() != null && matcher.matches(file.getFileName())) {
////	            	data.putQueue(file.toAbsolutePath().toString());
////	            }
//	    	   System.out.println(file.getFileName());
//	        }

//	      //Recursivamente, ele pega os arquivos a partir do diretório passado para o find, para assim
//	      //enviar cada imagem para fila
//	        @Override
//	        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//	           try {
//				find(file);
//			} catch (TimeoutException | FitsException e) {
//				
//				e.printStackTrace();
//			}
//	            return CONTINUE;
//	        }
	       @Override
	       public FileVisitResult postVisitDirectory(Path dir,
	                                             IOException exc) {
	          System.out.println(dir.getFileName());
	           return CONTINUE;
	       }
	      
	        //Recursivamente, ele pega cada diretório a parti do diretória passado para o find, para assim
	        //ter uma referência de onde está a imagem
//	        @Override
//	        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//	            try {
//				find(dir);
//				} catch (TimeoutException | FitsException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	            return CONTINUE;
//	        }
	        
	        //Caso de algum problema, irá cair nesse método. 
	        //Pode ser usado para verificar se a imagem é do tipo FITS ou se está completa(?)
	        @Override
	        public FileVisitResult visitFileFailed(Path file, IOException exc) {
	            System.err.println(exc + "  " + file.getFileName());
	            return CONTINUE;
	        }
	        public void setMap(BidiMap<WatchKey, Path> map){
	    		this.map = map;
	    	}
	    	
	    }

//    static void usage() {
//        System.err.println("java Find <path>" +
//            " -name \"<glob_pattern>\"");
//        System.exit(-1);
//    }
    
    public void deleteFolder(Path directory) throws IOException, TimeoutException{
    	 Files.walkFileTree(directory, new Finder());
    }
    public void remove(BidiMap<WatchKey, Path> map, Path directory) throws IOException{
		finder.setMap(map);
		Files.walkFileTree(directory, finder);
	}
    
}