package src.main.java;

public class Receipt {
    private int id; 
    private Table table;
    private Cart cart;

    public Receipt(int id, Table table, Cart cart) {
        setId(id).setTable(table).setCart(cart);
    }

    // setters
    private Receipt setId(int id) {
        this.id = id;
        return this;
    }

    private Receipt setTable(Table table) {
        this.table = table;
        return this;
    }

    private Receipt setCart(Cart cart) {
        this.cart = cart;
        return this;
    }

    // getters
    public int getId() {
        return id;
    }

    public Table getTable() {
        return table;
    }

    public Cart getCart() {
        return cart;
    }

}