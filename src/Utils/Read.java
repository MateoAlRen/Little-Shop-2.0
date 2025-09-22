package Utils;

import Models.Products;

import javax.swing.*;
import java.util.Map;

public class Read {
    public static void Button(JFrame frame){
        JButton seeProducts = new JButton("See products");
        seeProducts.setBounds(160, 175, 150, 30);
        frame.add(seeProducts);

        seeProducts.addActionListener(e -> {
            StringBuilder message = new StringBuilder("Products:\n");

            // Check if inventory is empty
            if (Storage.products.isEmpty()){
                JOptionPane.showMessageDialog(frame, "There's no products to show!");
                return;
            }

            // Build product list string
            for (Map.Entry<Integer, Products> entry : Storage.products.entrySet()){
                int id = entry.getKey();
                Products product = entry.getValue();
                message.append("ID: ").append(id)
                        .append(" - Name: ").append(product.getProductName())
                        .append(" - Price: ").append(product.getProductPrice())
                        .append(" - Stock: ").append(product.getProductStock())
                        .append(" - Description: ").append(product.getDescription())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(frame, message.toString(), "Products", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
