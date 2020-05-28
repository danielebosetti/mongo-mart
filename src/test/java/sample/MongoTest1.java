package sample;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.ReplaceOptions;
import util.BsonUtil;
import util.UtilMongoFactory;

public class MongoTest1 {
  
  public static final String COL_NAME = "col";

  @Test
  public void test() throws Exception {
    MongoCollection<Document> col = UtilMongoFactory.getTestColl(COL_NAME);
    long id = System.currentTimeMillis();

    Document doc = BsonUtil.newDocument(id, "b");
    Bson filter = new Document("_id", id);
    ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
    try {
      col.replaceOne(filter, doc, replaceOptions);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
  }
}
