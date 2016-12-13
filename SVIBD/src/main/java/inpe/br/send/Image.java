package inpe.br.send;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

public class Image {
		private static Map messageProps;
	
public static Map<String, Object> setData(String fileDirectory) throws FitsException, IOException{
		
		Fits fits = new Fits(fileDirectory);
		messageProps = new HashMap<String, Object>();
		
		messageProps.put("name", fits.getHDU(0).getHeader().getStringValue("name"));
		messageProps.put("hour",fits.getHDU(0).getHeader().getStringValue("hour"));
		messageProps.put("minute",fits.getHDU(0).getHeader().getStringValue("minute"));
		messageProps.put("second", fits.getHDU(0).getHeader().getStringValue("second"));
		messageProps.put("mili", fits.getHDU(0).getHeader().getStringValue("mili"));
		messageProps.put("day", fits.getHDU(0).getHeader().getStringValue("day"));
		messageProps.put("mounth", fits.getHDU(0).getHeader().getStringValue("mounth"));
		messageProps.put("year", fits.getHDU(0).getHeader().getStringValue("year"));
		messageProps.put("period", fits.getHDU(0).getHeader().getStringValue("period"));
		messageProps.put("QUVI", fits.getHDU(0).getHeader().getStringValue("QUVI"));
		
		fits.close();
		return messageProps;
	}

}
