package com.dima.Light;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class LightSource {
	private BufferedImage lightMap ;
	private int size;
	
	public LightSource(int size){
		lightMap = new BufferedImage(WinCanvas.WIDTH * WinCanvas.SCALE * 2, WinCanvas.HEIGHT* WinCanvas.SCALE * 2 , BufferedImage.TYPE_INT_ARGB);
		this.size = size;
		drawLight();
	}
	private void drawLight(){
		int times;
		times = size / 3;
		Graphics2D g = lightMap.createGraphics();
		g.setColor(new Color(0,0,0,252));
		g.fillRect(0, 0,WinCanvas.WIDTH * WinCanvas.SCALE * 2, WinCanvas.HEIGHT* WinCanvas.SCALE * 2);
		g.setComposite(AlphaComposite.DstOut );
		for(int i = 0; i < times; i++){
			g.setColor(new Color(0,0,0,4));
			g.fillOval(WinCanvas.WIDTH * WinCanvas.SCALE  - (size - i) / 2, WinCanvas.HEIGHT * WinCanvas.SCALE - (size- i) / 2 , (size - i), (size -  i ));	
		}		
	}
	public BufferedImage getLightMap(){
		return lightMap;
	}
	
	public int getSize(){
		return size;
	}
}
