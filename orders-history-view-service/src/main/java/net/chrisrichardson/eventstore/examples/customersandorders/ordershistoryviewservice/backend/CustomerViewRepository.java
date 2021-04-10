package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderState;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.CustomerView;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.OrderInfo;
import org.bson.Document;
import java.util.Optional;

public class CustomerViewRepository extends AbstractRepository {

  public CustomerViewRepository(MongoClient mongoClient) {
    super(mongoClient, "customers");
  }

  public Optional<CustomerView> findById(String customerId) {
    return findOne(customerId)
            .map(customerDocument -> {
              CustomerView customerView = new CustomerView();

              customerView.setId(customerId);
              customerView.setName(customerDocument.getString("name"));
              customerView.setCreditLimit(getMoney(customerDocument, "creditLimit"));

              Document orders = customerDocument.get("orders", Document.class);

              if (orders != null) {
                orders.forEach((key, value) -> {
                  String orderId = key;

                  Document orderDocument = (Document) value;

                  OrderInfo orderInfo = new OrderInfo(orderId, getMoney(orderDocument, "orderTotal"));

                  Optional
                          .ofNullable(orderDocument.getString("state"))
                          .map(OrderState::valueOf)
                          .ifPresent(orderInfo::setState);

                  customerView.getOrders().put(orderId, orderInfo);
                });
              }

              return customerView;
            });
  }

  public void addCustomer(String customerId, String customerName, Money creditLimit) {
    repeatOnFailure(() -> {
      BasicDBObject customer = new BasicDBObject()
              .append("name", customerName)
              .append("creditLimit", creditLimit.getAmount());

      findOneAndUpdate(customerId, customer);
    });
  }

  public void addOrder(String customerId, String orderId, Money orderTotal) {
    repeatOnFailure(() -> {
      BasicDBObject orderInfo = new BasicDBObject()
              .append("orderId", orderId)
              .append("orderTotal", orderTotal.getAmount());

      findOneAndUpdate(customerId, new BasicDBObject("orders." + orderId, orderInfo));
    });
  }

  public void updateOrderState(String customerId, String orderId, OrderState state) {
    repeatOnFailure(() -> {
      collection().findOneAndUpdate(new BasicDBObject("_id", customerId),
              new BasicDBObject("$set", new BasicDBObject(String.format("orders.%s.state", orderId), state.name())),
              upsertOptions());
    });
  }
}
