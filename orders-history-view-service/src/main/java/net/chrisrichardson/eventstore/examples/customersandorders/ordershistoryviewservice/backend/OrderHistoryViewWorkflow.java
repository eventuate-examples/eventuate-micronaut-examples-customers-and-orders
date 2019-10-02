package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import io.eventuate.Subscriber;
import net.chrisrichardson.eventstore.examples.customersandorders.common.customer.CustomerCreatedEvent;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderApprovedEvent;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderCreatedEvent;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderRejectedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EventSubscriber(id = "orderHistoryWorkflow")
public class OrderHistoryViewWorkflow implements Subscriber {
  private Logger logger = LoggerFactory.getLogger(getClass());

  private OrderHistoryViewService orderHistoryViewService;

  public OrderHistoryViewWorkflow(OrderHistoryViewService orderHistoryViewService) {
    this.orderHistoryViewService = orderHistoryViewService;
  }

  @EventHandlerMethod
  public void createCustomer(DispatchedEvent<CustomerCreatedEvent> de) {
    System.out.println(String.format("createCustomer invoked %s", de));

    String customerId = de.getEntityId();
    orderHistoryViewService.createCustomer(customerId, de.getEvent().getName(),
            de.getEvent().getCreditLimit());
  }

  @EventHandlerMethod
  public void createOrder(DispatchedEvent<OrderCreatedEvent> de) {
    System.out.println(String.format("createOrder invoked %s", de));

    String customerId = de.getEvent().getCustomerId();
    String orderId = de.getEntityId();
    Money orderTotal = de.getEvent().getOrderTotal();
    orderHistoryViewService.addOrder(customerId, orderId, orderTotal);
  }

  @EventHandlerMethod
  public void orderApproved(DispatchedEvent<OrderApprovedEvent> de) {
    System.out.println(String.format("orderApproved invoked %s", de));

    String customerId = de.getEvent().getCustomerId();
    String orderId = de.getEntityId();
    orderHistoryViewService.approveOrder(customerId, orderId);  }

  @EventHandlerMethod
  public void orderRejected(DispatchedEvent<OrderRejectedEvent> de) {
    System.out.println(String.format("orderRejected invoked %s", de));

    String customerId = de.getEvent().getCustomerId();
    String orderId = de.getEntityId();
    orderHistoryViewService.rejectOrder(customerId, orderId);
  }


}
