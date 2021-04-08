package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.web.customers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.CustomerView;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend.CustomerViewRepository;

import javax.inject.Inject;

@Controller
public class CustomerOrderHistoryController {

  @Inject
  private CustomerViewRepository customerViewRepository;

  @Get("/customers/{customerId}")
  public HttpResponse<CustomerView> getCustomer(String customerId) {
    return customerViewRepository.findById(customerId).map(HttpResponse::ok).orElseGet(HttpResponse::notFound);
  }
}
