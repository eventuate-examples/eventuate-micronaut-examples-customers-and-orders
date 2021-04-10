package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import com.mongodb.client.MongoClient;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class OrderHistoryViewBackendFactory {

  @Singleton
  public OrderHistoryViewWorkflow orderHistoryViewWorkflow(OrderHistoryViewService service) {
    return new OrderHistoryViewWorkflow(service);
  }

  @Singleton
  public CustomerViewRepository customerViewRepository(MongoClient mongoClient) {
    return new CustomerViewRepository(mongoClient);
  }

  @Singleton
  public OrderViewRepository orderViewRepository(MongoClient mongoClient) {
    return new OrderViewRepository(mongoClient);
  }
}
