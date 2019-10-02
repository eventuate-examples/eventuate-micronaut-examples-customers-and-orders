package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

import javax.inject.Inject;

public class OrderServiceImpl implements OrderService {

  @Inject
  private AggregateRepository<Order, OrderCommand> orderRepository;

  @Inject
  private CustomerService customerService;

  @Override
  public EntityWithIdAndVersion<Order> createOrder(String customerId, Money orderTotal) {
    customerService.verifyCustomerCustomerId(customerId);
    return orderRepository.save(new CreateOrderCommand(customerId, orderTotal));
  }
}
