package org.learning.j2ee.supermarket.main;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.learning.j2ee.supermarket.dao.SupermarketMySql;
import org.learning.j2ee.supermarket.dao.User;
import org.learning.j2ee.supermarket.dao.UserDao;
import org.learning.j2ee.supermarket.frame.CreateNewUserWindow;
import org.learning.j2ee.supermarket.frame.MainFunctionFrame;
import org.learning.j2ee.supermarket.util.Session;

public class SupermarketManagementSystem extends JFrame {
	private BackgroundPanel loginPanel;
	private JTextField userNameTextField;
	private JPasswordField passwordField;
	private Point spoint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							SupermarketManagementSystem mostly = new SupermarketManagementSystem();
							mostly.setVisible(true);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SupermarketManagementSystem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);

		setLocationRelativeTo(null);// 窗体居中

		setTitle("登录窗体");

		setBounds(100, 100, 559, 285);

		loginPanel = getLoginPanel();
		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(loginPanel);
		loginPanel.setLayout(null);
	}

	/**
	 * 初始化登录面板
	 * 
	 * @return
	 */
	private BackgroundPanel getLoginPanel() {
		if (loginPanel == null) {
			loginPanel = new BackgroundPanel();// 创建登录面板对象
			loginPanel
					.setImage(Toolkit
							.getDefaultToolkit()
							.getImage(
									SupermarketManagementSystem.class
											.getResource("/org/learning/j2ee/supermarket/main/login.png")));

			loginPanel.config();

			addUserNameLabelAndField();

			addPasswordLabelAndField();

			addLoginButton();

			addCreateUserButton();

			// URL urlclose = getClass().getResource("close.png");
			// ImageIcon imageIconclose = new ImageIcon(urlclose);

			// 添加鼠标事件监听器
			loginPanel.addMouseListener(new TitleMouseAdapter());
			// 添加鼠标动作监听器

		}
		return loginPanel;
	}

	private void addCreateUserButton() {
		JButton createNewUserButton = new JButton("添加新用户");

		createNewUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreateNewUserWindow createNewUserWindow = new CreateNewUserWindow();
				createNewUserWindow.setVisible(true);

			}
		});

		createNewUserButton.setBounds(253, 191, 93, 40);
		loginPanel.add(createNewUserButton);

	}

	private void addLoginButton() {
		JButton loginButton = new JButton("");
		URL url = getClass().getResource("enter.png");
		ImageIcon imageIcon = new ImageIcon(url);

		loginButton.setBounds(198, 74, 93, 25);
		loginButton.setIcon(imageIcon);
		loginButton.setContentAreaFilled(false); // 取消填充区域
		loginButton.setBorder(null); // 取消边框

		loginButton.addActionListener(new ActionListener() { // 按钮的单击事件
					public void actionPerformed(ActionEvent e) {
						User userToLogin = new User();
						userToLogin.setUserName(userNameTextField.getText());
						userToLogin.setPassword(passwordField.getText()); // 以用户添加的用户名与密码为参数调用查询用户方法

						UserDao userDao = new UserDao();
						List<User> searchResult = null;
						try {
							searchResult = userDao.searchMatching(
									SupermarketMySql.getConnection(),
									userToLogin);
						} catch (SQLException e1) {

							JOptionPane.showMessageDialog(getContentPane(),
									"Error in querying MySQL DB with message "
											+ e1.toString());

						}

						if (searchResult.get(0).getId() > 0) { // 判断用户编号是否大于0
							Session.setUser(userToLogin); // 设置Session对象的User属性值
							MainFunctionFrame frame = new MainFunctionFrame(); // 创建主窗体对象
							frame.setVisible(true); // 显示主窗体

							SupermarketManagementSystem.this.dispose(); // 销毁登录窗体
						} else { // 如果用户输入的用户名与密码错误
							JOptionPane.showMessageDialog(getContentPane(),
									"用户名或密码错误"); // 给出提示信息
							userNameTextField.setText(""); // 用户名文本框设置为空
							passwordField.setText(""); // 密码文本框设置为空
						}
					}
				});
		loginButton.setBounds(253, 116, 93, 64);
		loginPanel.add(loginButton);
	}

	private void addPasswordLabelAndField() {
		JLabel passWordLabel = new JLabel("密  码：");
		passWordLabel.setBounds(40, 158, 54, 15);
		loginPanel.add(passWordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(92, 155, 139, 25);
		loginPanel.add(passwordField);
	}

	private void addUserNameLabelAndField() {
		JLabel userNameLabel = new JLabel("用户名：");
		userNameLabel.setBounds(40, 116, 54, 15);
		loginPanel.add(userNameLabel);

		userNameTextField = new JTextField();
		userNameTextField.setBounds(92, 113, 139, 25);
		userNameTextField.setColumns(10);
		loginPanel.add(userNameTextField);
	}

	private final class TitleMouseAdapter extends MouseAdapter implements
			Serializable {
		public void mousePressed(java.awt.event.MouseEvent e) {
			spoint = e.getPoint();
		}

	}

}
