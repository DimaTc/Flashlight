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
	private Point[] cornerPoints = new Point[4];
	private Point[] shadowPoints = new Point[4];
	private int pointsAmount = 4;
	private int id1;
	private int id2;
	Polygon[] areas = new Polygon[4];
	public Shadow(Point p1, Point p2, Point mousePoint){
		this.points[0] = p1;
		this.points[1] = p2;
		this.mousePoint = mousePoint;
		this.m1 = (mousePoint.y - p1.y) / (mousePoint.x - p1.x);
		this.m2 = (mousePoint.y - p2.y) / (mousePoint.x - p2.x);
		cornerPoints[0] = new Point(0,0);
		cornerPoints[1] = new Point(WinCanvas.WIDTH * WinCanvas.SCALE, 0);
		cornerPoints[2] = new Point(WinCanvas.WIDTH * WinCanvas.SCALE, WinCanvas.HEIGHT * WinCanvas.SCALE);
		cornerPoints[3] = new Point(0, WinCanvas.HEIGHT * WinCanvas.SCALE);
		calculateEdge();
	}

	public void calculateEdge(){  
		for(int i = 0; i < pointsAmount; i++){
			areas[i] = new Polygon(new int[]{cornerPoints[i].x, mousePoint.x, cornerPoints[(i + 1) % 4].x} ,
						new int[]{cornerPoints[i].y, mousePoint.y, cornerPoints[(i + 1) % 4].y} , 3);
			if(areas[i].contains(points[0])){
				id1 = i;
			}

			if(areas[i].contains(points[1])){
				id2 = i;
			}
			
//			if(id1 == id2){
//				pointsAmount = 5;
//				shadowPoints = new Point[5];
//			}
//			if(id1 == id2){
//				pointsAmount = 5;
//				shadowPoints = new Point[4];
//			}

		}
	}
	
	public void update(){
		calculateEdge();
		m1 = (mousePoint.y - points[0].y) / ((mousePoint.x - points[0].x) + 0.0001);
		m2 = (mousePoint.y - points[1].y) / ((mousePoint.x - points[1].x) + 0.0001);
		
		
	}
	public void setPoints(Point[] points){
		this.points = points;
	}
	public void setMousePoint(Point mouse){
		this.mousePoint = mouse;
	}
	public void draw(Graphics2D g){
		g.setColor(Color.red);
		g.drawString(Integer.toString(id1), 40, 40);
		g.drawString(Integer.toString(id2), 40, 80);
		g.drawString(Double.toString(m1), 40, 120);
		g.drawString(Double.toString(m2), 40, 160);

	}
}
