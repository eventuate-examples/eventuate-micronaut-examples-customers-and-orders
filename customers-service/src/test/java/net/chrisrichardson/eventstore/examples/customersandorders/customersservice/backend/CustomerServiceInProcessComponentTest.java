package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend;

import com.jayway.restassured.RestAssured;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerRequest;
import org.junit.jupiter.api.Test;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@MicronautTest
public class CustomerServiceInProcessComponentTest {

  private String baseUrl(int port, String path) {
    return "http://localhost:" + port + path;
  }

  @Test
  public void shouldCreateOrder() {
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer.class);

    int port = embeddedServer.getPort();

    String postUrl = baseUrl(port,"/customers");

    String customerId = RestAssured.given().
      body(new CreateCustomerRequest("John Doe", new Money(1234))).
            contentType("application/json").
    when().
           post(postUrl).
    then().
           statusCode(200).
    extract().
        path("customerId");

    assertNotNull(customerId);


    Integer creditLimit = RestAssured.given().
            when().
            get(postUrl + "/" + customerId).
            then().
            statusCode(200).
            extract().
            path("creditLimit.amount");

    assertEquals(new Integer(1234), creditLimit);

  }
}
