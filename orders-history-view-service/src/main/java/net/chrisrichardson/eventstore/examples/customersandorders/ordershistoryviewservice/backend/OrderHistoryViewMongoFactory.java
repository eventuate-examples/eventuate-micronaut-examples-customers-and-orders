package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import com.mongodb.MongoClient;
import io.micronaut.context.annotation.Factory;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.inject.Singleton;

@Factory
public class OrderHistoryViewMongoFactory {
  @Singleton
  public MongoTemplate mongoTemplate(MongoClient mongoClient) {
    return new MongoTemplate(mongoClient, "customers_and_orders");
  }
}
