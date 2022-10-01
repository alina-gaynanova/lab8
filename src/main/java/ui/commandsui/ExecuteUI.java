package ui.commandsui;

import ui.MainView;
import utilities.organizations.Address;
import utilities.organizations.Coordinates;
import utilities.organizations.Organization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import static ui.LoginView.locale;

public class ExecuteUI {
    private ResourceBundle resourceBundle;

    private JLabel filenameLabel;
    private JTextField filenameField;
    private JFrame commandFrame;

    public ExecuteUI() throws IOException {
        resourceBundle = ResourceBundle.getBundle("ui.lang.Bundle", locale);
        commandFrame = new JFrame("Login");
        final Image backgroundImage = javax.imageio.ImageIO.read(new File("C:\\Users\\ticug\\Desktop\\ITMOStudy\\Alina\\lab7\\src\\main\\java\\ui\\img\\Background.png"));
        commandFrame.setContentPane(new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });

        filenameLabel = new JLabel(resourceBundle.getString("filename"));
        filenameField = new JTextField(20);

        //создаем FlowLayout для текстовых полей
        FlowLayout textFieldsLayout = new FlowLayout();
        textFieldsLayout.setAlignment(4);

        //создание Panel для ввода имени пользователя
        JPanel namePanel = new JPanel();
        namePanel.setLayout(textFieldsLayout);
        namePanel.setBackground(Color.decode("#b3b0a9"));
        namePanel.add(filenameLabel);
        namePanel.add(filenameField);

        JButton executeButton = new JButton(resourceBundle.getString("execute"));
        executeButton.addActionListener(e -> {
            String name = filenameField.getText();

            MainView.words.add(name);

            commandFrame.setVisible(false);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#b3b0a9"));
        buttonPanel.add(executeButton);

        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new FlowLayout());
        commandPanel.setBackground(Color.decode("#b3b0a9"));
        commandPanel.setBounds(50, 100, 400, 100);
        commandPanel.add(namePanel);
        commandPanel.add(buttonPanel);

        commandFrame.add(commandPanel);

        //установка Frame
        commandFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        commandFrame.setSize(520,650);
        commandFrame.setLayout(null);
        commandFrame.setVisible(true);
    }
}
