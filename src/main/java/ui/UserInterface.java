package ui;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class UserInterface {

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                new LoginView();
            } catch (IOException | InterruptedException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        });
    }
}
