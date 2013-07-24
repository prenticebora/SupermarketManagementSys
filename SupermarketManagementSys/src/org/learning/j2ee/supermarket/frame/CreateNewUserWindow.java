package org.learning.j2ee.supermarket.frame;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.learning.j2ee.supermarket.dao.SupermarketMySql;
import org.learning.j2ee.supermarket.dao.User;
import org.learning.j2ee.supermarket.dao.UserDao;

public class CreateNewUserWindow extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNewUserWindow frame = new CreateNewUserWindow();
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
	public CreateNewUserWindow() {
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						CreateNewUserWindow.class
								.getResource("/org/learning/j2ee/supermarket/main/new_user.png")));
		setTitle("\u521B\u5EFA\u65B0\u7528\u6237");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton createNewUserButton = new JButton(
				"\u521B\u5EFA\u65B0\u7528\u6237");
		
		createNewUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String username = userNameTextField.getText();
				String password = passwordField.getText();

				if (isValidCredential(username, password)) {
					User newUser = new User();
					newUser.setUserName(username);
					newUser.setPassword(password);

					UserDao userHandler = new UserDao();

					try {
						userHandler.create(SupermarketMySql.getConnection(),
								newUser);
					} catch (SQLException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(getContentPane(),
								"Error in connecting to MySQL DB... Failed to create new user: "
										+ newUser.toString());
					}

					JOptionPane.showMessageDialog(getContentPane(),
							"Successfully created user " + username
									+ ".\nPlease try to login again.");
					dispose();
				}
			}
		});
		createNewUserButton.setBackground(SystemColor.textHighlight);
		createNewUserButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
		createNewUserButton.setBounds(312, 107, 132, 42);
		contentPane.add(createNewUserButton);

		passwordField = new JPasswordField();
		passwordField.setBounds(118, 151, 155, 27);
		contentPane.add(passwordField);

		userNameTextField = new JTextField();
		userNameTextField.setBounds(118, 107, 155, 27);
		contentPane.add(userNameTextField);
		userNameTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(60, 155, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblDfhaod = new JLabel("\u7528\u6237\u540D");
		lblDfhaod.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblDfhaod.setBounds(60, 104, 90, 33);
		contentPane.add(lblDfhaod);

		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(CreateNewUserWindow.class
				.getResource("/org/learning/j2ee/supermarket/main/login.png")));
		backgroundImage.setBounds(0, 0, 560, 262);
		contentPane.add(backgroundImage);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { userNameTextField, passwordField,
						createNewUserButton, lblNewLabel_1, lblDfhaod,
						backgroundImage }));
	}

	protected boolean isValidCredential(String username, String password) {
		boolean isValid = true;
		if (username.length() == 0 || password.length() == 0) {
			isValid = false;
		}

		return isValid;

	}
}
