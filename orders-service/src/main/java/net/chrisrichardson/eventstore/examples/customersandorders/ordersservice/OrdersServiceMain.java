package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition
public class OrdersServiceMain {
  public static void main(String[] args) {
    Micronaut.run(OrdersServiceMain.class, args);
  }
}
