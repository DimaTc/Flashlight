package com.dima.Light;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class LightSource {
	private BufferedImage lightMap ;
	private int width;
	private int height;
	
	public LightSource(int width, int height){
		lightMap = new BufferedImage(WinCanvas.WIDTH * WinCanvas.SCALE * 2, WinCanvas.HEIGHT* WinCanvas.SCALE * 2 , BufferedImage.TYPE_INT_ARGB);
		this.width = width;
		this.height = height;
		drawLight();
	}
	private void drawLight(){
		int times;
		if(height > width)
			times = height / 3;
		else
			times = width / 3;
		Graphics2D g = lightMap.createGraphics();
		g.setColor(new Color(0,0,0,240));
		g.fillRect(0, 0,WinCanvas.WIDTH * WinCanvas.SCALE * 2, WinCanvas.HEIGHT* WinCanvas.SCALE * 2);
		g.setComposite(AlphaComposite.DstOut);
		for(int i = 0; i < times; i++){
			g.setColor(new Color(0,0,0,4));
			g.fillOval(WinCanvas.WIDTH * WinCanvas.SCALE  - (width - i) / 2, WinCanvas.HEIGHT * WinCanvas.SCALE - (height - i) / 2 , (width - i), (height -  i ));	
		}		
	}
	public BufferedImage getLightMap(){
		return lightMap;
	}
}
