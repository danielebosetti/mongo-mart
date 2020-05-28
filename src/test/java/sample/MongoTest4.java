package sample;

import org.bson.Document;
import org.junit.Test;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import util.BsonUtil;
import util.UtilMongoFactory;

public class MongoTest4 {
  
  @Test
  public void test() throws Exception {
    MongoCollection<Document> col = UtilMongoFactory.getTestColl(MongoTest1.COL_NAME);
    Document doc = BsonUtil.newDocument("asd");
    try {
      col = col.withWriteConcern(WriteConcern.MAJORITY);
      col.insertOne(doc);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
  }
}
