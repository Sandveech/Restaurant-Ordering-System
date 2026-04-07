package src.main.java;

public class Customer {
    private int id;
    private int table;


    public Customer(int id, int table) {
        setId(id).setTable(table);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " + "Table: " + table;
    }

    // setters
    private Customer setId(int id) {
        this.id = id;
        return this;
    }

    public Customer setTable(int table) {
        this.table = table;
        return this;
    }

    // getters
    public int getID() {
        return id;
    }

    public int setTable() {
        return table;
    }
}
