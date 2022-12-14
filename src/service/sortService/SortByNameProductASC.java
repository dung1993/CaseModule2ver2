package src.service.sortService;

import src.models.Product;

import java.util.Comparator;

public class SortByNameProductASC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getNameProduct().compareTo(o2.getNameProduct());
    }
}
