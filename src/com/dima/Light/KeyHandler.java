package com.dima.Light;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	private boolean showPicture = false;	
	private boolean isLightOn 	= false;
	private boolean isLinesOn 	= false;
	private boolean isCursorOn 	= false;
	
	public void keyPressed(KeyEvent arg0) {
		
		toggle(arg0.getKeyCode());
	}

	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyTyped(KeyEvent arg0) {
	}
	
	public void toggle(int k){
		switch(k){
		case KeyEvent.VK_SPACE:
			isLightOn = !isLightOn;
			break;
		case KeyEvent.VK_M:
			showPicture = !showPicture;
			break;
		case KeyEvent.VK_L:
			isLinesOn = !isLinesOn;
			break;
		case KeyEvent.VK_C:
			isCursorOn = !isCursorOn;
			break;
		}
		
	}
	
	public boolean isPicureOn(){
		return showPicture;
	}
	public boolean isLightsOn(){
		return isLightOn;
	}
	public boolean isLinesOn(){
		return isLinesOn;
	}
	public boolean isCursorOn(){
		return isCursorOn;
	}

}
