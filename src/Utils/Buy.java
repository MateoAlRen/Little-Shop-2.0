package Utils;

import Models.CrudFunctions;
import Models.Products;
import Service.ShoppingCart;

import javax.swing.*;
import java.util.Map;

public class Buy {
    static int billId = 1;
    public static void Button(JFrame frame){
        JButton buyProducts = new JButton("Buy products");
        buyProducts.setBounds(160, 275, 150, 30);
        frame.add(buyProducts);

        buyProducts.addActionListener(e -> {
            boolean shopping = false;

            // Main shopping loop - allows multiple purchases
            while (!shopping){
                // Display products in a non-modal dialog
                StringBuilder message = new StringBuilder("Products:\n");
                if (Storage.products.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "There's no products!");
                    return;
                }

                // Build product display string
                for (Map.Entry<Integer, Products> entry : Storage.products.entrySet()) {
                    int id = entry.getKey();
                    Products products = entry.getValue();
                    message.append("ID: ").append(id)
                            .append(" - Name: ").append(products.getProductName())
                            .append(" - Price: ").append(products.getProductPrice())
                            .append(" - Stock: ").append(products.getProductStock())
                            .append(" - Description: ").append(products.getDescription())
                            .append("\n");
                }

                // Create non-modal product list dialog
                JOptionPane showPane = new JOptionPane(message.toString(), JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = showPane.createDialog(null, "Products");
                dialog.setLocation(550, 450);
                dialog.setModal(false); // Non-modal so user can see while shopping
                dialog.setVisible(true);

                // Product selection section
                String pName = null;
                JTextField textField = new JTextField(10);
                JOptionPane buyPane = new JOptionPane(
                        new Object[]{"Write the name of the product", textField},
                        JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.OK_CANCEL_OPTION
                );
                JDialog dialogBuy = buyPane.createDialog(null, "Select a product");
                dialogBuy.setLocation(1150, 450);

                // Product name validation loop
                boolean verifyName = false;
                int productId = -1;
                while (!verifyName) {
                    dialogBuy.setModal(true);
                    dialogBuy.setVisible(true);

                    Object selectedValue = buyPane.getValue();
                    if (selectedValue != null && (int) selectedValue == JOptionPane.OK_OPTION) {
                        String input = textField.getText().trim().toLowerCase();
                        boolean found = false;
                        int stock = 0;

                        if (!input.isEmpty()) {
                            pName = input;
                            // Search for product by name
                            for (Map.Entry<Integer, Products> entry : Storage.products.entrySet()) {
                                Products p = entry.getValue();
                                stock = p.getProductStock();
                                if (p.getProductName().equalsIgnoreCase(pName)) {
                                    found = true;
                                    productId = entry.getKey();
                                    break;
                                }
                            }

                            if (found){
                                if (stock == 0){
                                    JOptionPane.showMessageDialog(null, "There's not stock...");
                                } else {
                                    verifyName = true;
                                    pName = input;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "The product doesn't exists!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "You need to complete the field!");
                        }
                    } else {
                        // User canceled
                        JOptionPane.showMessageDialog(null, "Canceled!");
                        dialog.setVisible(false);
                        return;
                    }
                }

                // Quantity selection section
                int quantity = 0;
                Products product = Storage.products.get(productId);
                JTextField priceField = new JTextField(10);
                JOptionPane quantityPane = new JOptionPane(
                        new Object[]{"How many?", priceField},
                        JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.OK_CANCEL_OPTION
                );
                JDialog dialogQuantity = quantityPane.createDialog(null, "Quantity");
                dialogQuantity.setLocation(1150, 450);

                boolean hasStock = false;

                // Quantity validation loop
                while (!hasStock){
                    dialogQuantity.setModal(true);
                    dialogQuantity.setVisible(true);

                    Object selectedValue = quantityPane.getValue();
                    if (selectedValue != null && (int) selectedValue == JOptionPane.OK_OPTION) {
                        String input = priceField.getText().trim().toLowerCase();
                        if (!input.isEmpty()) {
                            try {
                                int content = Integer.parseInt(input);
                                int stock = product.getProductStock();

                                if (content <= 0){
                                    JOptionPane.showMessageDialog(null,"You need to put a real content!");
                                } else if (content <= stock) {
                                    // Reduce stock and set quantity
                                    product.getOffStock(content);
                                    quantity = content;
                                    hasStock = true;
                                } else {
                                    JOptionPane.showMessageDialog(null,"You need to put a real content!");
                                }
                            } catch (NumberFormatException ex){
                                JOptionPane.showMessageDialog(frame, "That's a no valid stock!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "You need to complete the field!");
                        }
                    } else {
                        // User canceled
                        JOptionPane.showMessageDialog(null, "Canceled!");
                        dialog.setVisible(false);
                        return;
                    }
                }

                // Calculate total and create shopping cart entry
                Products products = Storage.products.get(productId);
                double price = products.getProductPrice();
                double total = price * quantity;

                Service.ShoppingCart bill = new ShoppingCart(pName, quantity, price, total);
                BillStorage.billing.put(Integer.valueOf(billId), bill);
                billId++;

                JOptionPane.showMessageDialog(frame,"Product: " + pName + " quantity: " + quantity + " price: " + price + " total: " + total );


                // Ask if user wants to continue shopping
                int answer = JOptionPane.showConfirmDialog(
                        frame,
                        "Do you want to buy another product?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (answer == JOptionPane.NO_OPTION) {
                    dialog.setVisible(false);
                    shopping = true; // Exit shopping loop
                } else if (answer == JOptionPane.YES_OPTION){
                    dialog.setVisible(false); // Continue shopping
                }
            }

        });
    }
}
