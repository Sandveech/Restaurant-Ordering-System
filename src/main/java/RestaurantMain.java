package src.main.java;

public class RestaurantMain {
    public static void main(String[] args) {
        MenuItem carbonara = new MenuItem(0, 5, "Carbonara", "Italian cream.", "Pasta");
        MenuItem algio_olio = new MenuItem(0, 5, "Aglio Olio", "Garlic and oil.", "Pasta");

        OrderItem order1 = new OrderItem(0, carbonara,1, 's');

        OrderItem order2 = new OrderItem(0, algio_olio, 2, 's');

        Cart cart = new Cart();
        cart.pushOrder(order1).pushOrder(order2);

        for (int i = 0; i < cart.getOrderCount(); i++) {
            System.out.println(cart.getOrders().get(i).getItem().getName());
        }
    }
}
