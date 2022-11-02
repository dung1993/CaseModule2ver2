package src.Service.SortService;

import src.Raw.Product;

import java.util.Comparator;

public class SortByNameProductDESC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getNameProduct().compareTo(o1.getNameProduct());
    }
}
