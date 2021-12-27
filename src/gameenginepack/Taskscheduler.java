package gameenginepack;

import javax.swing.JPanel;
import java.awt.*;

public class Taskscheduler extends JPanel implements Runnable{

	private final Game game;

	//private final Graphics graphic;

	public Taskscheduler(Game game) {
		this.game = game;
		//this.graphic = game.getWindow().getGraphics().create(0,0,800,600);
		setFocusable(true);
		//System.out.println(isDoubleBuffered());
		//setDoubleBuffered(false);
		//repaint();
	}

	
	@Override
	public void run() {
		//paint(graphic);
		while (true) {
			//System.out.println("Running");
			try {
				if (game.getScreenFactory().getCurrentScreen()!=null) {
					game.getScreenFactory().getCurrentScreen().onUpdate();
					repaint();
					Thread.sleep(16);
					//System.out.println(game.getWindow().getGraphics().create());
					//paint(game.getWindow).getGraphics().create(200,200,800,600));
					//paint(graphic);
					//Thread.sleep(9);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}



	@Override
	public void paint(Graphics g)  {
		//System.out.println("Painting");

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		if (game.getScreenFactory().getCurrentScreen()!=null) {
			game.getScreenFactory().getCurrentScreen().onDraw(g2d);
		}
		//repaint();
	}
	
}
