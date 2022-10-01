package ui;

import utilities.commandsutilities.CommandList;
import utilities.commandsutilities.CommandReader;
import utilities.iowork.SocketReader;
import utilities.iowork.SocketWriter;
import utilities.users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginView {
    public static SocketWriter writer;
    public static SocketReader reader;

    private static Socket clientSocket;

    private static CommandReader commandReader;
    public static User user;


    private final JPanel logPane = new JPanel();

    public static Locale locale = new Locale("ru", "RU");
    private ResourceBundle resourceBundle;

    private JFrame loginFrame;
    private JLabel usernameLabel1, usernameLabel2, passwordLabel1, passwordLabel2;
    private JTextField usernameField1, usernameField2, passwordField1, passwordField2;
    private JButton signInButton, signUpButton;
    public static JLabel rusLabel, czechLabel, bulgLabel, espLabel;

    public LoginView() throws IOException, NoSuchAlgorithmException, InterruptedException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        //подключение к сообщениям на русском языке
        resourceBundle = ResourceBundle.getBundle("ui.lang.Bundle", locale);

        //подключение к серверу
        connect();

        //создание всех элементов
        loginFrame = new JFrame("Login");
        final Image backgroundImage = javax.imageio.ImageIO.read(new File("C:\\Users\\ticug\\Desktop\\ITMOStudy\\Alina\\lab7\\src\\main\\java\\ui\\img\\Background.png"));
        loginFrame.setContentPane(new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });
        usernameLabel1 = new JLabel(resourceBundle.getString("username"));
        passwordLabel1 = new JLabel(resourceBundle.getString("password"));
        usernameLabel2 = new JLabel(resourceBundle.getString("username"));
        passwordLabel2 = new JLabel(resourceBundle.getString("password"));

        usernameField1 = new JTextField(20);
        passwordField1 = new JPasswordField(20);
        usernameField2 = new JTextField(20);
        passwordField2 = new JPasswordField(20);

        signInButton = new JButton(resourceBundle.getString("sign_in"));
        signUpButton = new JButton(resourceBundle.getString("sign_up"));

        rusLabel = new JLabel("Русский");
        rusLabel.setForeground(Color.white);
        czechLabel = new JLabel("Čeština");
        bulgLabel = new JLabel("Български");
        espLabel = new JLabel("Español");

        //создаем FlowLayout для текстовых полей
        FlowLayout textFieldsLayout = new FlowLayout();
        textFieldsLayout.setAlignment(4);

        //создание Panel для ввода имени пользователя
        JPanel usernamePanel1 = new JPanel();
        usernamePanel1.setLayout(textFieldsLayout);
        usernamePanel1.setBackground(Color.decode("#b3b0a9"));
        usernamePanel1.add(usernameLabel1);
        usernamePanel1.add(usernameField1);

        JPanel usernamePanel2 = new JPanel();
        usernamePanel2.setLayout(textFieldsLayout);
        usernamePanel2.setBackground(Color.decode("#b3b0a9"));
        usernamePanel2.add(usernameLabel2);
        usernamePanel2.add(usernameField2);

        //аналогично для пароля
        JPanel passwordPanel1 = new JPanel();
        passwordPanel1.setLayout(textFieldsLayout);
        passwordPanel1.setBackground(Color.decode("#b3b0a9"));
        passwordPanel1.add(passwordLabel1);
        passwordPanel1.add(passwordField1);

        JPanel passwordPanel2 = new JPanel();
        passwordPanel2.setLayout(textFieldsLayout);
        passwordPanel2.setBackground(Color.decode("#b3b0a9"));
        passwordPanel2.add(passwordLabel2);
        passwordPanel2.add(passwordField2);

        //аналогично для кнопок
        JPanel buttonPanelSignIn = new JPanel();
        buttonPanelSignIn.setBackground(Color.decode("#b3b0a9"));
        buttonPanelSignIn.setSize(200, 30);
        buttonPanelSignIn.add(signInButton);

        JPanel buttonPanelSignUp = new JPanel();
        buttonPanelSignUp.setBackground(Color.decode("#b3b0a9"));
        buttonPanelSignUp.setSize(200, 30);
        buttonPanelSignUp.add(signUpButton);

        //блок для входа
        JPanel signInPanel = new JPanel();
        signInPanel.setLayout(new BorderLayout());
        signInPanel.setBackground(Color.decode("#b3b0a9"));
        signInPanel.setBounds(50, 100, 400, 100);
        signInPanel.add(usernamePanel1, BorderLayout.PAGE_START);
        signInPanel.add(passwordPanel1, BorderLayout.CENTER);
        signInPanel.add(buttonPanelSignIn, BorderLayout.AFTER_LAST_LINE);

        //блок для регистрации
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new BorderLayout());
        signUpPanel.setBackground(Color.decode("#b3b0a9"));
        signUpPanel.setBounds(50, 250, 400, 100);
        signUpPanel.add(usernamePanel2, BorderLayout.PAGE_START);
        signUpPanel.add(passwordPanel2, BorderLayout.CENTER);
        signUpPanel.add(buttonPanelSignUp, BorderLayout.AFTER_LAST_LINE);

        //блок для смены языков
        JPanel langPanel = new JPanel();
        langPanel.setLayout(new FlowLayout());
        langPanel.setBackground(Color.decode("#b3b0a9"));
        langPanel.setBounds(0, 560, 500, 30);
        langPanel.add(rusLabel);
        langPanel.add(czechLabel);
        langPanel.add(bulgLabel);
        langPanel.add(espLabel);

        //добавление всех элементов в Frame
        loginFrame.add(signInPanel);
        loginFrame.add(signUpPanel);
        loginFrame.add(langPanel);

        //добавляем всякие Listeners
        rusLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                locale = new Locale("ru", "RU");
                resourceBundle = ResourceBundle.getBundle("ui.lang.Bundle", locale);
                rusLabel.setForeground(Color.white);
                czechLabel.setForeground(Color.black);
                bulgLabel.setForeground(Color.black);
                espLabel.setForeground(Color.black);
                changeLang();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rusLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rusLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        czechLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                locale = new Locale("ces", "CZE");
                resourceBundle = ResourceBundle.getBundle("ui.lang.Bundle", locale);
                rusLabel.setForeground(Color.black);
                czechLabel.setForeground(Color.white);
                bulgLabel.setForeground(Color.black);
                espLabel.setForeground(Color.black);
                changeLang();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rusLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rusLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        bulgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                locale = new Locale("bul", "BGR");
                resourceBundle = ResourceBundle.getBundle("ui.lang.Bundle", locale);
                rusLabel.setForeground(Color.black);
                czechLabel.setForeground(Color.black);
                bulgLabel.setForeground(Color.white);
                espLabel.setForeground(Color.black);
                changeLang();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rusLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rusLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        espLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                locale = new Locale("spa", "ECU");
                resourceBundle = ResourceBundle.getBundle("ui.lang.Bundle", locale);
                rusLabel.setForeground(Color.black);
                czechLabel.setForeground(Color.black);
                bulgLabel.setForeground(Color.black);
                espLabel.setForeground(Color.white);
                changeLang();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rusLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rusLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        signInButton.addActionListener(new ButtonListener(false));
        signUpButton.addActionListener(new ButtonListener(true));

        //установка Frame
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(520,650);
        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
    }


    private void connect() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException, InterruptedException, InstantiationException, NoSuchAlgorithmException {
        try {
            setClientSocket();
        } catch (IOException e) {
            if (!reconnect()) {
                JOptionPane.showMessageDialog(logPane, resourceBundle.getString("server_broke"));
                close();
            }
        }

        CommandList.fillList("utilities.usercommands");

        reader = new SocketReader(clientSocket, logPane, resourceBundle);
        writer = new SocketWriter(clientSocket);

        commandReader = new CommandReader();
    }

    private static void close() throws IOException {
        writer.close();
        reader.close();
        clientSocket.close();
        System.exit(0);
    }

    private void setClientSocket() throws IOException {
        clientSocket = new Socket("localhost", 6543);
    }

    private boolean reconnect() throws InterruptedException {
        JOptionPane.showMessageDialog(logPane, resourceBundle.getString("server_unavailable"));
        boolean serverConnected = false;
        long end = System.currentTimeMillis() + 60000;
        while((System.currentTimeMillis() < end) && !serverConnected) {
            try {
                setClientSocket();
                serverConnected = true;
            } catch (IOException exception) {}
            Thread.sleep(100);
        }
        return serverConnected;
    }

    private void changeLang() {
        usernameLabel1.setText(resourceBundle.getString("username"));
        passwordLabel1.setText(resourceBundle.getString("password"));
        usernameLabel2.setText(resourceBundle.getString("username"));
        passwordLabel2.setText(resourceBundle.getString("password"));

        signInButton.setText(resourceBundle.getString("sign_in"));
        signUpButton.setText(resourceBundle.getString("sign_up"));
    }

    class ButtonListener implements ActionListener {
        private boolean isReg;

        protected ButtonListener(boolean isReg) {
            this.isReg = isReg;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String commandName, username, password;

            if (this.isReg) {
                commandName = "register";
                username = usernameField2.getText();
                password = passwordField2.getText();
            } else {
                commandName = "login";
                username = usernameField1.getText();
                password = passwordField1.getText();
            }

            try {
                ArrayList<Object> userArgs = null;
                try {
                    userArgs = commandReader.authorizeClient(commandName, username, password);
                } catch (IOException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException | NoSuchAlgorithmException ex) {
                    JOptionPane.showMessageDialog(loginFrame.getContentPane(), e.toString());
                    ex.printStackTrace();
                }
                writer.writeCommand(userArgs);
            } catch (NullPointerException | IOException ex) {
                JOptionPane.showMessageDialog(loginFrame.getContentPane(), e.toString());
                ex.printStackTrace();
            }

            int n = 0;
            String answer = "";
            try {
                n = reader.scanInt();
                answer = reader.scanLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (!answer.contains("successfully")) {
                JOptionPane.showMessageDialog(loginFrame.getContentPane(), answer + "\n" + resourceBundle.getString("try_again"));
                return;
            }

            --n;
            while (n > 1) {
                try {
                    reader.scanLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                --n;
            }

            try {
                user = reader.getUser();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            loginFrame.setVisible(false);
            try {
                new MainView();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
