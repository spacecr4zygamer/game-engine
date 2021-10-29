package gameenginepack;


import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Game {

    private final Workspace workspace;

    private final JFrame window = new JFrame("Game Engine");
    private final ScreenFactory screenFactory;
    private final Keyboardlistener keyboardlistener;
    private final Mousepadlistener mouselistener;
    public final Enum RenderType;
    private JPanel Explorer;
    private final JDialog jDialog = new JDialog();

    public Game(int windowX, int windowY, String title, Enum VersionD) {

        this.workspace = new Workspace(this);
        this.RenderType = VersionD;

        window.setSize(windowX, windowY);
        //window.setLayout(null);

        //window.
        //System.out.println(window.getLayout());
        //window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setFocusable(true);
        window.setLocationRelativeTo(null);
        window.setTitle(title);
        window.setVisible(true);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        window.setContentPane(contentPane);

        System.out.println("Window is set up");

        screenFactory = new ScreenFactory(this);
        Taskscheduler taskthread = new Taskscheduler(this);
        keyboardlistener = new Keyboardlistener();
        mouselistener = new Mousepadlistener();

        Explorer = new JPanel();
        //Explorer.setVisible(true);
        //Explorer.setSize(100,100);

        //JDialog jDialog = new JDialog();
        jDialog.setTitle("Explorer");
        //jDialog.setModal(true);
        jDialog.setFocusable(true);
        jDialog.setResizable(true);
        jDialog.setVisible(true);
        jDialog.setSize(300, 600);
        jDialog.setLocation(1350, 220);

        JButton jButton = new JButton();
        jButton.setSize(200, 200);
        jButton.setVisible(true);
        //jDialog.setLocationRelativeTo(window);

        //Explorer = new JPanel();
        //Explorer.setBounds(500, 500, 5000, 500);

        //Test.setSize(100,100);
        //Test.setLocation(100,100);
        //Explorer.setVisible(true);


        //window.add(Explorer,BorderLayout.WEST);
        //jDialog.add(taskthread);
        //window.add(jButton,BorderLayout.WEST);
        //contentPane.add(jButton);
        //window.add(taskthread,BorderLayout.CENTER);
        Explorer.setBounds(windowX - 100, 0, 100, windowY);
        Explorer.setBackground(new Color(100, 100, 100));
        taskthread.setBounds(0, 0, windowX - 100, windowY);
        contentPane.add(Explorer);
        contentPane.add(taskthread);

        //window.add(Explorer,BorderLayout.EAST);
        window.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("Please work");
                int xsize = window.getWidth();
                int ysize = window.getHeight();
                taskthread.setBounds(0,0, (int) (xsize*0.8),ysize);
                Explorer.setBounds((int) (xsize*0.8),0,(int) (xsize*0.2),ysize);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                System.out.println("I moved");
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                //System.out.println("HMMMM");
            }
        });
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
    }

    ;

}
