package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginView extends JFrame implements ActionListener {
    private Socket client;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel alert;

    public LoginView(Socket client) {
        this.client = client;
        alert = new JLabel("Tài khoản hoặc mật khẩu không chính xác");
        alert.setVisible(false);
        init();
    }

    private void init() {
        this.setTitle("Login View");
        this.setSize(600, 600);
        Font font = new Font("Arial", Font.BOLD, 40);
//        this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());



        JLabel loginLabel = new JLabel("Đăng nhập");
        loginLabel.setFont(font);

        JPanel inforPanel = new JPanel();

        JLabel usernameLabel = new JLabel("Tên đăng nhập");
        usernameField = new JTextField(50);
        JLabel passwordLabel = new JLabel("Mật khẩu");
        passwordField = new JPasswordField(50);
        alert.setVisible(false);

        inforPanel.add(usernameLabel);
        inforPanel.add(usernameField);
        inforPanel.add(passwordLabel);
        inforPanel.add(passwordField);
        inforPanel.add(alert);

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.addActionListener(this);

        JPanel jTextPanel = new JPanel();
        jTextPanel.setLayout(new FlowLayout());
        jTextPanel.add(loginLabel);

        this.setLayout(new FlowLayout());

//        this.add(boardPanel, BorderLayout.CENTER);
//        this.add(menuPanel, BorderLayout.EAST);


        this.setLayout(new BorderLayout());
        this.add(jTextPanel, BorderLayout.NORTH);
        this.add(inforPanel, BorderLayout.CENTER);
        this.add(loginButton, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals("Đăng nhập")) {
            if (usernameField.getText().equals("client1") && passwordField.getText().equals("****")) {
                System.out.println(usernameField.getText() + "-" + passwordField.getText());
                this.setVisible(false);
                try {
                    PrintWriter pw = new PrintWriter(client.getOutputStream());
                    pw.write(usernameField.getText() + "-" + passwordField.getText());
                    pw.flush();
                    pw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    client.close();
                    new BoardView("room master");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                alert.setVisible(false);
            } else {
                alert.setVisible(true);
            }
        }
    }
}
