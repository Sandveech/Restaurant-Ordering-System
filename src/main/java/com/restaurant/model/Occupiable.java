package src.main.java.com.restaurant.model;

public interface Occupiable {
    Boolean occupy();
    void unoccupy();
    Boolean isOccupied();
}
