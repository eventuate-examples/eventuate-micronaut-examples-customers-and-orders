package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;


import io.eventuate.*;
import net.chrisrichardson.eventstore.examples.customersandorders.common.customer.CustomerCreditLimitExceededEvent;
import net.chrisrichardson.eventstore.examples.customersandorders.common.customer.CustomerCreditReservedEvent;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id="orderWorkflow")
public class OrderWorkflow implements Subscriber {

  @EventHandlerMethod
  public CompletableFuture<EntityWithIdAndVersion<Order>>
        creditLimitReserved(EventHandlerContext<CustomerCreditReservedEvent> ctx) {
    System.out.println(String.format("creditLimitReserved invoked %s", ctx));

    String orderId = ctx.getEvent().getOrderId();

    return ctx.update(Order.class, orderId, new ApproveOrderCommand());
  }

  @EventHandlerMethod
  public CompletableFuture<EntityWithIdAndVersion<Order>>
        creditLimitExceeded(EventHandlerContext<CustomerCreditLimitExceededEvent> ctx) {
    System.out.println(String.format("creditLimitExceeded invoked %s", ctx));

    String orderId = ctx.getEvent().getOrderId();

    return ctx.update(Order.class, orderId, new RejectOrderCommand());
  }

}
