package src.model;

public class Table {
    private static int count = 0;
    private int id;
    private int seat_count;

    public Table(int seat_count) {
        setId(count++);
        setSeatCount(seat_count);
    }

    @Override
    public String toString() {
        return "ID: " + id;
    }

    // setters
    private void setId(int id) {
        if (id >= 0) { this.id = id; }
    }

    private void setSeatCount(int n) {
        if (n >= 0) { this.seat_count = n; }
    }

    // getters
    private int getCount() {
        return count;
    }

    private int getId() {
        return id;
    }

    public int getNumber() {
        return id + 1;
    }

    public int getSeatCount() {
        return seat_count;
    }
}
