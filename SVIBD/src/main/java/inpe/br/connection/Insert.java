package inpe.br.connection;

import org.bson.Document;

import inpe.br.image.FileDirectory;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static java.util.Arrays.asList;

import java.io.IOException;

public class Insert{
		
	
public void addBD(Fits fits, String directory) throws ParseException, FitsException, IOException{
	
		ConnectDB con = new ConnectDB();
		
		 con.getDB().getCollection("dateImage").insertOne(
		         new Document("Name",fits.getHDU(0).getHeader().getStringValue("name"))
		                 .append("Directory", directory)
		                 .append("Date", new Document()
		                                 .append("Day", fits.getHDU(0).getHeader().getStringValue("day") )
		                                 .append("Mounth", fits.getHDU(0).getHeader().getStringValue("mounth"))
		                                 .append("Year", fits.getHDU(0).getHeader().getStringValue("year")))
		                 .append("Time", new Document()
		                		 .append("Hour", fits.getHDU(0).getHeader().getStringValue("hour"))
		                		 .append("Minute", fits.getHDU(0).getHeader().getStringValue("minute"))
		                		 .append("Second", fits.getHDU(0).getHeader().getStringValue("second")))
		                 		// .append("Mili", fits.getHDU(0).getHeader().findCard("mili")))
		                 .append("Period", new Document()
		                		 .append("Q", fits.getHDU(0).getHeader().getStringValue("QUVI"))
		                		 .append("I", fits.getHDU(0).getHeader().getStringValue("QUVI"))
		                		 .append("U",fits.getHDU(0).getHeader().getStringValue("QUVI"))
		                		 .append("V", fits.getHDU(0).getHeader().getStringValue("QUVI")))
		                 
		                      );
		 con.getMC().close();

 } 

}
