package gameenginepack;

import javax.swing.JPanel;
import java.awt.*;

public class Taskscheduler extends JPanel implements Runnable{
	
	private final Game game;
	
	public Taskscheduler(Game game) {
		this.game = game;
		setFocusable(true);
		repaint();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			//System.out.println("Running");
			try {
				if (game.getScreenFactory().getCurrentScreen()!=null) {
					game.getScreenFactory().getCurrentScreen().onUpdate();
					Thread.sleep(1);

					Thread.sleep(9);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void paint(Graphics g)  {
		System.out.println("Painting");
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		if (game.getScreenFactory().getCurrentScreen()!=null) {
			game.getScreenFactory().getCurrentScreen().onDraw(g2d);
		}
		repaint();
	}
	
}
