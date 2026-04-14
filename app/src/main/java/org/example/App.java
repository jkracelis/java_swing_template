package org.example;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::createUI);
    }

    private static void createUI() {
        JFrame frame = new JFrame("My Swing App");

        JLabel label = new JLabel("Hello Swing 👋", SwingConstants.CENTER);

        JButton button = new JButton("Click me");
        button.addActionListener(e ->
                label.setText("Button clicked!")
        );

        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
