package org.arghya.app.orderservice.repository;

import org.arghya.app.orderservice.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
