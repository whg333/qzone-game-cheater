package com.hoolai.util.gui.demo;

/*
 * www.codefans.net
 */


//imports
import static com.hoolai.util.gui.ImageFinder.findImageIcon;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class SR_Slider extends Frame implements ActionListener, WindowListener, Runnable
{
  public static void main(String Pagli[])
  {
    new SR_Slider();
  }

  Thread time; //new thread
  File fl;
  URL dir;
  MediaTracker mt;

  //here you can use an array
  Image p1, p2, p3, p4, p5, p6, p7;

  About ab; //instance of About class
  Panel bottom;

  AudioClip b_n, rfs;
  Toolkit tk;
  Dimension dim;

  Button back, next, refresh, auto, abt, ext;
  Color sky;
  Font f;
  int count, x;

  public SR_Slider()
  {
    setTitle("SR-Slider 1.0");
    setLayout(new BorderLayout());
    bottom = new Panel();

    time = new Thread(this);
    sky = new Color(0,140,255); //color - skyblue
    f = new Font("Courier",Font.BOLD,16);
    count = 0;
    x = 0;
    mt=new MediaTracker(this);

    try
    {
      //getting all images and add them to the MediaTracker
      p1 = findImageIcon("/Image/p1.jpg").getImage();
      mt.addImage(p1, 0);
      p2 = findImageIcon("/Image/p2.jpg").getImage();
      mt.addImage(p2, 1);
      p3 = findImageIcon("/Image/p3.jpg").getImage();
      mt.addImage(p3, 2);
      p4 = findImageIcon("/Image/p4.jpg").getImage();
      mt.addImage(p4, 3);
      p5 = findImageIcon("/Image/p5.jpg").getImage();
      mt.addImage(p5, 4);
      p6 = findImageIcon("/Image/p6.jpg").getImage();
      mt.addImage(p6, 5);
      p7 = findImageIcon("/Image/p7.jpg").getImage();
      mt.addImage(p7, 6);

      fl = new File("user.dir");
      dir = fl.toURL();
      b_n = Applet.newAudioClip(new URL(dir, "Sound/bk_nxt.au"));
      rfs = Applet.newAudioClip(new URL(dir, "Sound/rfsh.au"));
    }

    catch(MalformedURLException e)
    {
      //do nothing!
    }

    //designing back button	
    back=new Button("Back");
    back.setFont(f);
    back.setBackground(sky);
    back.setForeground(Color.white);

    //designing next button
    next=new Button("Next");
    next.setFont(f);
    next.setBackground(sky);
    next.setForeground(Color.white);

    //designing refresh button
    refresh=new Button("Reset");
    refresh.setFont(f);
    refresh.setBackground(sky);
    refresh.setForeground(Color.white);

    //designing auto button
    auto=new Button("SlideShow");
    auto.setFont(f);
    auto.setBackground(sky);
    auto.setForeground(Color.white);

    //designing abt button
    abt=new Button("About");
    abt.setFont(f);
    abt.setBackground(sky);
    abt.setForeground(Color.white);

    //designing ext button
    ext=new Button("Exit");
    ext.setFont(f);
    ext.setBackground(sky);
    ext.setForeground(Color.white);

    //adding ActionListener to all buttons
    back.addActionListener(this);
    next.addActionListener(this);
    refresh.addActionListener(this);
    auto.addActionListener(this);
    abt.addActionListener(this);
    ext.addActionListener(this);

    //adding all components to the Frame
    bottom.add(back);
    bottom.add(next);
    bottom.add(refresh);
    bottom.add(auto);
    bottom.add(abt);
    bottom.add(ext);

    add(bottom, BorderLayout.SOUTH);
    addWindowListener(this);

    tk = this.getToolkit();
    dim = tk.getScreenSize(); //getting the current screen resolution

    setBounds(dim.width/6, dim.height/6, 540, 420);
    setResizable(false);
    setVisible(true);
  }

  public void paint(Graphics g)
  {
    if(count <  1)
     g.drawImage(p1, 0, 0, this);

    if(count == 1)
     g.drawImage(p2, 0, 0, this);

    if(count == 2)
     g.drawImage(p3, 0, 0, this);

    if(count == 3)
     g.drawImage(p4, 0, 0, this);

    if(count == 4)
     g.drawImage(p5, 0, 0, this);

    if(count == 5)
     g.drawImage(p6, 0, 0, this);

    if(count == 6)
     g.drawImage(p7, 0, 0, this);
  }

  public void update(Graphics g)
  {
    paint(g);
  }

  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource() == ext)
    {
      dispose();
      setVisible(false);
      System.exit(0);
    }

    if(ae.getSource() == next)
    {
      b_n.play();

      if(count < 7)
      {
	count++;
	repaint();
      }
    }

    if(ae.getSource() == back)
    {
      b_n.play();

      if(count == 0)
       count=0;

      if(count > 0)
      {
	count--;
	repaint();
      }
    }

    if(ae.getSource() == refresh)
    {
      rfs.play();
      time.suspend(); //suspends the thread
      count=0;
      repaint();
    }

    if(ae.getSource() == auto)
    {
      b_n.play();

      if(x < 1)
      {
	time.start();
	x++;
      }

      else
      {
	time.resume(); //resumes the thread
      }
    }

    if(ae.getSource() == abt)
    {
      ab = new About();
    }
  }

  public void run()
  {
    try
    {
      for(int i=1;i>0;i++)
      {
	b_n.play();
	time.sleep(3000);
	count++;

	if(count > 6)
	{
	  time.suspend(); //suspends the thread
	  count = 0;
	}

	repaint();
      }
    }

    catch(InterruptedException e)
    {
      //do nothing!
    }
  }

  //handling window events
  public void windowClosing(WindowEvent we)
  {
    dispose();
    setVisible(false);
    System.exit(0);
  }

  public void windowClosed(WindowEvent we)
  {
    //do nothing!
  }

  public void windowOpened(WindowEvent we)
  {
    //do nothing!
  }

  public void windowActivated(WindowEvent we)
  {
    //do nothing!
  }

  public void windowDeactivated(WindowEvent we)
  {
    //do nothing!
  }

  public void windowIconified(WindowEvent we)
  {
    //do nothing!
  }

  public void windowDeiconified(WindowEvent we)
  {
    //do nothing!
  }

  class About extends Frame implements WindowListener
  {
    Font f, f2;

    private About()
    {
      setTitle("About");
      setLayout(new FlowLayout());

      f = new Font("Verdana", Font.BOLD, 20);
      f2 = new Font("Verdana", Font.PLAIN, 12);

      addWindowListener(this);
      tk = this.getToolkit();
      dim = tk.getScreenSize(); //getting the current screen resolution

      setBounds(dim.width/4, dim.height/4, 300, 190);
      setResizable(false);
      setVisible(true);
    }

    public void paint(Graphics g)
    {
      g.setColor(Color.red);
      g.setFont(f);
      g.drawString("SR-Slider 1.0", 10, 60);
      g.setColor(Color.black);
      g.setFont(f2);
      g.drawString("Author: Mohammad Ahsanul Haque Shovon", 10, 90);
      g.drawString("http://www29.websamba.com/shovon", 10, 110);
      g.drawString("ahsanul_haque_shovon@yahoo.com", 10, 130);
      g.drawString("(C) ShuvoRim Pvt. Ltd.", 10, 150);
      g.drawString("All rights reserved.", 10, 170);
    }

    //handling window events
    public void windowClosing(WindowEvent we)
    {
      dispose();
      setVisible(false);
    }

    public void windowClosed(WindowEvent we)
    {
      //do nothing!
    }

    public void windowOpened(WindowEvent we)
    {
      //do nothing!
    }

    public void windowActivated(WindowEvent we)
    {
      //do nothing!
    }

    public void windowDeactivated(WindowEvent we)
    {
      //do nothing!
    }

    public void windowIconified(WindowEvent we)
    {
      //do nothing!
    }

    public void windowDeiconified(WindowEvent we)
    {
      //do nothing!
    }
  }
}
