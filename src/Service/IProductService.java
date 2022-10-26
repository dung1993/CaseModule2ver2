package src.Service;

import src.Raw.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllProducts();

    void addProduct(Product newProduct);

    void editProduct(Product newProduct);

    void removeProduct(Long idProduct);

    List<Product> findProductByName(String nameProduct);

    boolean exitsById(Long idProduct);

    Product checkId(Long idProduct);

    void updateAmount(Long idProduct, Integer quantity);

}
