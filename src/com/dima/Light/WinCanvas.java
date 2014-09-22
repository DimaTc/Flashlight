package com.dima.Light;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WinCanvas extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	private static final Dimension dim  = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);		
	private BufferedImage image;
	
	private boolean running;
	private Thread thread;
	private LightSource light;
	private MouseHandler mh;
	
	private int fps = 0;
	private int ups = 0;

	public WinCanvas(){
		setPreferredSize(dim);
		setMaximumSize(dim);
		setMinimumSize(dim);
		light = new LightSource(180, 180);
		mh = new MouseHandler();
		addMouseMotionListener(mh);
		try {
			image = ImageIO.read(getClass().getResource("/2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void start(){
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running) return;
		running = false;
		try{
			thread.join();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		long lastTime = System.nanoTime();
		double delta = 0;
		double ns = 1000000000 / 60;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				delta--;
				update();
				ups++;
			}
			draw();
			fps++;
			if(System.currentTimeMillis() - 1000 > timer){
				timer += 1000;
				System.out.printf("FPS - %d || UPS - %d \n",fps,ups);
				fps = 0;
				ups = 0;
			}
		}
	}
	
	public void update(){
		
	}
	
	public void draw(){
		BufferStrategy bs = getBufferStrategy();

		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		//
		g.setColor(Color.black);
		g.fillRect(0, 0, dim.width, dim.height);
		g.drawImage(image, 0, 0, dim.width, dim.height,null);
		g.setComposite(AlphaComposite.DstOut);
		g.drawImage(light.getLightMap(),(-dim.width / 2) - (dim.width / 2) + mh.getX(),(-dim.height / 2) - (dim.height/ 2) + mh.getY(),null);
		//
		bs.show();
		g.dispose();
		
	}

		
}
