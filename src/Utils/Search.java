package Utils;

import Models.Products;

import javax.swing.*;
import java.util.Map;

public class Search {
    public static void Button(JFrame frame){
        JButton searchProducts = new JButton("Search products");
        searchProducts.setBounds(160, 225, 150, 30);
        frame.add(searchProducts);

        searchProducts.addActionListener(e -> {
            if (Storage.products.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No products available!");
                return;
            }

            // Create search dialog
            JTextField textField = new JTextField(10);
            JOptionPane findPane = new JOptionPane(
                    new Object[]{"Write the name of the product", textField},
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.OK_CANCEL_OPTION
            );
            JDialog dialogFind = findPane.createDialog(frame, "Find Product");
            dialogFind.setLocation(825, 450);

            boolean foundProduct = false;

            // Search loop
            while (!foundProduct) {
                dialogFind.setModal(true);
                dialogFind.setVisible(true);

                Object selectedValue = findPane.getValue();
                if (selectedValue != null && (int) selectedValue == JOptionPane.OK_OPTION) {
                    String input = textField.getText().trim().toLowerCase();
                    if (!input.isEmpty()) {
                        // Search through all products
                        for (Map.Entry<Integer, Products> entry : Storage.products.entrySet()) {
                            Products p = entry.getValue();
                            if (p.getProductName().equalsIgnoreCase(input)) {
                                // Product found - display info
                                String message = "Product Found:\n" +
                                        "ID: " + entry.getKey() +
                                        " - Name: " + p.getProductName() +
                                        " - Price: " + p.getProductPrice() +
                                        " - Stock: " + p.getProductStock() +
                                        " - Description: " + p.getDescription();
                                JOptionPane.showMessageDialog(frame, message, "Product Info", JOptionPane.INFORMATION_MESSAGE);
                                foundProduct = true;
                                break;
                            }
                        }
                        if (!foundProduct) {
                            JOptionPane.showMessageDialog(frame, "Product not found!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "You need to enter a product name!");
                    }
                } else {
                    // User canceled search
                    foundProduct = true;
                }
            }
        });
    }
}
