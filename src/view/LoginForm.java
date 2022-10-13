package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

import client.Client;
import message.LoginMessageRequest;
import model.User;

import java.awt.Font;
import java.awt.Color;
import java.io.IOException;

public class LoginForm extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private JLabel notLogin;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginForm frame = new LoginForm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(236, 31, 116, 37);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(236, 130, 209, 31);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tài khoản");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(101, 130, 93, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel passwordLabel = new JLabel("Mật khẩu");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordLabel.setBounds(101, 169, 93, 34);
		contentPane.add(passwordLabel);
		
	
		
		password = new JPasswordField();
		password.setBounds(236, 172, 209, 31);
		contentPane.add(password);
		
		JButton loginBtn = new JButton("Đăng nhập");
		loginBtn.setBounds(236, 292, 116, 23);
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Đăng nhập"))
				login();
			}
		});
		contentPane.add(loginBtn);
		
		notLogin = new JLabel("Tài khoản hoặc mật khẩu không chính xác");
		notLogin.setVisible(false);
		notLogin.setForeground(new Color(255, 0, 0));
		notLogin.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		notLogin.setBounds(168, 228, 236, 31);
		contentPane.add(notLogin);

		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public void login() {
		LoginMessageRequest loginRequest = new LoginMessageRequest(username.getText(), password.getText());
		try {
			Client.unVerifyUser = new User(username.getText(), password.getText());
			Client.socketHandler.sendObjectToServer(loginRequest);
//			Client.socketHandler.sendObjectToServer("create");
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JLabel getNotLogin() {
		return this.notLogin;
	}

}
