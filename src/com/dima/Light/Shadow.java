package com.dima.Light;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class Shadow {
	private Point[] points = new Point[2];
	private Point mousePoint;
	private double m0;
	private double m1;
	private Point[] cornerPoints = new Point[4];
	private Point[] shadowPoints = new Point[4];
	private int pointsAmount = 4;
	private int shadowPointsAmount = 4;
	private int id1;
	private int id2;
	private int shadow0X;
	private int shadow0Y;
	private int shadow1X;
	private int shadow1Y;
	private Polygon shadow = new Polygon();
	private Polygon[] areas = new Polygon[4];
	public Shadow(Point p1, Point p2, Point mousePoint){
		this.points[0] = p1;
		this.points[1] = p2;
		this.mousePoint = mousePoint;
		this.m0 = (mousePoint.y - p1.y) / (mousePoint.x - p1.x);
		this.m1 = (mousePoint.y - p2.y) / (mousePoint.x - p2.x);
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
		}
		switch(id1){
		case 0 :
			shadow0Y = 0;
			shadow0X =(int)( ((shadow0Y - points[0].y) / -m0) - points[0].x);
			break;
		case 1 :
			shadow0X = WinCanvas.WIDTH * WinCanvas.SCALE;				
			shadow0Y = (int)(-m0 * (shadow0X - points[0].x) - points[0].y);
			break;
		case 2 :
			shadow0Y = WinCanvas.HEIGHT * WinCanvas.SCALE;
			shadow0X =(int)( ((shadow0Y - points[0].y) / -m0) - points[0].x);
			break;
		case 3 :
			shadow0X = 0;				
			shadow0Y = (int)(-m0 * (shadow0X - points[0].x) - points[0].y);
			break;
		default:
			break;
		}
		
		switch(id2){
		case 0 :
			shadow1Y = 0;
			shadow1X =(int)( ((shadow1Y - points[1].y) / -m1 + 0.000001) - points[1].x);
			break;
		case 1 :
			shadow1X = WinCanvas.WIDTH * WinCanvas.SCALE;	
			shadow1Y = (int)(-m1 * (shadow1X - points[1].x) - points[1].y);
			break;
		case 2 :
			shadow1Y = WinCanvas.HEIGHT * WinCanvas.SCALE;
			shadow1X =(int)( ((shadow1Y - points[1].y) / -m1 + 0.000001) - points[1].x);
			break;
		case 3 :
			shadow1X = 0;				
			shadow1Y = (int)(-m1 * (shadow1X - points[1].x) - points[1].y);
			break;
		default:
			break;
		}
		
		shadow0X = Math.abs(shadow0X);
		shadow0Y = Math.abs(shadow0Y);
		shadow1X = Math.abs(shadow1X);
		shadow1Y = Math.abs(shadow1Y);
		
		
		if(id1 != id2){
			shadowPointsAmount = 5;
			shadowPoints = new Point[5];
		}
		else{
			shadowPointsAmount = 4;
			shadowPoints = new Point[4];
		}
		
		if(shadowPointsAmount == 4){
			shadowPoints[2] = new Point(shadow1X, shadow1Y);
			shadowPoints[3] = new Point(shadow0X, shadow0Y);
		}
		else{

			shadowPoints[2] = new Point(shadow1X, shadow1Y);
			switch(id1){
			case 0:
				if(id2 == 1)
					shadowPoints[3] = new Point(WinCanvas.WIDTH * WinCanvas.SCALE, 0);
				else
					shadowPoints[3] = new Point(0, 0);
				break;
					
			case 1:
				if(id2 == 0)
					shadowPoints[3] = new Point(WinCanvas.WIDTH * WinCanvas.SCALE, 0);
				else
					shadowPoints[3] = new Point(WinCanvas.WIDTH * WinCanvas.SCALE, WinCanvas.HEIGHT * WinCanvas.SCALE);
				break;
				
			case 2:
				if(id2 == 1)
					shadowPoints[3] = new Point(WinCanvas.WIDTH * WinCanvas.SCALE, WinCanvas.HEIGHT * WinCanvas.SCALE);
				else
					shadowPoints[3] = new Point(0, WinCanvas.HEIGHT * WinCanvas.SCALE);
				break;
				
			case 3:
				if(id2 == 2)
					shadowPoints[3] = new Point(0, WinCanvas.HEIGHT * WinCanvas.SCALE);
				else
					shadowPoints[3] = new Point(0, 0);
				break;
				
			}
			shadowPoints[4] = new Point(shadow0X, shadow0Y);
		}
		for(int i = 0; i < 2; i++){
			shadowPoints[i] = points[i];
		}
		int[] xPoints = new int[shadowPointsAmount];
		int[] yPoints = new int[shadowPointsAmount];
		for(int i = 0; i < shadowPointsAmount; i++){
			xPoints[i] = shadowPoints[i].x;
			yPoints[i] = shadowPoints[i].y;
		}
		shadow.xpoints = xPoints;
		shadow.ypoints = yPoints;
		shadow.npoints = shadowPointsAmount;
			
	}
	
	public void update(){
		calculateEdge();
		m0 = (mousePoint.y - points[0].y) / ((mousePoint.x - points[0].x) + 0.000000001);
		m1 = (mousePoint.y - points[1].y) / ((mousePoint.x - points[1].x) + 0.000000001);
		
		
	}
	public void setPoints(Point[] points){
		this.points = points;
	}
	public void setMousePoint(Point mouse){
		this.mousePoint = mouse;
	}
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.fill(shadow);
	}
}
