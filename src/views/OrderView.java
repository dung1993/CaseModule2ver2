package src.views;

import src.menu.Menu;
import src.models.ItemOrder;
import src.models.Order;
import src.models.Product;
import src.service.ItemOrderService;
import src.service.OrderService;
import src.service.ProductService;
import src.utils.AppUtils;
import src.utils.DateUtils;
import src.utils.InstantUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    public List<Order> orders;
    private static OrderService orderService = new OrderService();
    private static ProductService productService = new ProductService();
    private static ItemOrderService itemOrderService = new ItemOrderService();

    private static ProductView productView = new ProductView();

    private static final Scanner scanner = new Scanner(System.in);

    public OrderView() {
        orderService = OrderService.getInstanceOrder();
        productService = ProductService.getInstanceProduct();
        itemOrderService = ItemOrderService.getInstanceItemOrder();
    }

    public static void addOrder() {
        try{
            itemOrderService.findAllItemOrder();
            Long idOrder = System.currentTimeMillis() / 1000;
            System.out.println("Enter the name of the person ordering: (EX: Petter)");
            System.out.print("==>  ");
            String fullName;
            while (!DateUtils.isNameInvalid(fullName = scanner.nextLine())) {
                System.out.println("Name" + fullName + " Incorrect format please try again " + " (Names must be capitalized and not accented)");
                System.out.print("==>  ");
            }
            System.out.println("Enter your mobile phone: ");
            System.out.print("==>  ");
            String phoneNumber = scanner.nextLine();
            while (!DateUtils.isPhoneInvalid(phoneNumber) || phoneNumber.trim().isEmpty()) {
                System.out.println("Mobile" + phoneNumber + " Incorrect format please try again");
                System.out.println(" Enter your mobile phone( 10 to 11 number and start at number 0)");
                System.out.print("==>  ");
                phoneNumber = scanner.nextLine();
            }

            Order order = new Order(idOrder, fullName, phoneNumber, Instant.now());
            List<ItemOrder> itemOrders = addItemOrder(idOrder);
            for (ItemOrder itemOrder : itemOrders) {
                itemOrderService.addItemOrder(itemOrder);
            }

            orderService.add(order);
            confirmOrder(order);
        } catch (Exception e) {
            System.out.println("Incorrect format please try again!");
        }
    }

    public static void confirmOrder(Order order) {
        try {
            boolean flag = true;
            String choice;
            System.out.println();
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("⥘⥘                      ⤿Order⤾                    ⥘⥘");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("⥘⥘                   1. Print Bill                 ⥘⥘");
            System.out.println("⥘⥘                   2. Return                     ⥘⥘");
            System.out.println("⥘⥘                   0. Exit                       ⥘⥘");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("Select your choice:");
            System.out.print("==>  ");
            do {
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        showPayInfoAdmin(order);
                        break;
                    case "2":
                        Menu.menuManageOrder();
                        break;
                    case "3":
                        System.exit(5);
                        break;
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


    public static List<ItemOrder> addItemOrder(Long id) {
        List<ItemOrder> listOrders = new ArrayList<>();
        productView.showProductList();
        System.out.println(" Input amount of product you need buy:");
        System.out.print("==>  ");
        int choice = Integer.parseInt(scanner.nextLine());
        while (choice < 0) {
            System.out.println("Amount is Wrong! Please input again(not less than 0)");
            System.out.print("==>  ");
            choice = Integer.parseInt(scanner.nextLine());
        }
        int count = 0;
        do {
            try {
                listOrders.add(addItemOrders(id));
                count++;

            } catch (Exception e) {
                System.out.println("Incorrect, please try again!");
            }
        } while (count < choice);
        return listOrders;
    }

    public static ItemOrder addItemOrders(Long idOrder) {
        do {
            try {
                itemOrderService.findAllItemOrder();
                productView.showProductList();
                Long idItemOrder = System.currentTimeMillis() / 1000;
                System.out.println("Input id product you need by:");
                System.out.print("==>  ");
                Long idProduct = Long.parseLong(scanner.nextLine());
                while (!productService.exitsById(idProduct)) {
                    System.out.println("idProduct does not exist! Please input again");
                    System.out.print("==>  ");
                    idProduct = Long.parseLong(scanner.nextLine());
                }
                Product product = productService.checkId(idProduct);
                Double price = product.getPrice();
                System.out.println("Input quantity you need by:");
                System.out.print("==>  ");
                Integer quantity = Integer.parseInt(scanner.nextLine());
                while (!checkQuantityProduct(product, quantity)) {
                    System.out.println("Quantity is Wrong! Please input again(not less than 0)");
                    System.out.print("==>  ");
                    quantity = Integer.parseInt(scanner.nextLine());
                    if (product.getQuantity() == 0) {
                        System.out.println("Product is out of stock");
                        int choice;
                        do {
                            System.out.println(" Press 0 to return to product manager");
                            choice = AppUtils.retryParseInt();
                        } while (choice != 0);
                        Menu.menuManageOrder();
                    }
                }
                Instant orderTime = Instant.now();
                String nameProduct = product.getNameProduct();
                Double total = quantity * price;
                Double grandTotal = 0.0;
                ItemOrder itemOrder = new ItemOrder(idItemOrder, idOrder, idProduct, nameProduct, price, quantity, total, grandTotal, orderTime);
                return itemOrder;
            } catch (Exception e) {
                System.out.println("Incorrect, please try again!");
            }
        } while (true);
    }

    private static boolean checkQuantityProduct(Product product, Integer quantity) {
        if (quantity <= product.getQuantity()) {
            return true;
        }
        return false;
    }

    public static void showPayInfo(Order order) {
        try {
            System.out.println();
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.println("⥘⥘                                    ⤿Bill⤾                                     ⥘⥘");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.printf("⥘⥘%-20s \t%-30s %20s ⥘⥘\n", "Name          | ", order.getFullName(), "");
            System.out.printf("⥘⥘%-20s \t%-30s %20s ⥘⥘\n", "PhoneNumber        | ", order.getPhoneNumber(), "");
            System.out.printf("⥘⥘%-20s \t%-30s %20s ⥘⥘\n", "CreateTime    | ", InstantUtils.instantToString(order.getOrderTime()), "");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.printf("⥘⥘%-4s │\t%-25s │\t%-15s │\t%-15s ⥘⥘\n", "Id", "Product Name", "Price", "Quantity");
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            List<ItemOrder> itemOrders = itemOrderService.findAllItemOrder();
            double sum = 0;
            int count = 0;
            for (ItemOrder itemOrder : itemOrders) {
                if (itemOrder.getIdOrder().equals(order.getIdOrder())) {
                    sum += itemOrder.getTotal();
                    count++;
                    itemOrder.setGrandTotal(sum);
                    itemOrderService.update(itemOrder.getIdOrder(), itemOrder.getPrice(), sum);
                    System.out.printf("⥘⥘%-4s │\t%-25s │\t%-15s │\t%-15s ⥘⥘ \n",
                            count,
                            itemOrder.getNameProduct(),
                            InstantUtils.doubleToVND(itemOrder.getPrice()),
                            InstantUtils.quantityProduct(itemOrder.getQuantity()));
                    System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
                }
            }
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.printf("⥘⥘                                        Total : %17s     ⥘⥘\n", InstantUtils.doubleToVND(sum));
            System.out.println("⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
        } catch (Exception e) {
            System.out.println("Incorrect, please try again!");
        }
    }

    public static void showListItemOrder() {
        try {
            List<Order> orders = orderService.findALlOrders();
            List<ItemOrder> itemOrders = itemOrderService.findAllItemOrder();
            int count = 0;
            double printTotal = 0;
            double total = 0;
            double sum = 0;
            double grandTotal = 0;
            System.out.println();
            System.out.println("\t⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚List⟚Bill⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            for (Order order : orders) {
                System.out.println("\t⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
                System.out.printf("\t⥘⥘\t%-15s %-20s %-20s %-20s⥘⥘\n", "Id            ", order.getIdOrder(), "Name ", order.getFullName());
                System.out.printf("\t⥘⥘\t%-15s %-20s %-20s %-20s⥘⥘\n", "PhoneNumber     ", order.getPhoneNumber(), "OrderTime", order.getOrderTime());
                System.out.println("\t⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
                System.out.printf("\t⥘⥘\t%-2s │%-10s %-25s %-10s %-20s⥘⥘\n", "Id", "Product Name", "Price", "Quantity", "GrandTotal");
                System.out.println("\t⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
                for (ItemOrder itemOrder : itemOrders) {
                    if (itemOrder.getIdOrder().equals(order.getIdOrder())) {
                        count++;
                        total = itemOrder.getPrice() * itemOrder.getQuantity();
                        System.out.printf("\t⥘⥘\t%-2s │%-10s %-25s %-10s %-20s⥘⥘\n", count,
                                itemOrder.getIdOrder(),
                                InstantUtils.doubleToVND(itemOrder.getPrice()),
                                InstantUtils.quantityProduct(itemOrder.getQuantity()),
                                InstantUtils.doubleToVND(itemOrder.getTotal()));
                        grandTotal += total;
                    }
                }
                printTotal += grandTotal;
                System.out.printf("\t⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚GrandTotal:%15s⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘\n\n\n", InstantUtils.doubleToVND(grandTotal));
                sum = 0;
                grandTotal = 0;
                count = 0;
            }
            System.out.println("\t\t\t\t\t\t\t⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘");
            System.out.printf("\t\t\t\t\t\t\t⥘⥘             printTotal: %20s         ⥘⥘\n", InstantUtils.doubleToVND(printTotal));
            System.out.println("\t\t\t\t\t\t\t⥘⥘⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⟚⥘⥘\n");
            int choice;
            do {
                System.out.println(" Press 0 to return to product manager");
                System.out.print("==>  ");
                choice = AppUtils.retryParseInt();
            } while (choice != 0);
            Menu.menuManageOrder();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private static void showPayInfoAdmin(Order order) {
        showPayInfo(order);
        int choice;
        do {
            System.out.println(" Press 0 to return to product manager");
            System.out.print("==>  ");
            choice = AppUtils.retryParseInt();
        } while (choice != 0);
        Menu.menuManageOrder();
    }

    public static void showPayInfoUser(Order order) {
        showPayInfo(order);
        int choice;
        do {
            System.out.println("Press 0 to return to product manager");
            System.out.print("==>  ");
            choice = AppUtils.retryParseInt();
        } while (choice != 0);
        Menu.menuUser();
    }

    public static void addOrderUser() {
        try {
            itemOrderService.findAllItemOrder();
            Long idOrder = System.currentTimeMillis() / 1000;
            System.out.println("Enter the name of the person ordering: (EX: Petter)");
            System.out.print("==>  ");
            String fullName;
            while (!DateUtils.isNameInvalid(fullName = scanner.nextLine())) {
                System.out.println("Name" + fullName + " Incorrect format please try again " + " (Names must be capitalized and not accented)");
                System.out.print("==>  ");
            }
            System.out.println("Enter your mobile phone: ");
            System.out.print("==>  ");
            String mobile = scanner.nextLine();
            while (!DateUtils.isPhoneInvalid(mobile) || mobile.trim().isEmpty()) {
                System.out.println("Mobile" + mobile + " Incorrect format please try again");
                System.out.println(" Enter your mobile phone( 10 to 11 number and start at number 0)");
                System.out.print("==>  ");
                mobile = scanner.nextLine();
            }

            Order order = new Order(idOrder, fullName, mobile, Instant.now());
            List<ItemOrder> itemOrders = addItemOrder(idOrder);
            for (ItemOrder itemOrder : itemOrders) {
                itemOrderService.addItemOrder(itemOrder);
            }

            orderService.add(order);
            confirmOrder(order);
        } catch (Exception e) {
            System.out.println("Incorrect format please try again!");
        }
    }
}
