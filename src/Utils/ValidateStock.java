package Utils;

import javax.swing.*;

public class ValidateStock {
    public static boolean VStock (String stock){
        try {
            if (stock == null || stock.isEmpty()) return false;

            if (!stock.matches("\\d+(\\.\\d+)?")) {
                return false;
            }

            Integer.parseInt(stock);
            return true;
        } catch (NumberFormatException error){
            return false;
        }
    }
}
