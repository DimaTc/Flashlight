package com.dima.Light;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Box {
	
	private int x;
	private int y;
	private int size;
	private Point[] points;
	
	public Box(int x, int y, int size){
		this.x = x;
		this.y = y;
		this.size = size;
		points = new Point[4];
		points[0] = new Point(x ,y);
		points[1] = new Point(x ,y + size);
		points[2] = new Point(x + size ,y);
		points[3] = new Point(x + size,y + size);
	}
	
	public Point[] getPoints(){
		return points;
	}
	
	public Point[] getFarPoint(Point mouse){
		Point[] tmp = new Point[3];
		int maxSum = 0;
		int avg;
		int sum = 0;
		int i = 0;
		int id = 0;
		for(Point p: points){
			sum = Math.abs(p.x - mouse.x) + Math.abs(p.y - mouse.y);
			if(sum > maxSum){
				maxSum = sum;
				id = i;
			}
			
			i++;
		}
		
		i = 0; 								//iterator of the temporary array.
		for(Point p : points){				//arrange the effective points to a temporary array of points.
			if(p != points[id]){ 			//if j equal to the iterator of the far point, ignore.
				tmp[i] = p;
				i++;
			}
		}
		//		TODO 																	//
		//		find out the length of the average line.								//
		//		calculate the angle between the average line to the other two points.	//
		//		remove the line with the small angle.									//
		
		
		return tmp;
		
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getSize(){
		return size;
	}
	
	public void update(){
		points[0] = new Point(x ,y);
		points[1] = new Point(x ,y + size);
		points[2] = new Point(x + size ,y);
		points[3] = new Point(x + size,y + size);
	}
	
	public void draw(Graphics2D g){
		g.setColor(new Color(0x009900));
		g.fillRect(x, y, size, size);
	}
}
