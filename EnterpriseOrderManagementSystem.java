
// Enterprise Order Management System (EOMS) - Complex Java Project

import java.util.*;
import java.util.stream.Collectors;

enum OrderStatus {
    PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
}

abstract class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public abstract String getType();

    public double getPrice() { return price; }
    public String getName() { return name; }
}

class PhysicalProduct extends Product {
    public PhysicalProduct(String name, double price) {
        super(name, price);
    }
    public String getType() { return "Physical"; }
}

class DigitalProduct extends Product {
    public DigitalProduct(String name, double price) {
        super(name, price);
    }
    public String getType() { return "Digital"; }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public String toString() {
        return String.format("%s (%s) x%d - $%.2f", product.getName(), product.getType(), quantity, getTotalPrice());
    }
}

class Order {
    private static int nextOrderId = 1;
    private int orderId;
    private String customerName;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(String customerName) {
        this.orderId = nextOrderId++;
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }

    public void addItem(OrderItem item) { items.add(item); }
    public double getTotalAmount() { return items.stream().mapToDouble(OrderItem::getTotalPrice).sum(); }
    public void changeStatus(OrderStatus status) { this.status = status; }
    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public OrderStatus getStatus() { return status; }

    public void printInvoice() {
        System.out.println("\nINVOICE - Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Status: " + status);
        for (OrderItem item : items) {
            System.out.println(" - " + item);
        }
        System.out.printf("Total Amount: $%.2f\n", getTotalAmount());
    }
}

public class EnterpriseOrderManagementSystem {
    private static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEOMS Menu:");
            System.out.println("1. Create Order");
            System.out.println("2. View Orders");
            System.out.println("3. Update Order Status");
            System.out.println("4. Generate Reports");
            System.out.println("5. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> createOrder(scanner);
                case 2 -> viewOrders();
                case 3 -> updateOrderStatus(scanner);
                case 4 -> generateReports();
                case 5 -> {
                    System.out.println("Exiting EOMS. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void createOrder(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        Order order = new Order(customerName);

        String more;
        do {
            System.out.print("Enter product name: ");
            String productName = scanner.nextLine();

            System.out.print("Enter product price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Product type (physical/digital): ");
            String type = scanner.nextLine().toLowerCase();

            Product product = type.equals("digital") ? new DigitalProduct(productName, price) : new PhysicalProduct(productName, price);
            OrderItem item = new OrderItem(product, qty);
            order.addItem(item);

            System.out.print("Add another product? (yes/no): ");
            more = scanner.nextLine();
        } while (more.equalsIgnoreCase("yes"));

        orders.add(order);
        order.printInvoice();
    }

    private static void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        for (Order order : orders) {
            order.printInvoice();
        }
    }

    private static void updateOrderStatus(Scanner scanner) {
        System.out.print("Enter Order ID to update: ");
        int orderId = scanner.nextInt();
        Optional<Order> optOrder = orders.stream().filter(o -> o.getOrderId() == orderId).findFirst();

        if (optOrder.isEmpty()) {
            System.out.println("Order ID not found.");
            return;
        }

        System.out.println("Select new status:");
        for (int i = 0; i < OrderStatus.values().length; i++) {
            System.out.printf("%d. %s\n", i + 1, OrderStatus.values()[i]);
        }
        int statusChoice = scanner.nextInt();
        if (statusChoice >= 1 && statusChoice <= OrderStatus.values().length) {
            optOrder.get().changeStatus(OrderStatus.values()[statusChoice - 1]);
            System.out.println("Order status updated.");
        } else {
            System.out.println("Invalid status.");
        }
    }

    private static void generateReports() {
        double totalRevenue = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        System.out.printf("Total Revenue: $%.2f\n", totalRevenue);

        Map<OrderStatus, Long> statusCounts = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
        System.out.println("Order Status Report:");
        for (OrderStatus status : OrderStatus.values()) {
            System.out.printf("%s: %d orders\n", status, statusCounts.getOrDefault(status, 0L));
        }
    }
}
