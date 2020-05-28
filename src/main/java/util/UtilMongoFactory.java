package util;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class UtilMongoFactory {
  public static final String MONGO_URL = 
      "mongodb://localhost:30001,localhost:30002,localhost:30003,localhost:30004,localhost:30005";

  private static MongoClient instance;
  
  public static synchronized MongoClient getMongoClientInstance() {
    if(instance==null) {
      instance = MongoClients.create(MONGO_URL);
    }
    return instance;
  }
  public static MongoDatabase getDatabase() {
    return getDatabase("mongomart");
  }
  public static MongoDatabase getDatabase(String name) {
    return getMongoClientInstance().getDatabase(name);
  }
  public static MongoCollection<Document> getTestColl(String name) {
    return getDatabase("test").getCollection(name);
  }

}
