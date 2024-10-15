package presentation;

import javax.swing.*;

public class Applauncher2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherApp("hhh").setVisible(true);

            }
        });
    }
}
