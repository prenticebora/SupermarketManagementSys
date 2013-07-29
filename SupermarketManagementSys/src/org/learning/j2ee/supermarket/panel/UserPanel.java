package org.learning.j2ee.supermarket.panel;

import static javax.swing.BorderFactory.createTitledBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.learning.j2ee.supermarket.dao.JoinDepot;
import org.learning.j2ee.supermarket.dao.JoinDepotDao;
import org.learning.j2ee.supermarket.dao.SupermarketMySql;
import org.learning.j2ee.supermarket.dao.User;
import org.learning.j2ee.supermarket.dao.UserDao;

public class UserPanel extends JPanel {
	private JTextField dateTextField;
	private final JoinDepotDao model = new JoinDepotDao();
	private JTable table_1;
	private UserDao userHandler = new UserDao();
	private JTable stockTable;

	/**
	 * Create the panel.
	 */
	public UserPanel() {
		this.setBorder(createTitledBorder(null, "用户管理",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		setSize(631, 413);
		setLayout(null);
		this.setBackground(new Color(71, 201, 223));

		dateTextField = new JTextField();
		dateTextField.setBounds(135, 81, 156, 25);
		add(dateTextField);
		dateTextField.setColumns(10);

		JButton findButton = new JButton("搜索");
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userNameToSearch = dateTextField.getText();
				if (userNameToSearch.length() == 0) {
					JOptionPane.showMessageDialog(getParent(), "没有填写查询条件！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				User searchUser = new User();
				searchUser.setUserName(userNameToSearch);

				List searchResult = searchMatchingRecords(searchUser);

				displayUserSearchResult(searchResult);

			}

			private List searchMatchingRecords(User searchTargetUser) {
				List matchingRecords = null;
				try {
					matchingRecords = userHandler.searchMatching(
							SupermarketMySql.getConnection(), searchTargetUser);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				return matchingRecords;
			}
		});

		addFindButton(findButton);

		addInsertButton();

		addUpdateButton();

		addDeleteButton();

		addSearchCriteriaInputArea();

		initializeStockDisplayTable();
	}

	private void addFindButton(JButton findButton) {
		findButton.setBounds(301, 82, 93, 23);
		add(findButton);
	}

	private void addSearchCriteriaInputArea() {
		JLabel dateLabel = new JLabel("\u7528\u6237\u540D");
		dateLabel.setBounds(68, 88, 66, 15);
		add(dateLabel);

		List lists = null;
		try {
			lists = userHandler.loadAll(SupermarketMySql.getConnection());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void addDeleteButton() {
		JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要刪除的数据！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					// String column = model.getValueAt(row, 0).toString();
					// dao.deleteJoinDepot(Integer.parseInt(column));
					JOptionPane.showMessageDialog(getParent(), "数据删除成功！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					repaint();

				}
			}
		});
		deleteButton.setBounds(380, 369, 66, 23);
		add(deleteButton);
	}

	private void addInsertButton() {
		JButton insertButton = new JButton("添加");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewUserFrame addNewUser = new AddNewUserFrame();
				addNewUser.setVisible(true);
			}
		});
		insertButton.setBounds(167, 369, 66, 23);
		add(insertButton);
	}

	private void addUpdateButton() {
		JButton updateButton = new JButton("修改");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要修改的数据！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					File file = new File("file.txt");
					// try {
					// String column = model.getValueAt(row, 0).toString();
					// file.createNewFile();
					// FileOutputStream out = new FileOutputStream(file);
					// out.write((Integer.parseInt(column)));
					// out.close();
					// UpdateJoinDepotFrame update = new UpdateJoinDepotFrame();
					// update.setVisible(true);
					// repaint();
					// } catch (Exception ee) {
					// ee.printStackTrace();
					// }
				}
			}
		});
		updateButton.setBounds(282, 369, 66, 23);
		add(updateButton);
	}

	private void initializeStockDisplayTable() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 150, 534, 199);
		add(scrollPane);

		String[] columnNames = { "序号", "用户名" };
		String[][] data = {};

		stockTable = new JTable(data, columnNames);
		stockTable.setModel(new DefaultTableModel(data, columnNames));
		scrollPane.setViewportView(stockTable);
	}

	protected void displayUserSearchResult(List matchingRecords) {
		for (Object record : matchingRecords) {
			addNewStockInTable(((User) record).getId(),
					((User) record).getUserName());
		}

	}

	private void addNewStockInTable(int id, String userName) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) (stockTable
				.getModel());

		Vector<String> newRow = new Vector<>();

		newRow.add(Integer.toString(id));
		newRow.add(userName);

		defaultTableModel.addRow(newRow);

	}
}
