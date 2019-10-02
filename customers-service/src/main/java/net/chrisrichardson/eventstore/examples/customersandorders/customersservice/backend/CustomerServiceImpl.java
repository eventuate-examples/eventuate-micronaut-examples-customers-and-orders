package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CustomerServiceImpl implements CustomerService {

  private AggregateRepository<Customer, CustomerCommand> customerRepository;

  @Inject
  public CustomerServiceImpl(AggregateRepository<Customer, CustomerCommand> customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public EntityWithIdAndVersion<Customer> createCustomer(String name, Money creditLimit) {
    return customerRepository.save(new CreateCustomerCommand(name, creditLimit));
  }

  @Override
  public EntityWithMetadata<Customer> findById(String customerId) {
    return customerRepository.find(customerId);
  }
}
