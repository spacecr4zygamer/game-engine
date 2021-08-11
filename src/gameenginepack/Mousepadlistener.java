package gameenginepack;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mousepadlistener implements MouseListener {

	private int mouseX, mouseY;
	private boolean clicked;
	
	
	@Override
	public void mouseClicked(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
		clicked = true;
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		
	}

	@Override
	public void mouseExited(MouseEvent event) {
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		mouseClicked(event);
		//clicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent event) {
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
