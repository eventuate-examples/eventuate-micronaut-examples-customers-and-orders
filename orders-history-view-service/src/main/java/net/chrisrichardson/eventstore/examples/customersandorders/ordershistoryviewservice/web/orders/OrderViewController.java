package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.web.orders;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.OrderView;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend.OrderViewRepository;
import javax.inject.Inject;

@Controller
public class OrderViewController {

  @Inject
  private OrderViewRepository orderViewRepository;

  @Get("/orders/{orderId}")
  public HttpResponse<OrderView> getOrder(String orderId) {

    OrderView ov = orderViewRepository.findOne(orderId);
    if (ov == null) {
      return HttpResponse.notFound();
    } else {
      return HttpResponse.ok(ov);
    }
  }
}
