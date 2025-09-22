package Utils;

import Models.Products;
import Service.ElectricalAppliance;
import Service.Food;

import javax.swing.*;

public class Create {
    static int counter = 3;
    public static void button(JFrame frame){
        JButton addProduct = new JButton("Add a product");
        addProduct.setBounds(160, 125, 150, 30);
        frame.add(addProduct);

        addProduct.addActionListener(e -> {

            String productName = null;
            double productPrice = 0;
            String productDescription = null;
            int productStock = 0;


            String[] buttons = {"Food","Electrical appliance","Cancel"};
            int Type = JOptionPane.showOptionDialog(
                    frame,
                    "What type of product do you want to add?",
                    "Type",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    buttons,
                    buttons[0]
            );

            if (Type == 2){
                JOptionPane.showMessageDialog(frame, "Cancelled.");
                return;
            }

            boolean VerifyN = false;
            while (!VerifyN) {
                String name = JOptionPane.showInputDialog(null, "Write the name of the price");

                if (name == null){
                    JOptionPane.showMessageDialog(frame, "Cancelled.");
                    return;
                }

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name can't be null!");
                    continue;
                }

                boolean check = VerifyName.Verify(name);

                if (!check) {
                    JOptionPane.showMessageDialog(frame, "That name already exists!");
                } else {
                    productName = name;
                    VerifyN = true;
                }
            }

            boolean VerifyP = false;
            while (!VerifyP) {

                String price = JOptionPane.showInputDialog(null,"Write the price of the product");

                if (price == null){
                    JOptionPane.showMessageDialog(frame, "Cancelled.");
                    return;
                }

                if (price.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name can't be null!");
                    continue;
                }

                boolean checkP = VerifyPrice.Verify(price);

                if (!checkP){
                    JOptionPane.showMessageDialog(frame,"That's a not valid price!");
                } else {
                    productPrice = Double.parseDouble(price);
                    VerifyP = true;
                }

            }

            boolean VerifyD = false;
            while (!VerifyD){

                String description = JOptionPane.showInputDialog(frame, "Write a description");

                if (description == null){
                    JOptionPane.showMessageDialog(frame, "Cancelled.");
                    return;
                }

                if (description.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name can't be null!");
                    continue;
                }

                productDescription = description;
                VerifyD = true;

            }

            boolean verifyS = false;
            while (!verifyS){

                String stock = JOptionPane.showInputDialog(frame, "Write a stock");

                if (stock == null){
                    JOptionPane.showMessageDialog(frame, "Cancelled.");
                    return;
                }

                if (stock.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name can't be null!");
                    continue;
                }

                boolean checkStock = ValidateStock.VStock(stock);

                if (!checkStock){
                    JOptionPane.showMessageDialog(frame, "That's a not valid stock!");
                } else {
                    productStock = Integer.parseInt(stock);
                    verifyS = true;
                }
            }

            if (Type == 0){
                Products product = new Food(productName,productPrice,productDescription,productStock);
                Storage.products.put(counter,product);
                counter++;
            } else if (Type == 1){
                Products product = new ElectricalAppliance(productName,productPrice,productDescription,productStock);
                Storage.products.put(counter,product);
                counter++;
            }

            JOptionPane.showMessageDialog(frame,"Product added successfully!");

            System.out.println(productPrice);
        });
    }
}
