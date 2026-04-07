package src.main.java;

public class RestaurantMain {
    public static void main(String[] args) {
        MenuItem carbonara = new MenuItem(0, 5, "Carbonara", "Italian cream.", "Pasta");
        OrderItem order1 = new OrderItem(0, carbonara,1, 's');
        order1.setQuantity(10);

        System.out.println(order1.calculateTotal());
    }
}
