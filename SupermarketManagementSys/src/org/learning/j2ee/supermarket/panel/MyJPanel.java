package org.learning.j2ee.supermarket.panel;

import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyJPanel extends JPanel {

	public void paintComponent(Graphics g) {
		try {
			URL url = getClass().getResource(
					"/org/learning/j2ee/supermarket/util/buttonIcons/back.jpg");
			ImageIcon image = new ImageIcon(url);
			g.drawImage(image.getImage(), 0, 0, this);
		} catch (Exception e) {
		}
	}
}
