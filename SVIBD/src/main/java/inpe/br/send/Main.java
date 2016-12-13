package inpe.br.send;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

public class Main {

//	public static String se(File directory){
//	
//		if (directory.isDirectory() != true){
//			return 
//		}
//		
//		return se(new File(directory.getAbsolutePath()));
//		}
	
	public static void main(String[] args) throws FitsException, IOException, TimeoutException {

		
		
//		Fits fits = new Fits("/home/inpe/Imagens/testkey.fits");
//		
//		System.out.println(fits.getHDU(1).getHeader().getNumberOfCards());
//	File a = new File("/home/inpe/Imagens");
		OldFind a = new OldFind();
		a.deleteFolder(Paths.get("/home/inpe/Imagens/"));
	}

}
