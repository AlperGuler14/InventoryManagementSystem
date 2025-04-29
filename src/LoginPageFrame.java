import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPageFrame extends JFrame implements ActionListener {
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    //  kullanıcı adı ve şifre  :)
    private final String USERNAME = "admin";
    private final String PASSWORD = "1234";

    public LoginPageFrame() {
        setTitle("Login Sayfası");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Kullanıcı Adı:");
        userLabel.setBounds(20, 20, 100, 25);
        panel.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(130, 20, 160, 25);
        panel.add(userTextField);

        JLabel passwordLabel = new JLabel("Şifre:");
        passwordLabel.setBounds(20, 60, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 60, 160, 25);
        panel.add(passwordField);

        loginButton = new JButton("Giriş Yap");
        loginButton.setBounds(130, 100, 100, 25);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        messageLabel = new JLabel("");
        messageLabel.setBounds(20, 130, 300, 25);
        panel.add(messageLabel);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            dispose(); // Giriş ekranını kapatır
            new MainFrame(username);

        } else {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Hatalı kullanıcı adı veya şifre.");
        }
    }

    public static void main(String[] args) {
        new LoginPageFrame();
    }
}
