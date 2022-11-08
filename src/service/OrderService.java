package src.service;

import src.models.Order;
import src.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {

    private final String Path = "D:\\CaseModule2ver2\\data\\Orders.csv";
    public static OrderService instanceOrder;

    public static OrderService getInstanceOrder() {
        if (instanceOrder == null) {
            instanceOrder = new OrderService();
        }
        return instanceOrder;
    }

    @Override
    public List<Order> findALlOrders() {
        List<Order> orders = new ArrayList<>();
        List<String> records = CSVUtils.read(Path);
        for (String record : records) {
            orders.add(Order.parseOrder(record));
        }
        return orders;
    }

    @Override
    public void add(Order newOrder) {
        List<Order> orders = findALlOrders();
        newOrder.setOrderTime(Instant.now());
        orders.add(newOrder);
        CSVUtils.write(Path, orders);
    }

    @Override
    public void edit(Order newOrder) {
        List<Order> orders = findALlOrders();
        for (Order oldOrder : orders) {
            if (oldOrder.getIdOrder().equals(newOrder.getIdOrder())) {
                String name = newOrder.getFullName();
                if (name != null && !name.isEmpty()) {
                    oldOrder.setFullName(newOrder.getFullName());
                }
                String phoneNumber = newOrder.getPhoneNumber();
                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                    oldOrder.setPhoneNumber(newOrder.getPhoneNumber());
                }
                CSVUtils.write(Path, orders);
                break;
            }
        }
    }

    @Override
    public void remove(Long idOrder) {
        List<Order> orders = findALlOrders();
        orders.removeIf(id -> id.getIdOrder().equals(idOrder));
        CSVUtils.write(Path, orders);
    }

    @Override
    public Order findOrderById(Long idOrder) {
        List<Order> orders = findALlOrders();
        List<String> listFind = new ArrayList<>();
        if (idOrder > 0) {
            for (Order findOrder : orders) {
                if (findOrder.getIdOrder().equals(idOrder)) {
                    listFind.add(findOrder.toString());
                }
            }
        }
        return null;
    }

    @Override
    public boolean exitOrderById(Long idOrder) {
        return findOrderById(idOrder) != null;
    }

    @Override
    public List<Order> findOrderByIdUser(Long idOrder) {
        List<Order> order = findALlOrders();
        for (Order orders: findALlOrders()) {
            if (orders.getIdOrder().equals(idOrder)){
                order.add(orders);
            }
            return order;
        }
        return null;
    }
}
