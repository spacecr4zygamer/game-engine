package gameenginepack;


import java.awt.*;


import javax.swing.*;

public class Game {

	private final Workspace workspace;

	private final JFrame window = new JFrame("Game Engine");
	private final ScreenFactory screenFactory;
	private final Keyboardlistener keyboardlistener;
	private final Mousepadlistener mouselistener;
	public final Enum RenderType;
	private JPanel Explorer;
	private final JDialog jDialog = new JDialog();
	
	public Game(int windowX, int windowY, String title,Enum VersionD) {

		this.workspace = new Workspace(this);
		this.RenderType = VersionD;

		window.setSize(windowX,windowY);
		window.setLayout(new BorderLayout());
		System.out.println(window.getLayout());
		window.setResizable(false);
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
		mouselistener = new Mousepadlistener();

		//JDialog jDialog = new JDialog();
		jDialog.setTitle("Explorer");
		//jDialog.setModal(true);
		jDialog.setFocusable(true);
		jDialog.setResizable(true);
		jDialog.setVisible(true);
		jDialog.setSize(300,600);
		jDialog.setLocation(1350,220);

		JButton jButton = new JButton();
		jButton.setSize(200,200);
		jButton.setVisible(true);
		//jDialog.setLocationRelativeTo(window);

		//Explorer = new JPanel();
		//Explorer.setBounds(500, 500, 5000, 500);

		//Test.setSize(100,100);
		//Test.setLocation(100,100);
		//Explorer.setVisible(true);
		

		//window.add(Explorer,BorderLayout.WEST);
		//jDialog.add(taskthread);
		window.add(taskthread);
		//window.add(jButton,BorderLayout.WEST);
		window.addKeyListener(keyboardlistener);
		window.addMouseListener(mouselistener);

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
		return mouselistener;
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
