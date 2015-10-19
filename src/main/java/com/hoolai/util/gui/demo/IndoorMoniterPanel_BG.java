package com.hoolai.util.gui.demo;

import static com.hoolai.util.gui.ImageFinder.findImageIcon;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class IndoorMoniterPanel_BG extends JPanel {
	
	private static final int WIDTH = 550;
	private static final int HEIGHT = 605;
	
	public static void main(String[] args) {
		JFrame f = new JFrame("Test ppt");
		IndoorMoniterPanel_BG ppt = new IndoorMoniterPanel_BG();
		f.add(ppt);
		f.setSize(WIDTH, HEIGHT);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		f.setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
	}
	
	ImageIcon img0 = findImageIcon("/Image/p1.jpg");
	ImageIcon img1 = findImageIcon("/Image/p2.jpg");
	ImageIcon img2 = findImageIcon("/Image/p3.jpg");
	ImageIcon img3 = findImageIcon("/Image/p4.jpg");
	ImageIcon img4 = findImageIcon("/Image/p5.jpg");
	ImageIcon img5 = findImageIcon("/Image/p6.jpg");
	public JLayeredPane layeredPane = new JLayeredPane();
	public JLabel distance = new JLabel("距离");
	public JTextField distanceData = new JTextField(4);
	public JLabel cm = new JLabel("cm");
	public JLabel calam = new JLabel("报警提示");
	public JTextField calamData = new JTextField(4);
	Point origin = new Point(10, 20);
	JLabel bg0 = new JLabel(img0);
	JLabel bg1 = new JLabel(img1);
	JLabel bg2 = new JLabel(img2);
	JLabel bg3 = new JLabel(img3);// 每次导入即声明一个对象
	JLabel bg4 = new JLabel(img4);// 每次导入即声明一个对象
	JLabel bg5 = new JLabel(img5);// 每次导入即声明一个对象

	public IndoorMoniterPanel_BG() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(1200, 760));
		layeredPane.setOpaque(false);

		JPanel panel = new JPanel();
		// panel.add(layeredPane);
		panel.add(distance);
		panel.add(distanceData);
		panel.add(cm);
		panel.add(calam);
		panel.add(calamData);
		panel.setOpaque(false);
		layeredPane.add(panel, new Integer(7));
		panel.setBounds(50, 700, img0.getIconWidth(), img0.getIconHeight());

		layeredPane.add(bg0, new Integer(5));
		bg0.setBounds(0, 0, img0.getIconWidth(), img0.getIconHeight());

		layeredPane.add(bg1, new Integer(4));
		bg1.setBounds(0, 0, img1.getIconWidth(), img1.getIconHeight());

		layeredPane.add(bg2, new Integer(3));
		bg2.setBounds(0, 0, img1.getIconWidth(), img2.getIconHeight());

		layeredPane.add(bg3, new Integer(2));
		bg3.setBounds(0, 0, img3.getIconWidth(), img3.getIconHeight());

		layeredPane.add(bg4, new Integer(1));
		bg4.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());

		layeredPane.add(bg5, new Integer(0));
		bg4.setBounds(0, 0, img5.getIconWidth(), img5.getIconHeight());

		add(layeredPane);
		Timer timer = new Timer(1000, new TimerListener());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		timer.start();
	}

	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double k = Math.random() * 100;
			distanceData.setText(String.valueOf(k));

			if (k < 70) {
				layeredPane.setLayer(bg1, new Integer(6)); // 重新设置组件层数
				System.out.println("k1=" + k);
			}
			if (k < 60) {
				layeredPane.setLayer(bg2, new Integer(6)); // 重新设置组件层数
				System.out.println("k2=" + k);
			}
			if (k < 50) {
				layeredPane.setLayer(bg3, new Integer(6)); // 重新设置组件层数
				System.out.println("k3=" + k);
			}
			if (k < 40) {
				layeredPane.setLayer(bg4, new Integer(6)); // 重新设置组件层数
				System.out.println("k4=" + k);
			}

			if (k < 20) {
				layeredPane.setLayer(bg0, new Integer(6)); // 重新设置组件层数

			}

		}
	}
}
