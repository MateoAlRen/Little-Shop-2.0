package Utils;

import Models.Products;
import Service.ProductService;

import javax.swing.*;
import java.util.Map;

public class Statistics {
    public static void Button(JFrame frame){
        JButton priceProducts = new JButton("Lowest and Biggest Prices");
        priceProducts.setBounds(160, 325, 150, 30);
        frame.add(priceProducts);

        priceProducts.addActionListener(e -> {
            if (Storage.products.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No products to show!");
                return;
            }

            // Initialize tracking variables for max price product
            int maxProductId = 0;
            String maxName = "";
            double maxValue = Double.MIN_VALUE;

            // Initialize tracking variables for min price product
            int minProductId = 0;
            String minName = "";
            double minValue = Double.MAX_VALUE;

            // Find products with highest and lowest prices
            for (Map.Entry<Integer, Products> entry : Storage.products.entrySet()) {
                Products product = entry.getValue();
                int idProduct = entry.getKey();
                double price = product.getProductPrice();
                String name = product.getProductName();

                // Check for highest price
                if (price > maxValue) {
                    maxValue = price;
                    maxProductId = idProduct;
                    maxName = name;
                }

                // Check for lowest price
                if (price < minValue) {
                    minValue = price;
                    minProductId = idProduct;
                    minName = name;
                }
            }

            // Display results
            String message = "Highest price product:\n" +
                    "ID: " + maxProductId + " - Name: " + maxName + " - Price: $" + maxValue + "\n\n" +
                    "Lowest price product:\n" +
                    "ID: " + minProductId + " - Name: " + minName + " - Price: $" + minValue;

            JOptionPane.showMessageDialog(frame, message, "Price Extremes", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
