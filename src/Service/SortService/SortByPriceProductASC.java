package src.Service.SortService;

import src.Raw.Product;

import java.util.Comparator;

public class SortByPriceProductASC implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getPrice() - o2.getPrice());
    }
}
