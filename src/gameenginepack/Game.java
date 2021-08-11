package gameenginepack;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class Game {
	
	private final JFrame window = new JFrame("Game Engine");
	private final ScreenFactory screenFactory;
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

		System.out.println("Window is set up");

		screenFactory = new ScreenFactory(this);
		Taskscheduler taskthread = new Taskscheduler(this);
		keyboardlistener = new Keyboardlistener();
		mousepadlistener = new Mousepadlistener();
		
		//Test = new JLabel("HEEEEY");
		//Test.setBounds(500, 200, 200, 200);
		//Test.setSize(100,100);
		//Test.setLocation(100,100);
		//Test.setVisible(true);
		
		window.add(taskthread);
		//window.add(Test,BorderLayout.SOUTH);
		window.addKeyListener(keyboardlistener);
		window.addMouseListener(mousepadlistener);

		screenFactory.showScreen(new MyScreen(screenFactory));

		System.out.println("Starting Thread");
		new Thread(taskthread).start();
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
