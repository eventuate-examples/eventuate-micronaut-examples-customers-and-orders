package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class CustomerBackendFactory {

  @Singleton
  public CustomerWorkflow customerWorkflow() {
    return new CustomerWorkflow();
  }

  @Singleton
  public AggregateRepository<Customer, CustomerCommand> customerRepository(EventuateAggregateStore eventStore) {
    return new AggregateRepository<>(Customer.class, eventStore);
  }
}


