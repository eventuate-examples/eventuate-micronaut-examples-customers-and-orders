package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.web;

import io.eventuate.EntityWithIdAndVersion;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.CreateOrderRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.CreateOrderResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend.CustomerNotFoundException;
import net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend.Order;
import net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend.OrderService;

import javax.inject.Inject;

@Controller
public class OrderController {

    @Inject
    private OrderService orderService;

    @Post(value = "/orders")
    public HttpResponse<CreateOrderResponse> createOrder(CreateOrderRequest createOrderRequest) {
        try {
            EntityWithIdAndVersion<Order> order =
                    orderService.createOrder(createOrderRequest.getCustomerId(), createOrderRequest.getOrderTotal());
            return HttpResponse.ok(new CreateOrderResponse(order.getEntityId()));
        } catch (CustomerNotFoundException e) {
            return HttpResponse.badRequest();
        }
    }
}
