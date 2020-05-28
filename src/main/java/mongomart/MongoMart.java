package mongomart;

import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;
import java.io.IOException;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import mongomart.controller.AdminController;
import mongomart.controller.CartController;
import mongomart.controller.LocationsController;
import mongomart.controller.StoreController;
import util.UtilMongoFactory;

/**
 * Main MongoMart class
 *
 * To run: Ensure the dataset from data/ has been imported to your MongoDB instance (instructions
 * located in README.md) Run the main method below Open http://localhost:8080
 */
public class MongoMart {
  
  public static final int HTTP_PORT = 8080;

  public static void main(String[] args) throws IOException {
    new MongoMart();
  }
  /**
   * Create an instance of MongoMart
   *
   * @param mongoURIString
   * @throws IOException
   */

  public MongoMart() throws IOException {
    /**
     * TODO-lab1
     *
     * LAB #1: Create a connection to your MongoDB instance, assign the "mongomart" database to the
     * itemDatabase variable
     *
     * HINT: You'll need to create a MongoClient object first, the "mongoURIString" may also be used
     *
     */
    final MongoDatabase itemDatabase = UtilMongoFactory.getDatabase();

    /**
     * TODO-lab1 Replace all code above
     */

    // Freemarker configuration
    final Configuration cfg = createFreemarkerConfiguration();

    port(HTTP_PORT);
    staticFileLocation("/assets");

    // Start controllers
    AdminController adminController = new AdminController(cfg, itemDatabase);
    StoreController storeController = new StoreController(cfg, itemDatabase);
    CartController cartController = new CartController(cfg, itemDatabase);
    LocationsController locationsController = new LocationsController(cfg, itemDatabase);
  }

  /**
   *
   * @return
   */
  private Configuration createFreemarkerConfiguration() {
    Configuration retVal = new Configuration();
    retVal.setClassForTemplateLoading(MongoMart.class, "/freemarker");
    return retVal;
  }
}
