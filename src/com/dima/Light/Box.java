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
	
	public Point[] getEdgePoints(Point mouse){
		int maxSum = 	0;
		int minSum =  	22200000;
		int idMax = 	0;
		int idMin = 	0;
		int sum = 		0;
		int i = 		0;
		int id = 		0;
		double mMin, mAvg, mMax;				//the Average line, must be in the final point.
		double angle1, angle2;
		
		Point[] tmp = new Point[3];
		Point[] edgePoints = new Point[2];
		Point avg = new Point(0,0);
		
		for(Point p: points){
			sum = Math.abs(p.x - mouse.x) + Math.abs(p.y - mouse.y);
			if(sum > maxSum){
				maxSum = sum;
				id = i;
			}
			
			i++;
		}
		maxSum = 0;
		i = 0; 								//iterator of the temporary array.
		for(Point p : points){				//arrange the effective points to a temporary array of points.
			if(p != points[id]){ 			//if j equal to the iterator of the far point, ignore.
				tmp[i] = p;
				sum = Math.abs(p.x - mouse.x) + Math.abs(p.y - mouse.y);
				if(sum > maxSum){			//ordering the Minimum point and the Maximum.
					maxSum = sum;			
					idMax = i;
				}
				else if(sum < minSum){
					minSum = sum;
					idMin= i;
				}
				if(i == 0)
					maxSum = minSum = sum;	//to make sure every value counts, in the first run both min and max are applied.
				i++;
			}
		}
		i = 0;
		for(Point p : tmp){
			if( p != tmp[idMin] && p != tmp[idMax] ){
				avg = p;					//getting the Average point.
				i++;
			}
		}
		//Calculating the slope for all 3 points.
		mMin = (tmp[idMin].y - mouse.y) / ((tmp[idMin].x - mouse.x) + 0.001);
		mAvg = (avg.y - mouse.y) / ((avg.x - mouse.x) + 0.001);
		mMax = (tmp[idMax].y - mouse.y) / ((tmp[idMax].x - mouse.x) + 0.001);
		
		//Calculating the angle of max/min slope with the avg slope.
		angle1 = (mMin - mAvg) / (1 + mMin * mAvg) ;
		angle2 = (mMax- mAvg) / (1 + mMax * mAvg) ;
		
		edgePoints[0] = avg;
		if(Math.abs(angle1) > Math.abs(angle2))
			edgePoints[1] = tmp[idMin];
		else
			edgePoints[1] = tmp[idMax];
		return edgePoints;
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
