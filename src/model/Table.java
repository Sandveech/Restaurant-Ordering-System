package src.model;

public class Table {
    private static int count = 0;
    private int id;

    public Table() {
        setId(count++);
    }

    @Override
    public String toString() {
        return "ID: " + id;
    }

    // setters
    private Table setId(int id) {
        if (id >= 0) { this.id = id; }
        return this;
    }

    // getters
    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return id + 1;
    }
}
