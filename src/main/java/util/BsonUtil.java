package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

public class BsonUtil {
  
  static DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
  
  public static Document newDocument(String val) {
    Map<String, Object> m = new HashMap<>();
    m.put("a", val);
    m.put("tst", df.format(new Date()));
    return new Document(m);
  }
  
  public static Document newDocument(long id, String val) {
    Map<String, Object> m = new HashMap<>();
    m.put("_id", id);
    m.put("a", val);
    return new Document(m);
  }

  
}
