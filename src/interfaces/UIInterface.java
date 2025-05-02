package interfaces;

public interface UIInterface {

    // All UIs must have a menu printed upon entering their scope
    void printMenu();
    int handleMenuInput();
    void handleMenuChoice(int input);

}
