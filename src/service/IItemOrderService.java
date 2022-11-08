package src.service;

import src.models.ItemOrder;

import java.util.List;

public interface IItemOrderService {
    List<ItemOrder> findAllItemOrder();
    void addItemOrder(ItemOrder NewItemOrder);
    void update(Long idOrder,Double price, Double grandTotal);
    ItemOrder getItemOrderById(Long id);
}
