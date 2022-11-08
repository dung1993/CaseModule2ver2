package src.service.sortService;

import src.models.Product;

import java.util.Comparator;

public class SortByPriceDESC implements Comparator<Product> {
    @Override
    //1.5-1.4
    public int compare(Product o1, Product o2) {
        return Double.compare(o2.getPrice(), o1.getPrice());
//        double result = - ;
//        if (result == 0)
//            return 0;
//        return result > 0 ? 1 : -1;
    }
}
