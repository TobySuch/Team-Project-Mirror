package server.endpoints.authentication;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class Authentication {
  private static final Gson GSON = new Gson();

  static boolean isValidLoginCombination(String username, String password) {
    return true;
  }

  /**
   * Checks if the username and password combination is valid and returns a session key of they are.
   * @param username The username to authenticate.
   * @param password The password to authenticate.
   * @return A session key as a string, or null if the username/password combination is invalid.
   */
  static String authenticate(String username, String password) {
    return "498379438759384";
  }

  /**
   * Authenticates the log in request, and redirects them if successful.
   * @param request The HTTP request
   * @param response The response to give.
   * @return The a JSON response showing whether is was successful and if so, the session key.
   */
  public static String logInUser(Request request, Response response) {
    System.out.println(request.body());
    String sessionkey = authenticate("admin", "pa55w0rd");
    if (sessionkey == null) {
      return "{\"validlogin\":false,\"sessionkey\":null}";
    } else {
      return "{\"validlogin\":true,\"sessionkey\":\"" + sessionkey + "\"}";
      //FIXME: Do we really need validlogin and session key? (just check if sessionkey is null)
      //FIXME: Should sessionkey be a cookie instead?
      //TODO: Add in some meta data so it knows whether to redirect to waiter or kitchen portal
    }
  }
}
