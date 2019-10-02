package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.web;

import io.eventuate.EntityNotFoundException;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.CreateCustomerResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.customerscommon.GetCustomerResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend.Customer;
import net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend.CustomerService;

import javax.inject.Inject;

@Controller
public class CustomerController {

  @Inject
  private CustomerService customerService;

  @Post(value = "/customers")
  public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
    EntityWithIdAndVersion<Customer> ewidv = customerService.createCustomer(createCustomerRequest.getName(), createCustomerRequest.getCreditLimit());
    return new CreateCustomerResponse(ewidv.getEntityId());
  }

  @Get("/customers/{customerId}")
  public HttpResponse<GetCustomerResponse> getCustomer(String customerId) {
    EntityWithMetadata<Customer> customerWithMetadata;
    try {
      customerWithMetadata = customerService.findById(customerId);
    } catch (EntityNotFoundException e) {
      return HttpResponse.notFound();
    }

    Customer customer = customerWithMetadata.getEntity();
    GetCustomerResponse response =
            new GetCustomerResponse(customerWithMetadata.getEntityIdAndVersion().getEntityId(), customer.getCreditLimit(),
                    customer.availableCredit());

    return HttpResponse.ok(response);
  }
}
