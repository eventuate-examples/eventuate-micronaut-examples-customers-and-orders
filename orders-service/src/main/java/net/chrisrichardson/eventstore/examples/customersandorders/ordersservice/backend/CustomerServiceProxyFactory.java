package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import io.micronaut.context.annotation.Factory;
import org.springframework.web.client.RestTemplate;

import javax.inject.Singleton;

@Factory
public class CustomerServiceProxyFactory {
  @Singleton
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
