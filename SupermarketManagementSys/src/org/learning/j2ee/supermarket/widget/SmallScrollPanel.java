package org.learning.j2ee.supermarket.widget;

import static javax.swing.BorderFactory.createEmptyBorder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 * 移动面板
 * 
 * @author
 */
public class SmallScrollPanel extends BGPanel {
	private static final long serialVersionUID = 3592329256836525981L;
	private AlphaScrollPane alphaScrollPane;// 滚动面板
	private JButton leftScrollButton = null;// 左侧微调按钮
	private JButton rightScrollButton = null;// 右侧微调按钮
	private ScrollMouseAdapter scrollMouseAdapter; // 滚动事件处理器
	private ImageIcon icon1;
	private ImageIcon icon2;

	/**
	 * 构造方法
	 */
	public SmallScrollPanel() {
		scrollMouseAdapter = new ScrollMouseAdapter();// 初始化处理器
		// 初始化程序用图
		icon1 = new ImageIcon(getClass().getResource("/org/learning/j2ee/supermarket/util/buttonIcons/top01.png"));
		icon2 = new ImageIcon(getClass().getResource("/org/learning/j2ee/supermarket/util/buttonIcons/top01.png"));
		setIcon(icon1);// 设置用图
		setIconFill(BOTH_FILL);// 将图标拉伸适应界面大小
		initialize();// 调用初始化方法
	}

	/**
	 * 初始化程序界面的方法
	 */
	private void initialize() {
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(0);
		this.setLayout(borderLayout);// 设置布局管理器
		this.setSize(new Dimension(300, 84));
		this.setOpaque(true);// 使控件透明
		// 添加滚动面板到界面居中位置
		this.add(getAlphaScrollPanel(), BorderLayout.CENTER);
		// 添加左侧微调按钮
		this.add(getLeftScrollButton(), BorderLayout.WEST);
		// 添加右侧微调按钮
		this.add(getRightScrollButton(), BorderLayout.EAST);
	}

	/**
	 * 创建滚动面板
	 * 
	 * @return
	 */
	public AlphaScrollPane getAlphaScrollPanel() {
		if (alphaScrollPane == null) {
			alphaScrollPane = new AlphaScrollPane();
			// 设置初始大小
			alphaScrollPane.setPreferredSize(new Dimension(564, 69));
			// 不显示垂直滚动条
			alphaScrollPane
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			// 不显示水平滚动条
			alphaScrollPane
					.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			// 取消滚动面板边框
			alphaScrollPane.setBorderPaint(false);
			// 添加事件监听器
			alphaScrollPane
					.addComponentListener(new ScrollButtonShowListener());
		}
		return alphaScrollPane;
	}

	public void setViewportView(Component view) {
		alphaScrollPane.setViewportView(view);
	}

	/**
	 * 滚动微调处理器
	 * 
	 * @author
	 */
	private class ScrollButtonShowListener extends ComponentAdapter implements
			Serializable {
		private static final long serialVersionUID = 814596372430146361L;

		@Override
		public void componentResized(ComponentEvent e) {
			// 获取横向滚动条
			JScrollBar scrollBar = alphaScrollPane.getHorizontalScrollBar();
			// 获取范围限制参数
			int scrollWidth = scrollBar.getMaximum();
			int paneWidth = alphaScrollPane.getWidth();
			// 在容器大于包含内容的时候隐藏左右微调按钮
			if (paneWidth >= scrollWidth) {
				getLeftScrollButton().setVisible(false);
				getRightScrollButton().setVisible(false);
			}
			// 在容器小于包含内容的时候显示左右微调按钮
			if (paneWidth < scrollWidth) {
				getLeftScrollButton().setVisible(true);
				getRightScrollButton().setVisible(true);
			}
		}
	}

	/**
	 * 创建左侧微调按钮
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getLeftScrollButton() {
		if (leftScrollButton == null) {
			leftScrollButton = new JButton();
			leftScrollButton.setOpaque(true);// 按钮透明
			// 设置边框
			leftScrollButton.setBorder(createEmptyBorder(0, 10, 0, 0));
			// 设置按钮图标
			leftScrollButton
					.setIcon(new ImageIcon(
							SmallScrollPanel.class
									.getResource("/org/learning/j2ee/supermarket/util/buttonIcons/zuoyidongoff.png")));
			leftScrollButton
					.setPressedIcon(new ImageIcon(
							SmallScrollPanel.class
									.getResource("/org/learning/j2ee/supermarket/util/buttonIcons/zuoyidongon.png")));
			leftScrollButton
					.setRolloverIcon(new ImageIcon(
							SmallScrollPanel.class
									.getResource("/org/learning/j2ee/supermarket/util/buttonIcons/zuoyidongon.png")));
			// 取消按钮内容填充
			leftScrollButton.setContentAreaFilled(false);
			// 设置初始大小
			leftScrollButton.setPreferredSize(new Dimension(38, 0));
			// 取消按钮焦点功能
			leftScrollButton.setFocusable(false);
			// 添加滚动事件监听器
			leftScrollButton.addMouseListener(scrollMouseAdapter);
		}
		return leftScrollButton;
	}

	/**
	 * 创建右侧滚动微调按钮
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getRightScrollButton() {
		if (rightScrollButton == null) {
			rightScrollButton = new JButton();
			// 按钮透明
			rightScrollButton.setOpaque(false);
			// 设置边框
			rightScrollButton.setBorder(createEmptyBorder(0, 0, 0, 10));
			rightScrollButton
					.setIcon(new ImageIcon(
							SmallScrollPanel.class
									.getResource("/org/learning/j2ee/supermarket/util/buttonIcons/youyidongoff.png")));// 设置按钮图标
			rightScrollButton
					.setPressedIcon(new ImageIcon(
							SmallScrollPanel.class
									.getResource("/org/learning/j2ee/supermarket/util/buttonIcons/youyidongon.png")));
			rightScrollButton
					.setRolloverIcon(new ImageIcon(
							SmallScrollPanel.class
									.getResource("/org/learning/j2ee/supermarket/util/buttonIcons/youyidongon.png")));
			// 取消按钮内容绘制
			rightScrollButton.setContentAreaFilled(false);
			// 设置按钮初始大小
			rightScrollButton.setPreferredSize(new Dimension(38, 92));
			// 取消按钮焦点功能
			rightScrollButton.setFocusable(false);
			// 添加滚动事件监听器
			rightScrollButton.addMouseListener(scrollMouseAdapter);
		}
		return rightScrollButton;
	}

	/**
	 * 左右微调按钮的事件监听器
	 * 
	 * @author
	 */
	private final class ScrollMouseAdapter extends MouseAdapter implements
			Serializable {
		private static final long serialVersionUID = 5589204752770150732L;
		// 获取滚动面板的水平滚动条
		JScrollBar scrollBar = getAlphaScrollPanel().getHorizontalScrollBar();
		// 定义线程控制变量
		private boolean isPressed = true;

		public void mousePressed(MouseEvent e) {
			Object source = e.getSource();// 获取事件源
			isPressed = true;
			// 判断事件源是左侧按钮还是右侧按钮，并执行相应操作
			if (source == getLeftScrollButton()) {
				scrollMoved(-1);
			} else {
				scrollMoved(1);
			}
		}

		/**
		 * 移动滚动条的方法
		 * 
		 * @param orientation
		 *            移动方向 -1是左或上移动，1是右或下移动
		 */
		private void scrollMoved(final int orientation) {
			new Thread() {// 开辟新的线程
				// 保存原有滚动条的值
				private int oldValue = scrollBar.getValue();

				public void run() {
					while (isPressed) {// 循环移动面板
						try {
							Thread.sleep(10);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						// 获取滚动条当前值
						oldValue = scrollBar.getValue();
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								// 设置滚动条移动3个像素
								scrollBar.setValue(oldValue + 3 * orientation);
							}
						});
					}
				}
			}.start();
		}

		public void mouseExited(java.awt.event.MouseEvent e) {
			isPressed = false;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			isPressed = false;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 在组件顶层绘制图片
		g.drawImage(icon2.getImage(), 0, 0, getWidth(), getHeight(), this);
	}
} // @jve:decl-index=0:visual-constraint="10,10"
