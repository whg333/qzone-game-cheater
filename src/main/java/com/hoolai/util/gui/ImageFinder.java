package com.hoolai.util.gui;

import javax.swing.ImageIcon;

public class ImageFinder {

	public static ImageIcon findImageIcon(String imgPath){
		//System.out.println(imgPath);
		//System.out.println(ImageFinder.class.getResource(imgPath).getPath());
		return new ImageIcon(ImageFinder.class.getResource(imgPath));
	}
	
}
