package view;

import client.Client;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class ChooseOption extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JRadioButton roomMaster;
	private JRadioButton client;
	private JButton submit;
	private JLabel idLabel;
	private JLabel warning;
	private JLabel wrongId;
	private String message = null;
	private String id = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChooseOption frame = new ChooseOption();
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
	public ChooseOption() {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lựa chọn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(153, 10, 113, 29);
		contentPane.add(lblNewLabel);
		
		roomMaster = new JRadioButton("Tạo phòng");
		roomMaster.setBounds(33, 58, 111, 23);
		roomMaster.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				message = "create";
			}
		});
		contentPane.add(roomMaster);
		
		client = new JRadioButton("Vào phòng");
		client.setBounds(224, 58, 111, 23);
		client.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				message = "join";
			}
		});
		contentPane.add(client);

		ButtonGroup option = new ButtonGroup();
		option.add(roomMaster);
		option.add(client);

		idField = new JTextField();
		idField.setBounds(311, 113, 96, 20);
		idField.setColumns(10);
		idField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id = idField.getText();
				System.out.println(id);
			}
		});
		contentPane.add(idField);


		idLabel = new JLabel("ID Phòng");
		idLabel.setBounds(235, 116, 49, 14);
		contentPane.add(idLabel);

		submit = new JButton("Chọn");
		submit.setBounds(177, 214, 89, 23);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Chọn")) {
					if ("create".equals(message)) {
						try {
							Client.socketHandler.sendObjectToServer(message);
						} catch (IOException ex) {
							throw new RuntimeException(ex);
						}
					} else if ("join".equals(message)){
						if (id != null) {
							warning.setVisible(false);
							try {
								Client.socketHandler.sendObjectToServer(message);
								Client.socketHandler.sendObjectToServer(id);
							} catch (IOException ex) {
								throw new RuntimeException(ex);
							}
						} else {
							System.out.println("id is null");
							warning.setVisible(true);
						}
					}
				}
			}
		});
		contentPane.add(submit);

		warning = new JLabel();
		warning.setText("Không được để trống ID");
		warning.setForeground(Color.RED);
		warning.setBounds(224, 100, 200, 111);
		warning.setVisible(false);
		contentPane.add(warning);

		wrongId = new JLabel();
		wrongId.setForeground(Color.RED);
		wrongId.setText("ID phòng không đúng");
		warning.setBounds(224, 100, 200, 111);
		contentPane.add(wrongId);
	}


	public void setWrongIdWarning() {
		this.wrongId.setVisible(true);
	}
}
