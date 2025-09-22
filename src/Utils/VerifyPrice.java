package Utils;

import javax.swing.*;

public class VerifyPrice {
    public static boolean Verify (String price){
        try {
            if (price == null || price.isEmpty()) return false;

            if (!price.matches("\\d+(\\.\\d+)?")) {
                return false;
            }

            Double.parseDouble(price);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }
}
