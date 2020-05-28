package sample;

import org.bson.Document;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongodb.client.MongoCollection;
import util.UtilMongoFactory;

public class MongoTest2 {
  private static final Logger logger = LoggerFactory.getLogger(MongoTest2.class);

  MongoCollection<Document> col = UtilMongoFactory.getTestColl(MongoTest1.COL_NAME);

  @Test
  public void test() throws Exception {
    try {
      for(Document e:col.find()) {
        logger.info("e={}", e);
      }
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
  }
}
