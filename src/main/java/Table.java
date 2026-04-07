package src.main.java;

public class Table {
    private int id;


    public Table(int id) {
        setId(id);
    }

    @Override
    public String toString() {
        return "ID: " + id;
    }

    // setters
    private Table setId(int id) {
        this.id = id;
        return this;
    }

    // getters
    public int getId() {
        return id;
    }

}
