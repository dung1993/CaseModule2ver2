package src.Service;

import src.Raw.Order;

import java.util.List;

public interface IOrderService {
    List<Order> findALlOrders();

    void add(Order newOrder);

    void edit(Order newOrder);

    void remove(Long idOrder);

    Order findOrderById(Long idOrder);

    boolean exitOrderById(Long idOrder);

    List<Order> findOrderByIdUser(Long idOrder);
}
