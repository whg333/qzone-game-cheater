package com.hoolai.util.gui.demo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class InputDialog implements ActionListener
{
    JFrame f = null;
    JLabel label = null;
    
    public InputDialog() 
    {
        f = new JFrame("OptionPane Demo");
        Container contentPane = f.getContentPane();
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        
        JButton b = new JButton("Show Text Input");
        b.addActionListener(this);
        panel.add(b);
        b = new JButton("Show ComboBox Input");
        b.addActionListener(this);
        panel.add(b);
        
        label = new JLabel(" ",JLabel.CENTER);
        contentPane.add(label,BorderLayout.NORTH);
        contentPane.add(panel,BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
        
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }    
    
    public static void main(String[] args)
    {
    	SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				new InputDialog();
			}
		});
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        String cmd = e.getActionCommand();
        String title = "Input Dialog";
        String message ="您最熟悉哪一种程序语言？";
        int messageType = JOptionPane.QUESTION_MESSAGE;
        String[] values = {"JAVA","PHP","ASP","C++","VB"};
        String result ="";

        if(cmd.equals("Show Text Input")) {
            result = JOptionPane.showInputDialog(f, message, 
                     title, messageType);
            
        } else if(cmd.equals("Show ComboBox Input")) {
            result = (String)JOptionPane.showInputDialog(f, message, 
                     title, messageType,null,values,values[0]);
        }
    
        if (result == null)
            label.setText("您取消了对话框");
        else{
            label.setText("您输入："+result);
        }
    }
}
