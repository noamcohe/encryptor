package menus;

public interface MenuChoice<T> {
    T performAction();
    void display(String message);
}
