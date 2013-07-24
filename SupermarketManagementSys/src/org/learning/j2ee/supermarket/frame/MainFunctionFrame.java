package org.learning.j2ee.supermarket.frame;

import static javax.swing.BorderFactory.createTitledBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.learning.j2ee.supermarket.dao.User;
import org.learning.j2ee.supermarket.panel.AddDepotPanel;
import org.learning.j2ee.supermarket.panel.ClockPanel;
import org.learning.j2ee.supermarket.panel.MyJPanel;
import org.learning.j2ee.supermarket.util.Session;
import org.learning.j2ee.supermarket.widget.BGPanel;
import org.learning.j2ee.supermarket.widget.CalendarPanel;
import org.learning.j2ee.supermarket.widget.GlassButton;
import org.learning.j2ee.supermarket.widget.SmallScrollPanel;

public class MainFunctionFrame extends JFrame {
	private MyJPanel contentPane;
	private BGPanel backPanel;
	private SmallScrollPanel scrollButtonGroupPanel = null;
	private JTree tree;
	private JPanel panel;
	// FeelWarePanel panelFeel = new FeelWarePanel();
	JPanel panel_1 = new JPanel();
	JLabel fristLabel = new JLabel("基本档案管理");
	private BGPanel jPanel = null;
	private ButtonGroup buttonGroup = null;
	private GlassButton workSpaceButton = null;
	private GlassButton progressButton = null;
	private GlassButton bookProjectButton = null;
	private GlassButton checkOutDepositeButton = null;
	private GlassButton personnelManagerButton = null;
	private GlassButton deptManagerButton = null;
	JLabel label_1 = new JLabel("您当前的位置是：");

	/**
	 * Create the frame.
	 */
	public MainFunctionFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 934, 625);

		contentPane = new MyJPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.add(getModuleButtonGroup());
		setTitle("超市管理系统");
		contentPane.setLayout(null);
		JPanel clockpanel = new JPanel();
		clockpanel.setBackground(new Color(71, 201, 223));
		clockpanel.setBounds(10, 124, 258, 245);
		contentPane.add(clockpanel);
		clockpanel.setLayout(null);

		JPanel tempPanel = new JPanel();
		tempPanel.setBounds(0, 210, 276, 1);
		clockpanel.add(tempPanel);
		tempPanel.setLayout(null);
		User user = Session.getUser(); // 获取登录用户对象
		String info = "<html><body>" + "<font color=#FFFFFF>你 好：</font>"
				+ "<font color=yellow><b>" + user.getUserName() + "</b></font>"
				+ "<font color=#FFFFFF>                欢 迎 登 录</font>"
				+ "</body></html>"; // 定义窗体显示内容
		clockpanel.add(getPanel());
		JLabel label = new JLabel(info); // 定义显示指定内容的标签对象
		label.setBackground(Color.yellow);
		label.setBounds(45, 210, 128, 35);
		clockpanel.add(label);

		CalendarPanel panel_2 = new CalendarPanel();
		panel_2.setBounds(10, 370, 258, 207);
		contentPane.add(panel_2);
		contentPane.add(getContentPanel()); // 在主窗体中添加

		initialize();

	}

	private void initialize() {
		buttonGroup = new ButtonGroup();
		workSpaceButton = new GlassButton();
		progressButton = new GlassButton();
		bookProjectButton = new GlassButton();
		checkOutDepositeButton = new GlassButton();
		personnelManagerButton = new GlassButton();
		deptManagerButton = new GlassButton();
	}

	private BGPanel getContentPanel() {
		if (backPanel == null) {
			backPanel = new BGPanel();
			backPanel.setBackground(new Color(71, 201, 223));
			backPanel.setSize(629, 416); // 内容显示区主面板
			backPanel.setLocation(279, 149);
			backPanel.setLayout(null);

			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setVerticalAlignment(SwingConstants.BOTTOM);
			label_1.setBounds(38, 38, 96, 15);
			backPanel
					.setBorder(createTitledBorder(null, "基本档案管理",
							TitledBorder.DEFAULT_JUSTIFICATION,
							TitledBorder.TOP, new Font("sansserif", Font.BOLD,
									12), new Color(59, 59, 59)));
			backPanel.add(label_1);
			fristLabel.setBounds(133, 38, 123, 15);
			backPanel.add(fristLabel);
			panel_1.setBounds(10, 63, 611, 343);
			panel_1.setLayout(null);
			backPanel.add(panel_1);
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane);
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBackground(new Color(71, 201, 223));
			scrollPane_1.setBounds(0, 0, 138, 334);
			panel_1.add(scrollPane_1);
			DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
					"基本档案管理");
			DefaultMutableTreeNode childNode1 = new DefaultMutableTreeNode(
					"供货商管理");
			DefaultMutableTreeNode childNode2 = new DefaultMutableTreeNode(
					"销售商管理");
			DefaultMutableTreeNode childNode3 = new DefaultMutableTreeNode(
					"货品档案管理");
			DefaultMutableTreeNode childNode4 = new DefaultMutableTreeNode(
					"仓库管理");
			rootNode.add(childNode1);
			rootNode.add(childNode2);
			rootNode.add(childNode3);
			rootNode.add(childNode4);
			tree = new JTree(rootNode);
			scrollPane_1.setColumnHeaderView(tree);
			final JPanel sellPanel = new JPanel();
			sellPanel.setBackground(new Color(71, 201, 223));
			sellPanel.setBounds(138, 0, 473, 343);
			sellPanel.setLayout(null);
			panel_1.add(sellPanel);

			tree.getSelectionModel().setSelectionMode(
					TreeSelectionModel.SINGLE_TREE_SELECTION);
			TreeSelectionModel treeSelectionModel = tree.getSelectionModel();
			treeSelectionModel
					.setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
			tree.addTreeSelectionListener(new TreeSelectionListener() {
				@Override
				public void valueChanged(TreeSelectionEvent e) {
					if (!tree.isSelectionEmpty()) {
						TreePath selectionPaths = tree.getSelectionPath();
						Object path = selectionPaths.getLastPathComponent();
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) path;
						String userObject = (String) node.getUserObject();
						repaint();
						if (userObject.equals("供货商管理")) {
							fristLabel.setText("供货商管理");
							sellPanel.removeAll();
							// sellPanel.add(panelFeel.getMessage());

						} else if (userObject.equals("销售商管理")) {
							fristLabel.setText("销售商管理");
							sellPanel.removeAll();
							// SellPanel sell = new SellPanel();
							// sellPanel.add(sell.getMessage());
							repaint();
						} else if (userObject.equals("货品档案管理")) {
							fristLabel.setText("货品档案管理");
							sellPanel.removeAll();
							// WarePanel warePanel = new WarePanel();
							// sellPanel.add(warePanel.getMessage());
							repaint();
						} else if (userObject.equals("仓库管理")) {
							fristLabel.setText("仓库管理");
							sellPanel.removeAll();
							AddDepotPanel depotPanel = new AddDepotPanel();
							sellPanel.add(depotPanel);
							repaint();
						}
					}
				}
			});

		}
		return backPanel;
	}

	private SmallScrollPanel getModuleButtonGroup() {
		if (scrollButtonGroupPanel == null) {
			scrollButtonGroupPanel = new SmallScrollPanel();// 创建移动面板
			scrollButtonGroupPanel.setBounds(250, 20, 434, 68);
			scrollButtonGroupPanel.setOpaque(false);
			// 将按钮组面板作为移动面板的视图
			scrollButtonGroupPanel.setViewportView(getFunctionButtonsJPanel());
			scrollButtonGroupPanel.getAlphaScrollPanel().setViewportView(
					getFunctionButtonsJPanel());
			// 添加鼠标事件监听器
		}
		return scrollButtonGroupPanel;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new ClockPanel();
			panel.setBounds(56, 35, 153, 148);
		}
		return panel;
	}

	public BGPanel getFunctionButtonsJPanel() {
		if (jPanel == null) {
			GridLayout gridLayout = new GridLayout(); // 定义网格布局管理器
			gridLayout.setRows(1); // 设置网格布局管理器的行数
			gridLayout.setHgap(0); // 设置组件间水平间距
			gridLayout.setVgap(0); // 设置组件间垂直间距
			jPanel = new BGPanel(); //
			// 设置布局管理器
			jPanel.setLayout(gridLayout);
			// 设置初始大小
			jPanel.setPreferredSize(new Dimension(400, 50));
			jPanel.setOpaque(false);

			// 添加按钮
			jPanel.add(getWorkSpaceButton(), null);
			jPanel.add(getProgressButton(), null);
			jPanel.add(getrukuButton(), null);
			jPanel.add(getchukuButton(), null);
			jPanel.add(getPersonnelManagerButton(), null);
			jPanel.add(getDeptManagerButton(), null);
			if (buttonGroup == null) {
				buttonGroup = new ButtonGroup();
			}
			// 把所有按钮添加到一个组控件中
			buttonGroup.add(getProgressButton());
			buttonGroup.add(getWorkSpaceButton());
			buttonGroup.add(getrukuButton());
			buttonGroup.add(getchukuButton());
			buttonGroup.add(getPersonnelManagerButton());
			buttonGroup.add(getDeptManagerButton());
		}
		return jPanel;
	}

	// 基本档案管理按钮
	private GlassButton getWorkSpaceButton() {
		if (workSpaceButton == null) {
			workSpaceButton = new GlassButton();
		}

		workSpaceButton.setActionCommand("基本档案管理"); // 设置按钮的动作命令
		workSpaceButton
				.setIcon(new ImageIcon(
						getClass()
								.getResource(
										"/org/learning/j2ee/supermarket/util/buttonIcons/myWorkSpace.png"))); // 定义按钮的初始化背景
		ImageIcon icon = new ImageIcon(
				getClass()
						.getResource(
								"/org/learning/j2ee/supermarket/util/buttonIcons/myWorkSpace2.png")); // 创建图片对象
		workSpaceButton.setRolloverIcon(icon); // 设置按钮的翻转图片
		workSpaceButton.setSelectedIcon(icon); // 设置按钮被选中时显示图片
		workSpaceButton.setSelected(true);
		workSpaceButton.addActionListener(new toolsButtonActionAdapter()); // 按钮的监听器
		return workSpaceButton;
	}

	// 采购进货管理按钮
	private GlassButton getProgressButton() {
		if (progressButton == null) {
			progressButton = new GlassButton();
			progressButton.setActionCommand("采购进货");
			progressButton.setText("");
			progressButton
					.setIcon(new ImageIcon(
							getClass()
									.getResource(
											"/org/learning/j2ee/supermarket/util/buttonIcons/caigou1.png")));
			ImageIcon icon = new ImageIcon(
					getClass()
							.getResource(
									"/org/learning/j2ee/supermarket/util/buttonIcons/caigou2.png"));
			progressButton.setRolloverIcon(icon);
			progressButton.setSelectedIcon(icon);
			progressButton.addActionListener(new toolsButtonActionAdapter());
		}
		return progressButton;
	}

	// 仓库管理
	private GlassButton getrukuButton() {
		if (bookProjectButton == null) {
			bookProjectButton = new GlassButton();
			bookProjectButton.setActionCommand("仓库入库");
			// bookProjectButton.setText("图书计划");
			ImageIcon icon = new ImageIcon(
					getClass()
							.getResource(
									"/org/learning/j2ee/supermarket/util/buttonIcons/ruku2.png"));
			bookProjectButton.setSelectedIcon(icon);
			bookProjectButton.setRolloverIcon(icon);
			bookProjectButton
					.setIcon(new ImageIcon(
							getClass()
									.getResource(
											"/org/learning/j2ee/supermarket/util/buttonIcons/ruku1.png")));
			bookProjectButton.addActionListener(new toolsButtonActionAdapter());
		}
		return bookProjectButton;
	}

	// 仓库出库管理
	private GlassButton getchukuButton() {
		if (checkOutDepositeButton == null) {
			checkOutDepositeButton = new GlassButton();
			checkOutDepositeButton.setActionCommand("仓库出库");
			ImageIcon icon = new ImageIcon(
					getClass()
							.getResource(
									"/org/learning/j2ee/supermarket/util/buttonIcons/chuku1.png"));
			checkOutDepositeButton.setSelectedIcon(icon);
			checkOutDepositeButton.setRolloverIcon(icon);
			checkOutDepositeButton
					.setIcon(new ImageIcon(
							getClass()
									.getResource(
											"/org/learning/j2ee/supermarket/util/buttonIcons/chuku2.png")));
			checkOutDepositeButton.addActionListener(new toolsButtonActionAdapter());
		}
		return checkOutDepositeButton;
	}

	// 人员管理系统
	private GlassButton getPersonnelManagerButton() {
		if (personnelManagerButton == null) {
			personnelManagerButton = new GlassButton();
			// personnelManagerButton.setText("人员管理");
			personnelManagerButton.setActionCommand("查询及统计系统");
			// personnelManagerButton.setCursor(new
			// Cursor(Cursor.DEFAULT_CURSOR));
			ImageIcon imageIcon = new ImageIcon(
					getClass()
							.getResource(
									"/org/learning/j2ee/supermarket/util/buttonIcons/person2.png"));
			personnelManagerButton.setIcon(imageIcon);
			ImageIcon icon = new ImageIcon(
					getClass()
							.getResource(
									"/org/learning/j2ee/supermarket/util/buttonIcons/person1.png"));
			personnelManagerButton.setRolloverIcon(icon);
			personnelManagerButton.setSelectedIcon(icon);
			personnelManagerButton.setFocusPainted(false);
			personnelManagerButton
					.addActionListener(new toolsButtonActionAdapter());
		}
		return personnelManagerButton;
	}

	// 部门管理系统
	private GlassButton getDeptManagerButton() {
		if (deptManagerButton == null) {
			deptManagerButton = new GlassButton();

			deptManagerButton.setActionCommand("查询及统计系统");
			// personnelManagerButton.setCursor(new
			// Cursor(Cursor.DEFAULT_CURSOR));
			ImageIcon imageIcon = new ImageIcon(
					getClass()
							.getResource(
									"/org/learning/j2ee/supermarket/util/buttonIcons/deptButton.png"));
			deptManagerButton.setIcon(imageIcon);
			ImageIcon icon = new ImageIcon(
					getClass()
							.getResource(
									"/org/learning/j2ee/supermarket/util/buttonIcons/deptButton2.png"));
			deptManagerButton.setRolloverIcon(icon);
			deptManagerButton.setSelectedIcon(icon);
			deptManagerButton.setFocusPainted(false);
			deptManagerButton.addActionListener(new toolsButtonActionAdapter());
		}
		return deptManagerButton;
	}

	class toolsButtonActionAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getSource());
			
			if (e.getSource() == workSpaceButton) {
				// backPanel.removeAll();
				// backPanel.add(label_1);
				// fristLabel.setBounds(133, 38, 123, 15);
				// backPanel.add(fristLabel);
				// panel_1.setBounds(10, 63, 611, 376);
				// backPanel.add(panel_1);
				// fristLabel.setText("基本档案管理");
				repaint();
			}
			if (e.getSource() == progressButton) {
				// backPanel.removeAll();
				// backPanel.add(label_1);
				// fristLabel.setBounds(133, 38, 123, 15);
				// backPanel.add(fristLabel);
				// panel_1.setBounds(10, 63, 611, 386);
				// StockPanel stockPanl = new StockPanel();
				// fristLabel.setText("采购订货");
				// backPanel.add(stockPanl);
				repaint();
			}
			if (e.getActionCommand() == "仓库入库") {
				 backPanel.removeAll();
				 backPanel.add(label_1);
				 fristLabel.setBounds(133, 38, 123, 15);
				 backPanel.add(fristLabel);
				 panel_1.setBounds(10, 63, 611, 386);
				 AddDepotPanel joinPanel = new AddDepotPanel();
				 backPanel.add(joinPanel);
				 fristLabel.setText("仓库入库");
				repaint();
			}
			if (e.getSource() == checkOutDepositeButton) {
//				backPanel.removeAll();
//				backPanel.add(label_1);
//				fristLabel.setBounds(133, 38, 123, 15);
//				backPanel.add(fristLabel);
//				panel_1.setBounds(10, 63, 611, 386);
//				 OutDepotPanel outPanel = new OutDepotPanel();
//				 backPanel.add(outPanel);
//				fristLabel.setText("仓库出库");
				repaint();
			}
			if (e.getSource() == deptManagerButton) {
				// backPanel.removeAll();
				// backPanel.add(label_1);
				// fristLabel.setBounds(133, 38, 123, 15);
				// backPanel.add(fristLabel);
				// panel_1.setBounds(10, 63, 611, 386);
				// DeptPanel outPanel = new DeptPanel();
				// backPanel.add(outPanel);
				// fristLabel.setText("部门管理");
				repaint();
			}
			if (e.getSource() == personnelManagerButton) {
				// backPanel.removeAll();
				// panel_1.setBounds(10, 63, 611, 386);
				// PersonnelPanel panel = new PersonnelPanel();
				// backPanel.add(panel);
				repaint();
			}
		}

	}

}
