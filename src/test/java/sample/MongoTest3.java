package sample;

import org.bson.Document;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import util.BsonUtil;
import util.UtilMongoFactory;

public class MongoTest3 {
  private static final Logger logger = LoggerFactory.getLogger(MongoTest3.class);
  MongoCollection<Document> col = UtilMongoFactory.getTestColl(MongoTest1.COL_NAME);
  
  @Test
  public void test1() throws Exception {
    try {
      Document e = col.find().sort(Sorts.descending("_id")).limit(1).first();
      logger.info("e={}", e);
      long nextId = e==null ? 0 : e.getLong("_id")+1;
      Document doc = BsonUtil.newDocument(nextId, "asd");
      logger.info("insert={}", doc);
      col.insertOne(doc);
    } catch (Exception e) {
      logger.info("err: {}", e.getMessage());
    }
  }
  @Test
  public void test2() throws Exception {
    try {
      Document res = col.findOneAndUpdate(Filters.and(
          Filters.gt("_id", 3),
          Filters.eq("xx", null)
          ), Updates.set("xx", "some-value"));
      logger.info("update-res={}", res);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
  }
}
