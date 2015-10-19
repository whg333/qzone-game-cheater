package com.hoolai.util.gui.demo;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LoginSucces extends JFrame {

	public LoginSucces(String userName) {
		JLabel label = new JLabel("欢迎您，" + userName);
		setSize(new Dimension(800, 600));
		add(label);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
