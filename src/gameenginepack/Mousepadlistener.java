package gameenginepack;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mousepadlistener implements MouseListener {

	private int mouseX, mouseY;
	private boolean clicked;
	
	
	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		mouseX = event.getX();
		mouseY = event.getY();
		clicked = true;
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
		mouseClicked(event);
		//clicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub
		//mouseClicked(event);
		clicked = false;
	}
	
	public boolean isMousePressed() {
		return clicked;
	}
	
	public int getX() {
		return mouseX;
	}
	
	public int getY() {
		return mouseY;
	}

}
