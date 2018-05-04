package io.shiftleft.jackspoilt;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

import java.io.IOException;
import java.util.Collection;
import spark.Request;

public class App {

  private static final Logger log = LoggerFactory.getLogger(App.class);

  /*
  Trigger Gadget Chain
   */
  private static ObjectMapper deserializer = new ObjectMapper().enableDefaultTyping();
  private static ObjectMapper serializer = new ObjectMapper();
  private static AccountStore accounts = new AccountStore();

  public static void main(String[] args) {
    port(8888);

    get("/accounts", (request, response) -> {
      Collection<Account> res = accounts.list();
      log.info("/accounts -> {}", res);
      return serializer.writeValueAsString(res);
    });

    post("/accounts", (request, response) -> {
      log.info("/accounts -> {}", request.body());
      Account account = deserialize(request);
      if (account != null) {
        Account res = accounts.add(account);
        response.status(201);
        return serializer.writeValueAsString(res);
      } else {
        response.status(400);
        return "Invalid content";
      }
    });


  }

  private static Account deserialize(Request request)
      throws IOException, JsonParseException, JsonMappingException {
    try {
      return deserializer.readValue(request.body(), Account.class);
    } catch (Exception any) {
      log.warn("Unexpected exception deserializing content: {}", any.getClass());
      return null;
    }
  }
}
