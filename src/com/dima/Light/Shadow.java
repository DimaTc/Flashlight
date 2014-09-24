package com.dima.Light;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class Shadow {
	private Point[] points = new Point[2];
	private Point mousePoint;
	private double m1;
	private double m2;
	private Point[] cornerPoint = new Point[4];
	private int pointsAmount = 4;
	Polygon[] areas = new Polygon[4];
	
	public Shadow(Point p1, Point p2, Point mousePoint){
		this.points[0] = p1;
		this.points[1] = p2;
		this.mousePoint = mousePoint;
		this.m1 = (mousePoint.y - p1.y) / (mousePoint.x - p1.x);
		this.m1 = (mousePoint.y - p2.y) / (mousePoint.x - p2.x);
		cornerPoint[0] = new Point(0,0);
		cornerPoint[1] = new Point(WinCanvas.WIDTH * WinCanvas.SCALE, 0);
		cornerPoint[2] = new Point(WinCanvas.WIDTH * WinCanvas.SCALE, WinCanvas.HEIGHT * WinCanvas.SCALE);
		cornerPoint[3] = new Point(0, WinCanvas.HEIGHT * WinCanvas.SCALE);
		calculateEdge();
	}
	
	public void calculateEdge(){  
		for(int i = 0; i < pointsAmount; i++){
			areas[i] = new Polygon(new int[]{cornerPoint[i].x, mousePoint.x, cornerPoint[(i + 1) % 4].x} ,
						new int[]{cornerPoint[i].y, mousePoint.y, cornerPoint[(i + 1) % 4].y} , 3);
			if(areas[i].contains(points[0])){
				
			}
		}
	}
	
	public void update(){
		calculateEdge();
		
	}
	
	public void setMousePoint(Point mouse){
		this.mousePoint = mouse;
	}
	public void draw(Graphics2D g){
		if(areas[0] != null){
			g.setColor(Color.blue);
			g.fill(areas[0]);
			g.setColor(Color.red);
			g.fill(areas[1]);
			g.setColor(Color.green);
			g.fill(areas[2]);
			g.setColor(Color.yellow);
			g.fill(areas[3]);
		}
	}
}
