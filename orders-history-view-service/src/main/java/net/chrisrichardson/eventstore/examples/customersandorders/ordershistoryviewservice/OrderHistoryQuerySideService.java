package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition
public class OrderHistoryQuerySideService {
  public static void main(String[] args) {
    Micronaut.run(OrderHistoryQuerySideService.class);
  }
}
