package net.chrisrichardson.eventstore.examples.customersandorders.ordershistoryviewservice.backend;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderState;
import net.chrisrichardson.eventstore.examples.customersandorders.ordershistorycommon.OrderView;

import java.util.Optional;

public class OrderViewRepository extends AbstractRepository {

  public OrderViewRepository(MongoClient mongoClient) {
    super(mongoClient, "orders");
  }

  public Optional<OrderView> findById(String orderId) {
    return findOne(orderId)
            .map(orderDocument -> {

              OrderView orderView = new OrderView(orderId, getMoney(orderDocument, "orderTotal"));

              Optional
                      .ofNullable(orderDocument.getString("state"))
                      .map(OrderState::valueOf).ifPresent(orderView::setState);

              return orderView;
            });
  }

  public void addOrder(String orderId, Money orderTotal) {
    repeatOnFailure(() -> {
      findOneAndUpdate(orderId, new BasicDBObject("orderTotal", orderTotal.getAmount()));
    });
  }

  public void updateOrderState(String orderId, OrderState state) {
    repeatOnFailure(() -> {
      findOneAndUpdate(orderId, new BasicDBObject("state", state.name()));
    });
  }
}
