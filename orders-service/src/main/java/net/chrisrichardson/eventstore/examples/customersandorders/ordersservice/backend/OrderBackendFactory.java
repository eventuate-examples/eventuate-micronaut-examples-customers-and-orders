package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class OrderBackendFactory {

  @Singleton
  public OrderWorkflow orderWorkflow() {
    return new OrderWorkflow();
  }

  @Singleton
  public AggregateRepository<Order, OrderCommand> orderRepository(EventuateAggregateStore eventStore) {
    return new AggregateRepository<>(Order.class, eventStore);
  }

}


