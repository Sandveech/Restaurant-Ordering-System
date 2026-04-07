package src.main.java;

public class RestaurantMain {
    public static void main(String[] args) {
        MenuItem carbonara = new MenuItem(5, "Carbonara", "Italian cream.", "Pasta");
        MenuItem algio_olio = new MenuItem(5, "Aglio Olio", "Garlic and oil.", "Pasta");

        OrderItem order1 = new OrderItem(carbonara, 1, 's');

        OrderItem order2 = new OrderItem(algio_olio, 2, 's');

        Cart cart = new Cart(1, 10);
        cart.addOrder(order1).addOrder(order2);

        cart.removeOrder(1, 2);

        for (int i = 0; i < cart.getOrderCount(); i++) {
            System.out.println(cart.getOrders().get(i));
        }
    }
}
