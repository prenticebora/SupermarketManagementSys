package org.learning.j2ee.supermarket.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import org.learning.j2ee.supermarket.panel.ClockPanel;

/**
 * 背景面板
 * 
 * @author
 */
public class BackgroundPanel extends JPanel {
	private Image image;// 背景图片

	public BackgroundPanel() {
		setOpaque(false);
		setLayout(null);// 使用绝对定位布局控件
	}

	public void config() {
		setOpaque(false);// 面板透明
		setImage(getToolkit().getImage(getClass().getResource("login.png")));// 设置面板背景图片

		setLayout(null);

		JPanel clockPanel = new ClockPanel();
		clockPanel.setBounds(377, 54, 151, 142);
		add(clockPanel);
	}

	/**
	 * 设置背景图片对象的方法
	 * 
	 * @param image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * 画出背景
	 */
	protected void paintComponent(Graphics g) {
		if (image != null) {// 如果图片已经初始化
			// 画出图片
			g.drawImage(image, 0, 0, this);
		}
		super.paintComponent(g);
	}

}
