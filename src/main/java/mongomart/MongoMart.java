package mongomart;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import mongomart.controller.AdminController;
import mongomart.controller.CartController;
import mongomart.controller.LocationsController;
import mongomart.controller.StoreController;
import java.io.IOException;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

/**
 * Main MongoMart class
 *
 * To run: Ensure the dataset from data/ has been imported to your MongoDB instance (instructions
 * located in README.md) Run the main method below Open http://localhost:8080
 */
public class MongoMart {
  public static final int HTTP_PORT = 8080; // HTTP port to listen for requests on

  public static void main(String[] args) throws IOException {
    new MongoMart("mongodb://localhost:30000,localhost:30001,localhost:30002");
  }
  /**
   * Create an instance of MongoMart
   *
   * @param mongoURIString
   * @throws IOException
   */

  public MongoMart(String mongoURIString) throws IOException {
    /**
     * TODO-lab1
     *
     * LAB #1: Create a connection to your MongoDB instance, assign the "mongomart" database to the
     * itemDatabase variable
     *
     * HINT: You'll need to create a MongoClient object first, the "mongoURIString" may also be used
     *
     */
    MongoClient client = MongoClients.create(mongoURIString);
    final MongoDatabase itemDatabase = client.getDatabase("mongomart");

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
