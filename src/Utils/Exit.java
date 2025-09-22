package Utils;

import javax.swing.*;

public class Exit {
    public static void Button(JFrame frame){
        JButton exit = new JButton("Exit");
        exit.setBounds(160, 375, 150, 30);
        frame.add(exit);

        String[] buttons = {"Yes", "No"};
        exit.addActionListener(e -> {
            // Confirm exit with user
            int confirm = JOptionPane.showOptionDialog(
                    frame,
                    "Do you want to exit?",
                    "Confirm",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    buttons,
                    buttons[0]
            );

            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0); // Terminate application
            }
        });
    }
}
