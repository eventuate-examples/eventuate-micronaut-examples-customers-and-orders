package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class OrderHistoryViewBackendFactory {

  @Singleton
  public OrderHistoryViewWorkflow orderHistoryViewWorkflow(OrderHistoryViewService service) {
    return new OrderHistoryViewWorkflow(service);
  }
}
