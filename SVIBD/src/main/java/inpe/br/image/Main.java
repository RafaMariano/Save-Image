package inpe.br.image;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Data;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import nom.tam.fits.Header;

public class Main {

	public static void main(String[] args) throws IOException, FitsException {
		//FileDirectory f = new FileDirectory("");
		//f.createDirectory();
	//	if (Files.createDirectories(Paths.get("/home/inpe/Documentos/10_11_2016/c2/v")) != null){}
	//	File file = new File("/home/inpe/Imagens/testkeys8.fits");
		//System.out.println(file.getAbsolutePath());
	Fits f = new Fits("/home/inpe/Imagens/testkeys.fits");

	
	//	System.out.println(f.getHDU(0).getHeader().getStringValue("name"));
//		f.getHDU(0).addValue("name", "testkey", "");
//		f.getHDU(0).addValue("hour", "11", "");
//		f.getHDU(0).addValue("minute", "16", "");
//		f.getHDU(0).addValue("second", "11", "");
//		f.getHDU(0).addValue("mili", "1111", "");
//		f.getHDU(0).addValue("day", "10", "");
//		f.getHDU(0).addValue("mounth", "11", "");
//		f.getHDU(0).addValue("year", "2016", "");
//		f.getHDU(0).addValue("period", "1", "");
//		f.getHDU(0).addValue("QUVI", "Q", "");
//		f.getHDU(0).addValue("type", "", "");

//	String a = f.getHDU(0).getHeader().getStringValue("name");
//	System.out.println(a);
	//f.getHDU(0).addValue("name", "testkey", "");
		
	//f.write(new File("/home/inpe/Imagens/testkeys2.fits"));	
		//System.out.println(f.);
		
		
		//File[] files = new File("/home/inpe/Imagens/").listFiles();
		
		//System.out.println(files.length +" Pronto");
		
	
}
}