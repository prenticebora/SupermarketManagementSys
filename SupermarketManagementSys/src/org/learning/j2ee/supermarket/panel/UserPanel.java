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

import org.learning.j2ee.supermarket.dao.Basicmessage;
import org.learning.j2ee.supermarket.dao.BasicmessageDao;
import org.learning.j2ee.supermarket.dao.JoinDepotDao;
import org.learning.j2ee.supermarket.dao.NotFoundException;
import org.learning.j2ee.supermarket.dao.PositionDao;
import org.learning.j2ee.supermarket.dao.SupermarketMySql;
import org.learning.j2ee.supermarket.dao.User;
import org.learning.j2ee.supermarket.dao.UserDao;

public class UserPanel extends JPanel {
	private JTextField dateTextField;
	private final JoinDepotDao model = new JoinDepotDao();

	private BasicmessageDao basicMessageHandler = new BasicmessageDao();
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
		initFindButton(findButton);
		addFindButton(findButton);

		addInsertButton();

		addUpdateButton();

		addDeleteButton();

		addSearchCriteriaInputArea();

		initializeStockDisplayTable();
	}

	private void initFindButton(JButton findButton) {
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userNameToSearch = dateTextField.getText();
				if (userNameToSearch.length() == 0) {
					JOptionPane.showMessageDialog(getParent(), "没有填写查询条件！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				List searchResult = searchMatchingUserRecords(userNameToSearch);

				displayUserSearchResult(searchResult);

			}

			private List searchMatchingUserRecords(String searchUserName) {
				Basicmessage searchUser = new Basicmessage();
				searchUser.setName(searchUserName);

				List matchingRecords = null;

				try {
					matchingRecords = basicMessageHandler.searchMatching(
							SupermarketMySql.getConnection(), searchUser);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				return matchingRecords;
			}
		});
	}

	private void addFindButton(JButton findButton) {
		findButton.setBounds(301, 82, 93, 23);

		add(findButton);
	}

	private void addSearchCriteriaInputArea() {
		JLabel dateLabel = new JLabel("\u7528\u6237\u540D");
		dateLabel.setBounds(68, 88, 66, 15);
		add(dateLabel);

	}

	private void addDeleteButton() {
		JButton deleteButton = new JButton("删除");

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = stockTable.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要刪除的数据！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					DefaultTableModel defaultTableModel = (DefaultTableModel) (stockTable
							.getModel());

					String userId = (String) defaultTableModel.getValueAt(row,
							0);

					BasicmessageDao userHandler = new BasicmessageDao();
					Basicmessage userToDelete = new Basicmessage();
					userToDelete.setId(Integer.parseInt(userId));

					try {
						userHandler.delete(SupermarketMySql.getConnection(),
								userToDelete);
					} catch (NotFoundException | SQLException e1) {
						e1.printStackTrace();
						
						JOptionPane.showMessageDialog(getParent(), "删除用户 " + userToDelete + " 失败！");
						
						repaint();
						
						return;
					}

					JOptionPane.showMessageDialog(getParent(), "数据删除成功！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					
					defaultTableModel.removeRow(row);
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
				int row = stockTable.getSelectedRow();
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

		String[] columnNames = { "用户ID", "姓名", "年龄", "性别", "部门", "职务" };
		String[][] data = {};

		stockTable = new JTable(data, columnNames);
		stockTable.setModel(new DefaultTableModel(data, columnNames));
		scrollPane.setViewportView(stockTable);
	}

	protected void displayUserSearchResult(List matchingRecords) {
		if (matchingRecords.size() == 0) {
			JOptionPane.showMessageDialog(getParent(), "没有符合条件的查询结果！");

			return;
		}

		stockTable.removeAll();

		for (Object record : matchingRecords) {
			addNewStockInTable(((Basicmessage) record).getId(),
					((Basicmessage) record).getName(),
					((Basicmessage) record).getAge(),
					((Basicmessage) record).getDept(),
					((Basicmessage) record).getPositionId());
		}

	}

	private void addNewStockInTable(int id, String userName, int age,
			int department, int positionId) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) (stockTable
				.getModel());

		Vector<String> newRow = new Vector<>();

		newRow.add(Integer.toString(id));
		newRow.add(userName);
		newRow.add(Integer.toString(age));
		newRow.add("男");
		newRow.add("TODO");
		try {
			newRow.add(new PositionDao().getPositionNameById(positionId));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(getParent(), "连接MySQL数据库错误！");

			return;
		}

		defaultTableModel.addRow(newRow);

	}
}
