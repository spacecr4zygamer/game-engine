package gameenginepack;


import java.awt.*;


import javax.swing.*;

public class Game {

	private final Workspace workspace;

	private final JFrame window = new JFrame("Game Engine");
	private final ScreenFactory screenFactory;
	private final Keyboardlistener keyboardlistener;
	private final Mousepadlistener mousepadlistener;
	private JPanel Explorer;
	
	public Game(int windowX, int windowY, String title) {

		this.workspace = new Workspace(this);


		window.setSize(windowX,windowY);
		window.setLayout(new BorderLayout());
		System.out.println(window.getLayout());
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setFocusable(true);
		window.setLocationRelativeTo(null);
		window.setTitle(title);
		window.setVisible(true);
		//window.setFocusable(true);

		System.out.println("Window is set up");

		screenFactory = new ScreenFactory(this);
		Taskscheduler taskthread = new Taskscheduler(this);
		keyboardlistener = new Keyboardlistener();
		mousepadlistener = new Mousepadlistener();
		
		Explorer = new JPanel();
		Explorer.setBounds(500, 200, 5000, 500);
		//Test.setSize(100,100);
		//Test.setLocation(100,100);
		Explorer.setVisible(true);
		

		window.add(Explorer,BorderLayout.WEST);
		window.add(taskthread);
		window.addKeyListener(keyboardlistener);
		window.addMouseListener(mousepadlistener);

		screenFactory.showScreen(new MyScreen(screenFactory));


//		try {
//			//Thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
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

	public Workspace getWorkspace() {
		return workspace;
	};

}
