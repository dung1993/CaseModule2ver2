package src.service;

import src.models.Product;
import src.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    public final static String Path = "D:\\CaseModule2ver2\\data\\Products.csv";
    private static ProductService instanceProduct;

    public ProductService() {
    }

    public static ProductService getInstanceProduct() {
        if (instanceProduct == null) {
            instanceProduct = new ProductService();
        }
        return instanceProduct;
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.read(Path);
        for (String record : records) {
            products.add(Product.parseProduct(record));
        }
        return products;
    }

    @Override
    public void addProduct(Product newProduct) {
        List<Product> products = findAllProducts();
        products.add(newProduct);
        CSVUtils.write(Path, products);
    }

    @Override
    public void editProduct(Product newProduct) {
        List<Product> products = findAllProducts();
        for (Product oldProduct : products) {
            if (oldProduct.getIdProduct().equals(newProduct.getIdProduct())) {
                String name = newProduct.getNameProduct();
                if (name != null && !name.isEmpty()) {
                    oldProduct.setNameProduct(newProduct.getNameProduct());
                }
                Integer quantity = newProduct.getQuantity();
                if (quantity != null) {
                    if (quantity > 0) {
                        oldProduct.setQuantity(quantity);
                    }
                }
                Double price = newProduct.getPrice();
                if (price != null) {
                    if (price > 0) {
                        oldProduct.setPrice(price);
                    }
                }
                CSVUtils.write(Path, products);
                break;
            }
        }
    }

    @Override
    public void removeProduct(Long idProduct) {
        List<Product> products = findAllProducts();
        products.removeIf(id -> id.getIdProduct().equals(idProduct));
        CSVUtils.write(Path, products);
    }

    @Override
    public List<Product> findProductByName(String nameProduct) {
        List<Product> products = findAllProducts();
        List<Product> listFind = new ArrayList<>();
        if (nameProduct != null) {
            for (Product oldProduct : products) {
                if (oldProduct.getNameProduct().toLowerCase().contains(nameProduct.toLowerCase())) {
                    listFind.add(oldProduct);
                }
            }
        }
        return listFind;
    }

    @Override
    public boolean exitsById(Long idProduct) {
        return checkId(idProduct) != null;
    }

    @Override
    public Product checkId(Long idProduct) {
        List<Product> products = findAllProducts();
        for (Product product : products) {
            if (product.getIdProduct().equals(idProduct)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void updateAmount(Long idProduct, Integer quantity) {
        List<Product> products = findAllProducts();
        for (Product oldProduct : products) {
            if (oldProduct.getIdProduct().equals(idProduct)) {
                Integer oldQuantity = oldProduct.getQuantity();
                if (oldQuantity >= quantity) {
                    oldProduct.setQuantity(oldQuantity - quantity);
                    CSVUtils.write(Path, products);
                    break;
                }
            }
        }
    }
}
