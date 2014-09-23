package com.dima.Light;

import java.awt.Graphics2D;
import java.awt.Point;

public class Shadow {
	private Point[] points;
	private Point mousePoint;
	private double m1;
	private double m2;
	
	public Shadow(Point p1, Point p2, Point mousePoint){
		this.points[0] = p1;
		this.points[1] = p2;
		this.mousePoint = mousePoint;
		this.m1 = (mousePoint.y - p1.y) / (mousePoint.x - p1.x);
		this.m1 = (mousePoint.y - p2.y) / (mousePoint.x - p2.x);
		calculateEdge();
	}
	
	public void calculateEdge(){  
		
	}
	
	public void update(){
		calculateEdge();
		
	}
	
	public void draw(Graphics2D g){
//		g.fillPolygon(, yPoints, 4);
	}
}
