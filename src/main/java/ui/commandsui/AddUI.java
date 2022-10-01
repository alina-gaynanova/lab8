package ui.commandsui;

import ui.MainView;
import utilities.organizations.Address;
import utilities.organizations.Coordinates;
import utilities.organizations.Organization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import static ui.LoginView.locale;

public class AddUI {
    private ResourceBundle resourceBundle;

    private JLabel nameLabel, xLabel, yLabel, annualTurnoverLabel, typeLabel, zipcodeLabel;
    private JTextField nameField, xField, yField, annualTurnoverField, zipcodeField;
    JComboBox<String> typeBox;
    private JFrame commandFrame;

    private Organization.OrganizationType type = Organization.OrganizationType.PUBLIC;

    public AddUI() throws IOException {
        resourceBundle = ResourceBundle.getBundle("ui.lang.Bundle", locale);
        commandFrame = new JFrame("Login");
        final Image backgroundImage = javax.imageio.ImageIO.read(new File("C:\\Users\\ticug\\Desktop\\ITMOStudy\\Alina\\lab7\\src\\main\\java\\ui\\img\\Background.png"));
        commandFrame.setContentPane(new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });

        typeBox = new JComboBox<>();
        typeBox.setEditable(false);
        typeBox.addItem("PUBLIC");
        typeBox.addItem("PRIVATE_LIMITED_COMPANY");
        typeBox.addItem("OPEN_JOINT_STOCK_COMPANY");
        typeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String typeName = e.getItem().toString();
                if (typeName.equals("PUBLIC")) {
                    type = Organization.OrganizationType.PUBLIC;
                } else if (typeName.equals("PRIVATE_LIMITED_COMPANY")) {
                    type = Organization.OrganizationType.PRIVATE_LIMITED_COMPANY;
                } else {
                    type = Organization.OrganizationType.OPEN_JOINT_STOCK_COMPANY;
                }
            }
        });

        nameLabel = new JLabel(resourceBundle.getString("name"));
        xLabel = new JLabel(resourceBundle.getString("x"));
        yLabel = new JLabel(resourceBundle.getString("y"));
        annualTurnoverLabel = new JLabel(resourceBundle.getString("annualTurnover"));
        typeLabel = new JLabel(resourceBundle.getString("type"));
        zipcodeLabel = new JLabel(resourceBundle.getString("zipcode"));

        nameField = new JTextField(20);
        xField = new JTextField(20);
        yField = new JTextField(20);
        annualTurnoverField = new JTextField(20);
        zipcodeField = new JTextField(20);

        //создаем FlowLayout для текстовых полей
        FlowLayout textFieldsLayout = new FlowLayout();
        textFieldsLayout.setAlignment(4);

        //создание Panel для ввода имени пользователя
        JPanel namePanel = new JPanel();
        namePanel.setLayout(textFieldsLayout);
        namePanel.setBackground(Color.decode("#b3b0a9"));
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel xPanel = new JPanel();
        xPanel.setLayout(textFieldsLayout);
        xPanel.setBackground(Color.decode("#b3b0a9"));
        xPanel.add(xLabel);
        xPanel.add(xField);

        JPanel yPanel = new JPanel();
        yPanel.setLayout(textFieldsLayout);
        yPanel.setBackground(Color.decode("#b3b0a9"));
        yPanel.add(yLabel);
        yPanel.add(yField);

        JPanel annualTurnoverPanel = new JPanel();
        annualTurnoverPanel.setLayout(textFieldsLayout);
        annualTurnoverPanel.setBackground(Color.decode("#b3b0a9"));
        annualTurnoverPanel.add(annualTurnoverLabel);
        annualTurnoverPanel.add(annualTurnoverField);

        JPanel typePanel = new JPanel();
        typePanel.setLayout(textFieldsLayout);
        typePanel.setBackground(Color.decode("#b3b0a9"));
        typePanel.add(typeLabel);
        typePanel.add(typeBox);

        JPanel zipcodePanel = new JPanel();
        zipcodePanel.setLayout(textFieldsLayout);
        zipcodePanel.setBackground(Color.decode("#b3b0a9"));
        zipcodePanel.add(zipcodeLabel);
        zipcodePanel.add(zipcodeField);

        JButton addButton = new JButton(resourceBundle.getString("add"));
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            Float x = Float.valueOf(xField.getText());
            double y = Double.parseDouble(yField.getText());
            Coordinates coordinates = new Coordinates(x, y);
            long annualTurnover = Long.parseLong(annualTurnoverField.getText());
            String zipcode = zipcodeField.getText();
            Address address = new Address(zipcode);

            MainView.complexArgs.add(name);
            MainView.complexArgs.add(coordinates);
            MainView.complexArgs.add(annualTurnover);
            MainView.complexArgs.add(type);
            MainView.complexArgs.add(address);

            commandFrame.setVisible(false);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#b3b0a9"));
        buttonPanel.add(addButton);

        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new FlowLayout());
        commandPanel.setBackground(Color.decode("#b3b0a9"));
        commandPanel.setBounds(50, 100, 400, 300);
        commandPanel.add(namePanel);
        commandPanel.add(xPanel);
        commandPanel.add(yPanel);
        commandPanel.add(annualTurnoverPanel);
        commandPanel.add(typePanel);
        commandPanel.add(zipcodePanel);
        commandPanel.add(buttonPanel);

        commandFrame.add(commandPanel);

        //установка Frame
        commandFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        commandFrame.setSize(520,650);
        commandFrame.setLayout(null);
        commandFrame.setVisible(true);
    }
}
