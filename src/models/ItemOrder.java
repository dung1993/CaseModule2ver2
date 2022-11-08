package src.models;

import java.time.Instant;

public class ItemOrder {
    private Long idItemOrder;
    private Long idOrder;
    private Long idProduct;
    private String nameProduct;
    private Double price;
    private Integer quantity;
    private Double total;
    private Double grandTotal;
    private Instant orderTime;

    public ItemOrder() {
    }

    public ItemOrder(Long idItemOrder, Long idOrder, Long idProduct, String nameProduct, Double price, Integer quantity, Double total, Double grandTotal, Instant orderTime) {
        this.idItemOrder = idItemOrder;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.grandTotal = grandTotal;
        this.orderTime = orderTime;
    }



    public Long getIdItemOrder() {
        return idItemOrder;
    }

    public void setIdItemOrder(Long idItemOrder) {
        this.idItemOrder = idItemOrder;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Instant getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Instant orderTime) {
        this.orderTime = orderTime;
    }

    public String toString(){
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                idItemOrder,
                idOrder,
                idProduct,
                nameProduct,
                price,
                quantity,
                total,
                grandTotal,
                orderTime);
    }

    public static ItemOrder parseItemOrder(String myItemOrder){
        String[] array = myItemOrder.split(",");
        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setIdItemOrder(Long.parseLong(array[0]));
        itemOrder.setIdOrder(Long.parseLong(array[1]));
        itemOrder.setIdProduct(Long.parseLong(array[2]));
        itemOrder.setNameProduct(array[3]);
        itemOrder.setPrice(Double.parseDouble(array[4]));
        itemOrder.setQuantity(Integer.parseInt(array[5]));
        itemOrder.setTotal(Double.parseDouble(array[6]));
        itemOrder.setGrandTotal(Double.parseDouble(array[7]));
        itemOrder.setOrderTime(Instant.parse(array[8]));
        return itemOrder;
    }
}
