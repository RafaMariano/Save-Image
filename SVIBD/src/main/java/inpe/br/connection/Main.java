package inpe.br.connection;

import java.io.IOException;
import java.text.ParseException;

import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

public class Main {

	public static void main(String[] args) throws ParseException, FitsException, IOException{
		Insert insert = new Insert();
		
		insert.addBD(new Fits("/home/inpe/Imagens/testkeys.fits"), "/home/inpe/Imagens/testkeys.fits");
		
	}
	
}
