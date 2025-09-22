package Utils;


import Models.Products;

import javax.swing.*;

import java.util.Map;

public class VerifyName {
    public static Boolean Verify(String name){
        if (name == null) return false;

        String checkProduct = name.toLowerCase().trim();

        for (Map.Entry<Integer, Products> entry : Storage.products.entrySet()) {
            Products product = entry.getValue();
            if (product.getProductName().equalsIgnoreCase(checkProduct)) {
                return false;
            }
        }

        return true;

}}
