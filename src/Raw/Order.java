package src.Raw;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.time.Instant;

public class Order {
    private Long idOrder;
    private String fullName;
    private String phoneNumber;
    private Double grandTotal;
    private Instant orderTime;

    public Order() {
    }

    public Order(Long idOrder, String fullName, String phoneNumber, Double grandTotal, Instant orderTime) {
        this.idOrder = idOrder;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.grandTotal = grandTotal;
        this.orderTime = orderTime;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        return String.format("%s,%s,%s,%s,%s",idOrder,fullName,phoneNumber,grandTotal,orderTime);
    }

    public static  Order parseOrder(String myOrder){
        String[] array = myOrder.split(",");
        Order order = new Order();
        order.setIdOrder(Long.parseLong(array[0]));
        order.setFullName(array[1]);
        order.setPhoneNumber(array[2]);
        order.setGrandTotal(Double.parseDouble(array[3]));
        order.setOrderTime(Instant.parse(array[4]));
        return order;
    }
}

