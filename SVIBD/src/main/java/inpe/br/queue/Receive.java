package inpe.br.queue;


import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import inpe.br.image.FileDirectory;

public class Receive{
	private final static String TASK_QUEUE_NAME = "Fila";
	
	
	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {
		  

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    final Channel channel = connection.createChannel();
	    channel.basicQos(1);
	  
	    final Consumer consumer = new DefaultConsumer(channel) {
	    	FileDirectory fileDirectory = new FileDirectory("/home/inpe/Documentos/");
	    	  @Override
	    	  public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {	    	    	    		   
	    		 
	    		  try{
	    			  
	    		
	    			  fileDirectory.createDirectory(properties);
	    			  fileDirectory.createImage(body, properties);
//	    			  System.out.println(fileDirectory.getDirectory() + properties.getHeaders().get("day")+"_"+ properties.getHeaders().get("mounth")+"_"+properties.getHeaders().get("year")  + "/" + "cycle_"+properties.getHeaders().get("period") + "/" +
// 	         			properties.getHeaders().get("QUVI") + "/" + properties.getHeaders().get("name"));
	    			  channel.txSelect();
	    		  
	    	    } finally {
	    	 
	    	    	//  fileDirectory.saveBD(properties);
	    	    System.out.println("Receive");
	    	    }
					
	    	      System.out.println(" [x] Done");
	    	      channel.basicAck(envelope.getDeliveryTag(), false);
	    	      channel.txCommit();
	    	  }
	    	};
	    	boolean autoAck = false; // acknowledgment is covered below
	    	
	    	channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer);
	    	

	}
	
	

}
