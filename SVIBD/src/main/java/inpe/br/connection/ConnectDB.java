package inpe.br.connection;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConnectDB {
	
	private MongoClient mongoClient; 
	private MongoDatabase db;
	
	public ConnectDB(){
		this.mongoClient = new MongoClient();
		this.db = mongoClient.getDatabase("ImageDB");
	}

	public MongoDatabase getDB(){
		return this.db;
	}
	public MongoClient getMC(){
		return this.mongoClient;
	}
	
	
}
