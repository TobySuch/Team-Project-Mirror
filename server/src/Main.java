import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import endpoints.authentication.Authentication;
import endpoints.customer.Menu;
import endpoints.order.Orders;
import endpoints.waiter.Tables;

public class Main {
  /**
   * Main method sets up the api end points.
   * @param args Standard for a Java program, any launch arguments are passed onto the function.
   */
  public static void main(String[] args) {
    staticFileLocation("static"); // Lets spark know where the static files are

    // End points
    get("/api/menu", (req, res) -> Menu.getMenu());
    get("/api/tables", Tables::getTables);
    post("/api/login", Authentication::logInUser);
    post("/api/order", Orders::getOrder);
    post("/api/addToOrder", Orders::addOrderMenuItem);
    post("/api/changeOrderStatus", Orders::changeOrderStatus);

    System.out.println("Visit: http://localhost:4567");
  }
}
