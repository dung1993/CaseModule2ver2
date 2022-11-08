package src.models;

import java.time.Instant;
import java.util.Objects;

public class Product {
    private Long idProduct;
    private String nameProduct;
    private Double price;
    private Integer quantity;
    private Instant createTime;
    private Instant updateTime;

    public Product() {
    }

    public Product(Long idProduct, String nameProduct, Double price, Integer quantity, Instant createTime, Instant updateTime) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String toString(){
        return String.format("%s,%s,%s,%s,%s,%s",idProduct,nameProduct,price,quantity,createTime,updateTime);
    }

    public static Product parseProduct(String myProduct){
        String[] array = myProduct.split(",");
        Product product =new Product();
        product.setIdProduct(Long.parseLong(array[0]));
        product.setNameProduct(array[1]);
        product.setPrice(Double.parseDouble(array[2]));
        product.setQuantity(Integer.parseInt(array[3]));
        product.setCreateTime(Instant.parse(array[4]));
        product.setUpdateTime(Instant.parse(array[5]));
        return product;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        return idProduct.equals(product.idProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct);
    }
}
