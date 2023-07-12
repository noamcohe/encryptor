package menus;

public interface MenuChoice<T> {
    void display();
    T getChoice();
}
