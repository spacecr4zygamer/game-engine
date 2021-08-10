package gameenginepack;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboardlistener implements KeyListener {
	
	private boolean[] keys = new boolean[256];
	
	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		keys[event.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		keys[event.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isKeyPressed(int key) {
		return keys[key];
	}
	
	public boolean isKeyReleased(int key) {
		return !keys[key];
	}
	
}