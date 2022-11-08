package src.views;

import src.menu.Menu;
import src.models.Product;
import src.service.ProductService;
import src.utils.AppUtils;
import src.utils.InstantUtils;


import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    public List<Product> product;

    private static ProductService productService = new ProductService();
    private static Scanner scanner = new Scanner(System.in);

    public void addProduct() {
        boolean flag = true;
        do {
            System.out.println("ᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝ|Input New Product|ᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝᴝ");
            Long id = System.currentTimeMillis() / 1000;
            String nameProduct = inputName(ChoiceStatus.ADD);
            Double price = inputPrice(ChoiceStatus.ADD);
            Integer quantity = inputQuantity(ChoiceStatus.ADD);
            Instant createAt = Instant.now();
            Instant updateAt = createAt;
            Product product = new Product(id, nameProduct, price, quantity, createAt, updateAt);
            productService.addProduct(product);
            System.out.println("Input Success");
            showProductListShow();
            Menu.menuManagerProduct();
        } while (flag);
    }


    public Long inputIdProduct(ChoiceStatus status) {
        Long idProduct;
        switch (status) {
            case EDIT:
            case REMOVE:
                System.out.println("Input id product you need delete:");
                break;
        }
        System.out.print("==>  ");
        boolean flagProductId = true;
        do {
            idProduct = AppUtils.parseLong();
            boolean exits = productService.exitsById(idProduct);
            switch (status) {
                case EDIT:
                case REMOVE:
                    if (!exits){
                    System.out.println("Your id invalid! Please input id again: ");
                    System.out.print("==>  ");
                    }
                    flagProductId = !exits;
                    break;
            }
        } while (flagProductId);
        return idProduct;
    }

    private String inputName(ChoiceStatus status) {
        String name;
        switch (status) {
            case ADD:
                System.out.println("Input name product: ");
                break;
            case EDIT:
                System.out.println("Input name you need repair");
                break;
        }
        System.out.print("==>  ");
        boolean flagInputName = true;
        do {
            name = scanner.nextLine().trim();
            boolean exits = (!name.isEmpty());
            switch (status) {
                case ADD:
                case EDIT:
                    if (!exits) {
                        System.out.println("Input Invalid! Please again input name: ");
                        System.out.print("==>  ");
                    }
                    flagInputName = !exits;
                    break;
            }
        } while (flagInputName);
        return name;
    }

    private Double inputPrice(ChoiceStatus status) {
        Double price;
        switch (status) {
            case ADD:
                System.out.println("Input price of product: ");
                break;
            case EDIT:
                System.out.println("Input price you need repair: ");
                break;
        }
        System.out.print("==>  ");
        boolean flagInputName = true;
        do {
            price = AppUtils.parseDouble();
            boolean exits = (price >= 1000);
            switch (status) {
                case ADD:
                case EDIT:
                case REMOVE:
                    if (!exits) {
                        System.out.println(" Your input invalid, Please again input price( greater than 1000): ");
                        System.out.print("==>  ");
                    }
                    flagInputName = !exits;
                    break;
            }
        } while (flagInputName);
        return price;
    }

    private Integer inputQuantity(ChoiceStatus status) {
        Integer quantity;
        switch (status) {
            case ADD:
                System.out.println("Input quantity of product: ");
                break;
            case EDIT:
                System.out.println("Input quantity you need repair: ");
                break;
        }
        System.out.print("==>  ");
        boolean flagInputName = true;
        do {
            quantity = AppUtils.retryParseInt();
            boolean exits = (quantity > 0);
            switch (status) {
                case ADD:
                case EDIT:
                case REMOVE:
                    if (!exits) {
                        System.out.println(" Your input invalid, Please again input amount( greater than 1000): ");
                        System.out.print("==>  ");
                    }
                    flagInputName = !exits;
                    break;
            }
        } while (flagInputName);
        return quantity;
    }

    public void showProductListShow() {
        showProductList();
        int choice;
        do {
            System.out.println("Press 0 if you need return");
            System.out.print("==>  ");
            choice = AppUtils.retryParseInt();
        } while (choice != 0);
        Menu.menuManagerProduct();
    }

    public void showProductList() {
        System.out.println();
        System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚Product⟚List⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
        System.out.printf("⥘⥘%-20s %-25s %-20s %-20s %-20s %-20s⥘⥘\n", "IdProduct", "NameProduct", "Price", "Quantity", "CreateTime", "UpdateTime");
        System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
        for (Product product : productService.findAllProducts()) {
            System.out.printf("⥘⥘%-20s %-25s %-20s %-20s %-20s %-20s⥘⥘\n",
                    product.getIdProduct(),
                    product.getNameProduct(),
                    InstantUtils.doubleToVND(product.getPrice()),
                    InstantUtils.quantityProduct(product.getQuantity()),
                    InstantUtils.instantToString(product.getCreateTime()),
                    InstantUtils.instantToString(product.getUpdateTime())

            );
        }
        System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
    }

    public void showProductListShowUser() {
        showProductListShow();
        Menu.menuUser();
    }

    public void showProductListShowOutMenu() {
        showProductListShow();
        Menu.menuManagerProduct();
    }

    public void editProduct() {
        try {
            System.out.println();
            showProductList();
            System.out.println("Id of product you need repair: ");
            System.out.print("==>  ");
            Long id = AppUtils.parseLong();
            if (productService.exitsById(id)) {
                boolean flagUpdate = true;
                System.out.println();
                System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
                System.out.println("⥘⥘              ⤿Edit Product⤾              ⥘⥘");
                System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
                System.out.println("⥘⥘             1. Edit Product Name         ⥘⥘");
                System.out.println("⥘⥘             2. Edit Product Price        ⥘⥘");
                System.out.println("⥘⥘             3. Edit Product Quantity     ⥘⥘");
                System.out.println("⥘⥘             4. Return                    ⥘⥘");
                System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
                System.out.println("Select your choice:");
                System.out.print("==>  ");
                Product newProduct = new Product();
                newProduct.setIdProduct(id);
                do {
                    String choice = scanner.nextLine();
                    switch (choice){
                        case "1":
                            editName(newProduct);
                            break;
                        case "2":
                            editPrice(newProduct);
                            break;
                        case "3":
                            editQuantity(newProduct);
                            break;
                        default:
                            System.out.println("Wrong choice! Please select again:");
                            System.out.print("==>  ");
                            flagUpdate = false;    
                    }
                }while (flagUpdate);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void editName(Product newProduct) {
        String name = inputName(ChoiceStatus.EDIT);
        newProduct.setNameProduct(name);
        productService.editProduct(newProduct);
        System.out.println("Update success!");
        showProductList();
        ContinueOrExist();
    }

    private void editPrice(Product newProduct) {
        Double price = inputPrice(ChoiceStatus.EDIT);
        newProduct.setPrice(price);
        productService.editProduct(newProduct);
        System.out.println("Update success!");
        showProductList();
        ContinueOrExist();
    }

    private void editQuantity(Product newProduct) {
        Integer quantity = inputQuantity(ChoiceStatus.EDIT);
        newProduct.setQuantity(quantity);
        productService.editProduct(newProduct);
        System.out.println("Update success!");
        showProductList();
        ContinueOrExist();
    }

    private void ContinueOrExist() {
        boolean is = true;
        do {
            System.out.println("Press 1 to continues \t|\t '2' to return");
            System.out.print("=> ");
            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    editProduct();
                    break;
                case "2":
                    Menu.menuManagerProduct();
                    break;
                default:
                    System.out.println("Is wrong choice! Please choice again:");
                    System.out.print("==>  ");
                    is = false;
            }
        }while (!is);
    }

    public void findProduct(){
        try {
            boolean flag = true;
            String choice;
            System.out.println();
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("⥘⥘               ⤿Find Product⤾           ⥘⥘");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("⥘⥘          1. Find by name product       ⥘⥘");
            System.out.println("⥘⥘          2. Find by id product         ⥘⥘");
            System.out.println("⥘⥘          0. Return                     ⥘⥘");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("Select your choice:");
            System.out.print("==>  ");
            do {
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        findByName();
                        break;
                    case "2":
                        findById();
                        break;
                    case "0":
                        Menu.menuManagerProduct();
                        break;
                    default:
                        System.out.println("Is wrong choice! Please choice again:");
                        System.out.print("==>  ");
                        flag = false;
                }
            } while (!flag);
            findProduct();
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    private void findByName() {
        System.out.println("Input name product you need find:");
        System.out.print("==>  ");
        String name = scanner.nextLine().trim();
        List<Product> products = productService.findProductByName(name);
        if (products.size() != 0) {
            System.out.println();
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚Name⟚Product⟚You⟚Need⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.printf("⥘⥘%-25s %-20s %-20s %-20s⥘⥘\n", "IdProduct", "NameProduct", "Price", "Quantity");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            for (Product product : products) {
                System.out.printf("⥘⥘%-25s %-20s %-20s %-20s⥘⥘\n",
                        product.getIdProduct(),
                        product.getNameProduct(),
                        InstantUtils.doubleToVND(product.getPrice()),
                        InstantUtils.quantityProduct(product.getQuantity()));
            }
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            int choice;
            do {
                System.out.println("Press 0 if you need return productManager");
                System.out.print("==>  ");
                choice = AppUtils.retryParseInt();
            } while (choice != 0);
        } else {
            System.out.println();
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚Name⟚Product⟚You⟚Need⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.printf("⥘⥘%-25s %-20s %-20s %-20s⥘⥘\n", "IdProduct", "NameProduct", "Price", "Quantity");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.printf("⥘⥘%60s⥘⥘", "Product not found!!!\n");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            int choice;
            do {
                System.out.println("Press 0 if you need return productManager");
                System.out.print("==>  ");
                choice = AppUtils.retryParseInt();
            } while (choice != 0);
        }
        findProduct();
    }

    private void findById() {
        System.out.println("Input id you need find:");
        System.out.print("==>  ");
        Long id = AppUtils.parseLong();
        Product product = productService.checkId(id);
        if (product != null) {
            System.out.println();
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚Name⟚Product⟚You⟚Need⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.printf("⥘⥘%-25s %-20s %-20s %-20s⥘⥘\n", "IdProduct", "NameProduct", "Price", "Quantity");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.printf("⥘⥘%-25s %-20s %-20s %-20s⥘⥘\n",
                    product.getIdProduct(),
                    product.getNameProduct(),
                    InstantUtils.doubleToVND(product.getPrice()),
                    InstantUtils.quantityProduct(product.getQuantity()));
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            int choice;
            do {
                System.out.println("Press 0 if you need return productManager");
                System.out.print("==>  ");
                choice = AppUtils.retryParseInt();
            } while (choice != 0);
        } else {
            System.out.println();
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚Name⟚Product⟚You⟚Need⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.printf("⥘⥘%-25s %-20s %-20s %-20s⥘⥘\n", "IdProduct", "NameProduct", "Price", "Quantity");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.printf("⥘⥘%60s⥘⥘", "Product not found!!!\n");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            int choice;
            do {
                System.out.println("Press 0 if you need return productManager");
                System.out.print("==>  ");
                choice = AppUtils.retryParseInt();
            } while (choice != 0);
        }
        findProduct();
    }

    public void removeProduct() {
        try {
            boolean flag = true;
            showProductList();
            Long id = inputIdProduct(ChoiceStatus.REMOVE);
            System.out.println();
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("⥘⥘             ⤿Delete Product⤾           ⥘⥘");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("⥘⥘                  1. Yes                ⥘⥘");
            System.out.println("⥘⥘                  2. Return             ⥘⥘");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("Select your choice:");
            System.out.print("==>  ");
            do {
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        productService.removeProduct(id);
                        System.out.println("Detele product success!");
                        showProductList();
                        Menu.menuManagerProduct();
                        break;
                    case "2":
                        Menu.menuManagerProduct();
                    default:
                        System.out.println("Is wrong choice! Please choice again:");
                        System.out.print("==>  ");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
