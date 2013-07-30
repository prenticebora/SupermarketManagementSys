/**
 * 
 */
package org.learning.j2ee.supermarket.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.learning.j2ee.supermarket.dao.Basicmessage;
import org.learning.j2ee.supermarket.dao.BasicmessageDao;
import org.learning.j2ee.supermarket.dao.Position;
import org.learning.j2ee.supermarket.dao.PositionDao;
import org.learning.j2ee.supermarket.dao.SupermarketMySql;

/**
 * @author brliu
 * 
 */
public class AddNewUserFrame extends JFrame {
	private JTextField nameTextField;
	private JTextField ageTextField;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JLabel positionLabel;
	private JLabel nameLabel;
	private JLabel genderLabel;
	private JRadioButton maleRadioButton;
	private JRadioButton femaleRadioButton;
	private JLabel ageLabel;
	private JLabel departmentLabel;

	public AddNewUserFrame() {
		setBounds(100, 100, 459, 285);

		setTitle("\u6DFB\u52A0\u5458\u5DE5\u4FE1\u606F\u7A97\u4F53");
		getContentPane().setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 262);
		getContentPane().add(tabbedPane);

		panel = new JPanel();
		tabbedPane.addTab("基本信息", null, panel, null);
		panel.setLayout(null);

		positionLabel = new JLabel("\u804C\u52A1");
		positionLabel.setBounds(34, 139, 54, 19);
		panel.add(positionLabel);

		nameLabel = new JLabel("\u59D3 \u540D");
		nameLabel.setBounds(34, 50, 54, 19);
		panel.add(nameLabel);

		genderLabel = new JLabel("\u6027 \u522B");
		genderLabel.setBounds(34, 92, 54, 19);
		panel.add(genderLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(91, 49, 86, 29);
		panel.add(nameTextField);
		nameTextField.setColumns(10);

		maleRadioButton = new JRadioButton("\u7537");
		maleRadioButton.setBounds(94, 90, 54, 23);
		panel.add(maleRadioButton);

		femaleRadioButton = new JRadioButton("\u5973");
		femaleRadioButton.setBounds(154, 90, 80, 23);
		panel.add(femaleRadioButton);

		ageLabel = new JLabel("\u5E74 \u9F84");
		ageLabel.setBounds(256, 52, 54, 19);
		panel.add(ageLabel);

		departmentLabel = new JLabel("\u90E8 \u95E8");
		departmentLabel.setBounds(256, 92, 54, 19);
		panel.add(departmentLabel);

		String[] position = { "经理", "主管", "职员" };
		final JComboBox positionCombox = new JComboBox(position);
		positionCombox.setBounds(91, 138, 86, 20);
		panel.add(positionCombox);

		String[] department = { "收银", "办公室" };
		final JComboBox departCombox = new JComboBox(department);
		departCombox.setBounds(303, 91, 86, 20);
		panel.add(departCombox);

		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String name = nameTextField.getText();
				String age = ageTextField.getText();
				String gender = maleRadioButton.isSelected() ? "Male"
						: "Female";
				String position = (String) positionCombox.getSelectedItem();
				String department = (String) departCombox.getSelectedItem();

				addOneNewUser(name, age, gender, position, department);
			}

			private void addOneNewUser(String name, String age, String gender,
					String position, String department) {
				BasicmessageDao basicUserMessageDao = new BasicmessageDao();

				Basicmessage basicmessage = new Basicmessage();

				basicmessage.setName(name);
				basicmessage.setAge(Integer.parseInt(age));
				basicmessage.setDept(1);
				PositionDao positionDbHandler = new PositionDao();
				Position positionToSearch = new Position();
				positionToSearch.setPositionName((String) positionCombox
						.getSelectedItem());

				List searchResult;
				try {
					searchResult = positionDbHandler.searchMatching(
							SupermarketMySql.getConnection(), positionToSearch);
				} catch (SQLException e1) {
					e1.printStackTrace();

					JOptionPane.showMessageDialog(getParent(), "连接MySQL数据库出错！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);

					return;
				}
				
				if (searchResult.size() == 0) {
					JOptionPane.showMessageDialog(getParent(), "职位 "
							+ positionToSearch.getPositionName()
							+ " 在数据库中没有记录！添加用户失败！");

					return;
				}

				basicmessage.setPositionId(((Position) searchResult.get(0))
						.getPositionId());

				try {
					basicUserMessageDao.create(
							SupermarketMySql.getConnection(), basicmessage);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(getParent(), "连接MySQL数据库出错！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				JOptionPane.showMessageDialog(getParent(), "已添加新用户： "
						+ basicmessage, "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		button.setBounds(125, 186, 89, 23);
		panel.add(button);

		JButton button_1 = new JButton("\u5173\u95ED");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setBounds(267, 186, 89, 23);
		panel.add(button_1);

		ageTextField = new JTextField();
		ageTextField.setBounds(303, 49, 86, 29);
		panel.add(ageTextField);
		ageTextField.setColumns(10);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
