package client;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import util.UtilMongoFactory;

public class ItemRead {
  private static final Logger logger = LoggerFactory.getLogger(ItemRead.class);
  public static void main(String[] args) throws InterruptedException {
    new ItemRead().execute();
  }

  private void execute() throws InterruptedException {
    new Thread(()->go()).start();
  }

  private static void go() {
    MongoDatabase database = UtilMongoFactory.getDatabase();
    MongoCollection<Document> col = database.getCollection("zip");
    FindIterable<Document> elems = col.find(new Document("_id", "01005"));
    for (Document e:elems) {
      logger.info("e={}", e);
    }
  }
}
