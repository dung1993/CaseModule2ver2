package src.Service;

import src.Raw.ItemOrder;
import src.Raw.Order;

import java.util.List;

public interface IItemOrderService {
    List<ItemOrder> findAllItemOrder();
    void addItemOrder(ItemOrder NewItemOrder);
    void update(Long idOrder,Double price, Double grandTotal);
    ItemOrder getItemOrderById(Long id);
}
