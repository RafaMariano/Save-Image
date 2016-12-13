package inpe.br.send;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import inpe.br.queue.Send;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

public class DataDirectory {
	private ArrayList<String> queue;
	
	public DataDirectory(){
		this.queue = new ArrayList<>();
		
	}
	
	
	public void putQueue(String file) {
			this.queue.add(file);	
	}
	
	
		
}
