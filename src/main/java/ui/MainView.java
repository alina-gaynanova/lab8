package ui;

import ui.commandsui.AddUI;
import ui.commandsui.ExecuteUI;
import utilities.commandsutilities.CommandReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static server.Server.writer;
import static ui.LoginView.reader;

public class MainView {
    private static CommandReader commandReader = new CommandReader();

    private ResourceBundle resourceBundle;

    private JFrame mainFrame;
    JButton addButton, addIfMaxButton, addIfMinButton, clearButton, countGreaterThanAnnualTurnoverButton, executeScriptButton,
            exitButton, historyButton, infoButton, showAscendingButton, showDescendingButton, removeByIdButton, showButton,
            updateIdButton, helpButton;

    public static ArrayList<String> words;
    public static ArrayList<Object> complexArgs;

    public MainView() throws IOException {
        //устанаваливаем подходящий язык
        resourceBundle = ResourceBundle.getBundle("ui.lang.Bundle", LoginView.locale);

        //ставим фон
        mainFrame = new JFrame("Main");
        final Image backgroundImage = javax.imageio.ImageIO.read(new File("C:\\Users\\ticug\\Desktop\\ITMOStudy\\Alina\\lab7\\src\\main\\java\\ui\\img\\Background.png"));
        mainFrame.setContentPane(new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });

        //создаем все кнопки
        helpButton = new JButton(resourceBundle.getString("help"));
        addButton = new JButton(resourceBundle.getString("add"));
        addIfMaxButton = new JButton(resourceBundle.getString("add_if_max"));
        addIfMinButton = new JButton(resourceBundle.getString("add_if_min"));
        clearButton = new JButton(resourceBundle.getString("clear"));
        countGreaterThanAnnualTurnoverButton = new JButton(resourceBundle.getString("count_greater_than_annual_turnover"));
        executeScriptButton = new JButton(resourceBundle.getString("execute_script"));
        exitButton = new JButton(resourceBundle.getString("exit"));
        historyButton = new JButton(resourceBundle.getString("history"));
        infoButton = new JButton(resourceBundle.getString("info"));
        showAscendingButton = new JButton(resourceBundle.getString("show_ascending"));
        showDescendingButton = new JButton(resourceBundle.getString("show_descending"));
        removeByIdButton = new JButton(resourceBundle.getString("remove_by_id"));
        showButton = new JButton(resourceBundle.getString("show"));
        updateIdButton = new JButton(resourceBundle.getString("update_id"));

        helpButton.addActionListener(new ButtonListener("help"));
        addButton.addActionListener(new ButtonListener("add"));
        clearButton.addActionListener(new ButtonListener("clear"));
        executeScriptButton.addActionListener(new ButtonListener("execute_script"));
        exitButton.addActionListener(new ButtonListener("exit"));
        historyButton.addActionListener(new ButtonListener("history"));
        infoButton.addActionListener(new ButtonListener("info"));
        showButton.addActionListener(new ButtonListener("show"));

        //создаем Panel для кнопок
        JPanel buttonPanelHelp = new JPanel();
        buttonPanelHelp.setBackground(Color.decode("#b3b0a9"));
        buttonPanelHelp.setSize(420, 30);
        buttonPanelHelp.add(helpButton);

        JPanel buttonPanelAdd = new JPanel();
        buttonPanelAdd.setBackground(Color.decode("#b3b0a9"));
        buttonPanelAdd.setSize(420, 30);
        buttonPanelAdd.add(addButton);

        JPanel buttonPanelAddIfMax = new JPanel();
        buttonPanelAddIfMax.setBackground(Color.decode("#b3b0a9"));
        buttonPanelAddIfMax.setSize(420, 30);
        buttonPanelAddIfMax.add(addIfMaxButton);

        JPanel buttonPanelAddIfMin = new JPanel();
        buttonPanelAddIfMin.setBackground(Color.decode("#b3b0a9"));
        buttonPanelAddIfMin.setSize(420, 30);
        buttonPanelAddIfMin.add(addIfMinButton);

        JPanel buttonPanelClear = new JPanel();
        buttonPanelClear.setBackground(Color.decode("#b3b0a9"));
        buttonPanelClear.setSize(420, 30);
        buttonPanelClear.add(clearButton);

        JPanel buttonPanelCountGreater = new JPanel();
        buttonPanelCountGreater.setBackground(Color.decode("#b3b0a9"));
        buttonPanelCountGreater.setSize(420, 30);
        buttonPanelCountGreater.add(countGreaterThanAnnualTurnoverButton);

        JPanel buttonPanelExecute = new JPanel();
        buttonPanelExecute.setBackground(Color.decode("#b3b0a9"));
        buttonPanelExecute.setSize(420, 30);
        buttonPanelExecute.add(executeScriptButton);

        JPanel buttonPanelExit = new JPanel();
        buttonPanelExit.setBackground(Color.decode("#b3b0a9"));
        buttonPanelExit.setSize(420, 30);
        buttonPanelExit.add(exitButton);

        JPanel buttonPanelHistory = new JPanel();
        buttonPanelHistory.setBackground(Color.decode("#b3b0a9"));
        buttonPanelHistory.setSize(420, 30);
        buttonPanelHistory.add(historyButton);

        JPanel buttonPanelInfo = new JPanel();
        buttonPanelInfo.setBackground(Color.decode("#b3b0a9"));
        buttonPanelInfo.setSize(420, 30);
        buttonPanelInfo.add(infoButton);

        JPanel buttonPanelShowAsc = new JPanel();
        buttonPanelShowAsc.setBackground(Color.decode("#b3b0a9"));
        buttonPanelShowAsc.setSize(420, 30);
        buttonPanelShowAsc.add(showAscendingButton);

        JPanel buttonPanelShowDesc = new JPanel();
        buttonPanelShowDesc.setBackground(Color.decode("#b3b0a9"));
        buttonPanelShowDesc.setSize(420, 30);
        buttonPanelShowDesc.add(showDescendingButton);

        JPanel buttonPanelRemoveById = new JPanel();
        buttonPanelRemoveById.setBackground(Color.decode("#b3b0a9"));
        buttonPanelRemoveById.setSize(420, 30);
        buttonPanelRemoveById.add(removeByIdButton);

        JPanel buttonPanelShow = new JPanel();
        buttonPanelShow.setBackground(Color.decode("#b3b0a9"));
        buttonPanelShow.setSize(420, 30);
        buttonPanelShow.add(showButton);

        JPanel buttonPanelUpdateId = new JPanel();
        buttonPanelUpdateId.setBackground(Color.decode("#b3b0a9"));
        buttonPanelUpdateId.setSize(420, 30);
        buttonPanelUpdateId.add(updateIdButton);

        //блок для смены языков
        JPanel langPanel = new JPanel();
        langPanel.setLayout(new FlowLayout());
        langPanel.setBackground(Color.decode("#b3b0a9"));
        langPanel.setBounds(0, 560, 500, 30);
        langPanel.add(LoginView.rusLabel);
        langPanel.add(LoginView.czechLabel);
        langPanel.add(LoginView.bulgLabel);
        langPanel.add(LoginView.espLabel);

        //общий Panel
        JPanel mainPanel = new JPanel();
        FlowLayout mainLayout = new FlowLayout();
        mainLayout.setAlignment(0);
        mainPanel.setLayout(mainLayout);
        mainPanel.setBackground(Color.decode("#b3b0a9"));
        mainPanel.setBounds(120, 50, 250, 500);

        mainPanel.add(buttonPanelHelp);
        mainPanel.add(buttonPanelAdd);
        /*mainPanel.add(buttonPanelAddIfMax);
        mainPanel.add(buttonPanelAddIfMin);*/
        mainPanel.add(buttonPanelClear);
        //mainPanel.add(buttonPanelCountGreater);
        mainPanel.add(buttonPanelExecute);
        mainPanel.add(buttonPanelHistory);
        mainPanel.add(buttonPanelInfo);
        /*mainPanel.add(buttonPanelRemoveById);
        mainPanel.add(buttonPanelUpdateId);*/
        mainPanel.add(buttonPanelShow);
        mainPanel.add(buttonPanelShowAsc);
        mainPanel.add(buttonPanelShowDesc);
        mainPanel.add(buttonPanelExit);

        //добавляем элементы
        mainFrame.add(mainPanel);
        mainFrame.add(langPanel);

        //установка Frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(520,650);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
    }

    class ButtonListener implements ActionListener {
        private String commandName;

        protected ButtonListener(String commandName) {
            this.commandName = commandName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            words = new ArrayList<>();
            complexArgs = new ArrayList<>();

            words.add(commandName);

            if (commandName.equals("exit")) {
                try {
                    mainFrame.setVisible(false);
                    new LoginView();
                    return;
                } catch (IOException | NoSuchAlgorithmException | InterruptedException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException ex) {
                    ex.printStackTrace();
                }
            }

            switch (commandName) {
                case "add":
                case "add_if_min" :
                case "add_if_max":
                    try {
                        new AddUI();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;

                case "execute_script":
                    try {
                        new ExecuteUI();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
            }

            ArrayList<Object> commandArgs;
            try {
                commandArgs = commandReader.commandToClientUI();
                LoginView.writer.writeCommand(commandArgs);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            int n = 0;
            try {
                n = reader.scanInt();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String output = "";
            for (int i = 0; i < n; i++) {
                try {
                    output += reader.scanLine() + "\n";
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            if (commandName.equals("show") || commandName.equals("print_ascending") || commandName.equals("print_descending")) {
                JOptionPane.showMessageDialog(mainFrame.getContentPane(), output);
            } else {
                JOptionPane.showMessageDialog(mainFrame.getContentPane(), output);
            }
        }
    }
}
