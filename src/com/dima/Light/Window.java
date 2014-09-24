package com.dima.Light;


import javax.swing.JFrame;

public class Window {
	public static void main(String[] args){
		JFrame frame = new JFrame("Light");
		WinCanvas canvas = new WinCanvas();
		frame.add(canvas);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		canvas.start();
	}

}
