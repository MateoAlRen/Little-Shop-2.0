package Utils;

import Models.Products;
import Service.Food;

import javax.swing.*;

public class Container {
    public static JFrame createWindow() {
        Products milk = new Food("milk",2,"hi",2);
        Storage.products.put(1, milk);
        Products asd = new Food("water",23,"hi water",23);
        Storage.products.put(2, asd);
        // Set the container and his title.
        JFrame container = new JFrame("LittleShop - 2.0");
        container.setSize(500, 500);
        // Exit Default function.
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Position in the screen.
        container.setLocationRelativeTo(null);
        container.setLayout(null);
        // Message.
        JLabel message = new JLabel("Welcome to the new little shop!");
        message.setBounds(130, 20, 500, 30);
        // Functions.
        container.add(message);
        container.setVisible(true);
        return container;
    }
}
