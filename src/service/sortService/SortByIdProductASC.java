package src.service.sortService;

import src.models.Product;

import java.util.Comparator;

public class SortByIdProductASC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getIdProduct() - o2.getIdProduct());
    }
}
