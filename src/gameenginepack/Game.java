package gameenginepack;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class Game {
	
	private final JFrame window = new JFrame();
	private final ScreenFactory screenFactory;
	private final GameThread gameThread;
	private final Keyboardlistener keyboardlistener;
	private final Mousepadlistener mousepadlistener;
	//private JLabel Test;
	
	public Game(int windowX, int windowY, String title) {
		window.setSize(windowX,windowY);
		window.setLayout(new BorderLayout());
		//System.out.println(window.getLayout());
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setFocusable(true);
		window.setLocationRelativeTo(null);
		window.setTitle(title);
		window.setVisible(true);
		
		screenFactory = new ScreenFactory(this);
		gameThread = new GameThread(this);
		keyboardlistener = new Keyboardlistener();
		mousepadlistener = new Mousepadlistener();
		
		//Test = new JLabel("HEEEEY");
		//Test.setBounds(500, 200, 200, 200);
		//Test.setSize(100,100);
		//Test.setLocation(100,100);
		//Test.setVisible(true);
		
		window.add(gameThread);
		//window.add(Test,BorderLayout.SOUTH);
		window.addKeyListener(keyboardlistener);
		window.addMouseListener(mousepadlistener);
		
		new Thread(gameThread).start();
	}
	
	public Mousepadlistener getMouseListener() {
		return mousepadlistener;
	}
	
	public Keyboardlistener getKeyBoardListener() {
		return keyboardlistener;
	}
	
	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}
	
	public JFrame getWindow() {
		return window;
	}
}
