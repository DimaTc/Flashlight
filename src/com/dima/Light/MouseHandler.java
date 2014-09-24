
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseMotionListener{

	private int x = 0;
	private int y = 0;
	public void mouseDragged(MouseEvent e) {
		x = e.getX(); 
		y = e.getY();

	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX(); 
		y = e.getY();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Point getPoint(){
		return new Point(x,y);
	}

}
