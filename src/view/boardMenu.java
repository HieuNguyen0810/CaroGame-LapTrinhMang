package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class boardMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					boardMenu frame = new boardMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public boardMenu() {
		setSize(300, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bạn");
		lblNewLabel.setBounds(51, 26, 55, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Đối thủ");
		lblNewLabel_1.setBounds(200, 29, 49, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel myLabelGUI = new JLabel("X");
		myLabelGUI.setBounds(51, 54, 49, 14);
		contentPane.add(myLabelGUI);
		
		JLabel clientLabelGUI = new JLabel("O");
		clientLabelGUI.setBounds(200, 54, 49, 14);
		contentPane.add(clientLabelGUI);
		
		JLabel myWinGames = new JLabel("0");
		myWinGames.setBounds(51, 79, 49, 14);
		contentPane.add(myWinGames);
		
		JLabel clientWinGames = new JLabel("0");
		clientWinGames.setBounds(200, 79, 49, 14);
		contentPane.add(clientWinGames);
	}
}
