package inpe.br.image;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import com.rabbitmq.client.AMQP;

import inpe.br.connection.Insert;
import inpe.br.image.Image;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;


public class FileDirectory {
	
	private String directory;
	private File file;
	private Insert insert;
	
	public FileDirectory(String directory){
		this.file = new File(directory);
		this.directory = directory;
		this.insert = new Insert();
	}
	
	public void saveBD(AMQP.BasicProperties properties) throws IOException{
		
		try {
			insert.addBD(new Fits(directory + properties.getHeaders().get("day")+"_"+ properties.getHeaders().get("mounth")+"_"+properties.getHeaders().get("year")  + "/" + "cycle_"+properties.getHeaders().get("period") + "/" +
	         			properties.getHeaders().get("QUVI") + "/" + properties.getHeaders().get("name")),
					directory + properties.getHeaders().get("day") + "/" 
							+ properties.getHeaders().get("period")+ "/" + properties.getHeaders().get("QUVI"));
		} catch (ParseException | FitsException e) {
			//realizar tratamento
		
		}
	}
	public String getDirectory(){
		return this.directory;
	}
	
	
	public void createDirectory(AMQP.BasicProperties properties) throws IOException{
		Files.createDirectories(Paths.get(directory + properties.getHeaders().get("day")+"_"+ properties.getHeaders().get("mounth")+"_"+properties.getHeaders().get("year")  + "/" + "cycle_"+properties.getHeaders().get("period") + "/" +
      			properties.getHeaders().get("QUVI")));		
		
	}
	
	public void createImage(byte[] body, AMQP.BasicProperties properties) throws IOException{
		  try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
 	         byteArrayOutputStream.write(body);
 	           
 	         	try (FileOutputStream fileOutputStream = new FileOutputStream(directory + properties.getHeaders().get("day")+"_"+ properties.getHeaders().get("mounth")+"_"+properties.getHeaders().get("year")  + "/" + "cycle_"+properties.getHeaders().get("period") + "/" +
 	         			properties.getHeaders().get("QUVI") + "/" + properties.getHeaders().get("name"))) {
 	            	byteArrayOutputStream.writeTo(fileOutputStream);
 	            }
		  }
	}
	
	//deprecated
	public File[] getFileDirectory(){	
		return this.file.listFiles();
	}
	
	public byte[] getBytesFile(String namefile) throws IOException{
		//verificar se a String file é um arquivo, caso não, retornar um erro
		return Files.readAllBytes(new File("/home/inpe/Imagens/" + namefile).toPath());
	}
	public byte[] getBytesFile(File namefile) throws IOException{
		return Files.readAllBytes(namefile.toPath());
	}
}
