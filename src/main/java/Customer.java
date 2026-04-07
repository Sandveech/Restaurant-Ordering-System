package src.main.java;

public class Customer {
    private int id;
    private String name;


    public Customer(int id, String name) {
        setId(id);
        setName(name);
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }

    // setters
    private Customer setId(int id) {
        this.id = id;
        return this;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    // getters
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }
}
