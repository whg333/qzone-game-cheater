package com.hoolai.util.gui;

import static com.hoolai.util.gui.ImageFinder.findImageIcon;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SliderImagePanel extends JPanel {

	private static final int WIDTH = 550;
	private static final int HEIGHT = 605;

	public static void main(String[] args) {
		JFrame f = new JFrame("Test ppt");
		List<ImageIcon> images = new ArrayList<ImageIcon>();
		images.add(findImageIcon("/Image/1.jpg"));
		images.add(findImageIcon("/Image/2.jpg"));
		images.add(findImageIcon("/Image/3.jpg"));
		images.add(findImageIcon("/Image/4.jpg"));
		SliderImagePanel sliderImagePanel = new SliderImagePanel(images, 3000);
		f.add(sliderImagePanel);
		f.setSize(WIDTH, HEIGHT);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		f.setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
		
		sliderImagePanel.start();
	}

	private JLayeredPane layeredPane = new JLayeredPane();

	private static final int IMAGE_WIDTH = 400;
	private static final int IMAGE_HEIGHT = 300;
	
	private final List<ImageIcon> imgList;
	private final List<JLabel> bgList;
	private int bgIndex;
	
	private Timer timer;
	
	public SliderImagePanel(List<ImageIcon> images){
		this(images, 2000);
	}
	
	public SliderImagePanel(List<ImageIcon> images, int silderDelay) {
		this.imgList = images;
		this.bgList = bgs();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
		layeredPane.setOpaque(false);

		for(int i=0;i<images.size();i++){
			layeredPane.add(bgList.get(i), new Integer(images.size()-i));
			bgList.get(i).setBounds(0/*i*10*/, 0/*i*10*/, images.get(i).getIconWidth(), images.get(i).getIconHeight());
		}
		
		add(layeredPane);
//		layeredPane.setLayer(bgs.get(1), images.size());
//		layeredPane.setLayer(bgs.get(0), 0);
//		
//		layeredPane.setLayer(bgs.get(2), images.size());
//		layeredPane.setLayer(bgs.get(1), 1);
		
		
		timer = new Timer(silderDelay, new SliderImageListener());
	}
	
	private List<JLabel> bgs() {
		List<JLabel> bgs = new ArrayList<JLabel>();
		for(ImageIcon image:imgList){
			bgs.add(new JLabel(image));
		}
		return bgs;
	}
	
	public void start(){
		timer.start();
	}
	
	public void stop(){
		timer.stop();
	}
	
	private final class SliderImageListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bgIndex++;
			if(bgIndex >= imgList.size()){
				bgIndex = 0;
			}

			layeredPane.setLayer(bgList.get(bgIndex), new Integer(imgList.size()));
			
			// 除了设置pgIndex为最高层，还需要设置上一个索引为原来的层，即相当于和最高层替换一下
			int lastImageIndex = bgIndex-1 < 0 ? imgList.size()-1 : bgIndex-1;
			layeredPane.setLayer(bgList.get(lastImageIndex), new Integer(lastImageIndex));
			
			//System.out.println("pgIndex=" + pgIndex+",image="+images.get(pgIndex).getDescription());
		}
	}
}
