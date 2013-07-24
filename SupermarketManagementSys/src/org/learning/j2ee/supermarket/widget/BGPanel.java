package org.learning.j2ee.supermarket.widget;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;

public class BGPanel extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;
	private ImageIcon icon; // @jve:decl-index=0:
	public static final int HORIZONGTAL_FILL = 1;
	public static final int VERTICAL_FILL = 2;
	public static final int BOTH_FILL = 3;
	public static final int NO_FILL = 0;
	private int iconFill = NO_FILL;

	/**
	 * This is the default constructor
	 */
	public BGPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(new Dimension(300, 200));
		this.setLayout(new GridBagLayout());
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (icon != null) {
			switch (iconFill) {
			case NO_FILL:
				g.drawImage(icon.getImage(), 0, 0, this);
				break;
			case HORIZONGTAL_FILL:
				g.drawImage(icon.getImage(), 0, 0, getWidth(),
						icon.getIconHeight(), this);
				break;
			case VERTICAL_FILL:
				g.drawImage(icon.getImage(), 0, 0, icon.getIconWidth(),
						getHeight(), this);
				break;
			case BOTH_FILL:
				g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(),
						this);
				break;
			default:
				break;
			}
		}
	}

	public int getIconFill() {
		return iconFill;
	}

	/**
	 * 设置背景重复方式
	 * 
	 * @param repeat
	 * 
	 */
	public void setIconFill(int iconFill) {
		this.iconFill = iconFill;
	}

}
