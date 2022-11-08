package src.service;

import src.models.ItemOrder;
import src.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class ItemOrderService implements IItemOrderService {
    public final static String Path = "D:\\CaseModule2ver2\\data\\ItemOrder.csv";
    private static ItemOrderService instanceItemOrder;

    public ItemOrderService(){
    }

    public static ItemOrderService getInstanceItemOrder(){
        if (instanceItemOrder == null){
            instanceItemOrder = new ItemOrderService();
        }
        return instanceItemOrder;
    }
    @Override
    public List<ItemOrder> findAllItemOrder() {
        List<ItemOrder> ItemOrders =new ArrayList<>();
        List<String> records = CSVUtils.read(Path);
        for (String record: records) {
            ItemOrders.add(ItemOrder.parseItemOrder(record));
        }
        return ItemOrders;
    }

    @Override
    public void addItemOrder(ItemOrder newItemOrder) {
        List<ItemOrder> itemOrder= findAllItemOrder();
        itemOrder.add(newItemOrder);
        CSVUtils.write(Path,itemOrder);

    }

    @Override
    public void update(Long idOrder, Double price, Double grandTotal) {
        List<ItemOrder> itemOrders = findAllItemOrder();
        for (ItemOrder itemOrder: itemOrders) {
            if (itemOrder.getIdOrder().equals(idOrder)){
                if (itemOrder.getPrice().equals(price)){
                    itemOrder.setGrandTotal(grandTotal);
                    CSVUtils.write(Path,itemOrders);
                    break;
                }
            }
        }
    }

    @Override
    public ItemOrder getItemOrderById(Long id) {
        List<ItemOrder> list = findAllItemOrder();
        for (ItemOrder itemOrder : list){
            if (itemOrder.getIdItemOrder().equals(id)){
                return itemOrder;
            }
        }
        return null;
    }
}
