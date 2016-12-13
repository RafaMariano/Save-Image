package inpe.br.queue;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import inpe.br.image.FileDirectory;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Send {

	private final static String QUEUE_NAME = "Fila";
	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;

	public Send() throws IOException, TimeoutException{
		
		this.factory = new ConnectionFactory();
		this.factory.setHost("localhost");
		this.connection = factory.newConnection();
		this.channel = connection.createChannel();  
	}

	public void sendMQ(Map<String, Object> messageProps, String dir) throws TimeoutException {

		File file = new File(dir);

		try{
			
			channel.txSelect();

			AMQP.BasicProperties.Builder basicProperties = new AMQP.BasicProperties.Builder();
			basicProperties.contentType("").deliveryMode(1)
			.priority(1).headers(messageProps);

			channel.basicPublish("", QUEUE_NAME, basicProperties.build(), Files.readAllBytes(file.toPath()));
			channel.txCommit();
			System.out.println(" [x] Sent message number: ");
			channel.close();
			
		}catch(IOException e){
			
			System.out.println("Error: " + e);
			
			if (channel != null){
				try{
					channel.txRollback();
				}catch(IOException f){
					System.out.println("Error: " + f);
				}
			}
		}finally {
			
			if (connection != null){
				try{
					connection.close();

				} catch(IOException e){
					System.out.println("Error: " + e);
				}
			}
		}
	    
	}
}
