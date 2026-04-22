package src.main.java.com.restaurant;

import src.main.java.com.restaurant.model.Cart;
import src.main.java.com.restaurant.model.Category;
import src.main.java.com.restaurant.model.Employee;
import src.main.java.com.restaurant.model.JobRole;
import src.main.java.com.restaurant.model.MenuItem;
import src.main.java.com.restaurant.model.Order;
import src.main.java.com.restaurant.model.Receipt;
import src.main.java.com.restaurant.model.Table;

public class Main {
    public static void main(String[] args) {
        Category cat1 = new Category("Appetizers", "A small serving of food meant to be eaten before an entree, and often shared by several people.");
        Category cat2 = new Category("Entree", "A dish served before the main course of a meal.");
        Category cat3 = new Category("Main Course", "The featured or primary dish in a meal consisting of several courses.");
        Category cat4 = new Category("Dessert", "A dish that consists of sweet foods, such as cake, biscuits, and ice cream.");

        MenuItem item1 = new MenuItem("Aglio e Olio", "A classic Italian pasta dish from Naples, translating directly to \"garlic and oil\"", cat3, 5, true);
        MenuItem item2 = new MenuItem("Carbonara", "A classic Roman pasta dish made by tossing hot pasta with a creamy sauce of raw eggs, grated Pecorino Romano cheese, and cured pork, and black pepper.", cat3, 6, true);

        Table table1 = new Table(4);

        JobRole job1 = new JobRole("Waiter");
        JobRole job2 = new JobRole("Cashier");

        Employee employee1 = new Employee("Hout", "Khongdara", "Male", "houtkhongdara@gmail.com", "123456789", job1);
        Employee employee2 = new Employee("Sophal", "Varajedt", "Male", "sophalvarajedt@gmail.com", "987654321", job2);

        Cart cart = new Cart(employee1, table1);
        cart.addOrder(item1, 1, "small");
        cart.addOrder(item2, 1, "small");
        cart.addOrder(item1, 3, "small");
        cart.addOrder(item1, 1, "medium");

        Receipt receipt = new Receipt(cart, table1, employee2);

        System.out.println(employee1);
        System.out.println(employee2);

        // for (Order order : cart.getOrders()) {
        //     System.out.println(order);
        // }
    }
}
