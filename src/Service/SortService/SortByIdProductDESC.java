package src.Service.SortService;

import src.Raw.Product;

import java.util.Comparator;

public class SortByIdProductDESC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o2.getIdProduct()- o1.getIdProduct());
    }
}
