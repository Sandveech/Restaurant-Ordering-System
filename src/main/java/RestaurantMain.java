package src.main.java;

public class RestaurantMain {
    public static void main(String[] args) {
        MenuItem carbonara = new MenuItem(5, "Carbonara", "Italian cream.", "Pasta");
        MenuItem algio_e_olio = new MenuItem(5, "Aglio e Olio", "Garlic and oil.", "Pasta");

        OrderItem order1 = new OrderItem(carbonara, 1, 's');
        OrderItem order2 = new OrderItem(algio_e_olio, 2, 's');

        Cart cart = new Cart(1, 10);
        cart.addOrder(order1).addOrder(order2);

        cart.printOrders();
    }
}
