package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import io.micronaut.context.annotation.Value;
import io.micronaut.test.annotation.MicronautTest;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@MicronautTest
public class CustomerServiceProxyIntegrationTest {

  @Inject
  private CustomerServiceProxy customerServiceProxy;

  @Inject
  private RestTemplate restTemplate;

  @Value("${customer.service.root.url}")
  private String customerServiceRootUrl;

  @Test
  public void shouldVerifyExistingCustomer() {
    ResponseEntity<CreateCustomerResponse> response = restTemplate.postForEntity(customerServiceRootUrl,
            new CreateCustomerRequest("Fred", new Money(123)), CreateCustomerResponse.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    customerServiceProxy.verifyCustomerCustomerId(response.getBody().getCustomerId());
  }

  @Test
  public void shouldRejectNonExistentCustomer() {
    Assertions.assertThrows(CustomerNotFoundException.class, () -> customerServiceProxy.verifyCustomerCustomerId("1223232-none"));
  }

}
