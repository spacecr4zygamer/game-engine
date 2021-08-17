package gameenginepack;

public class MyGame {
	
	private Game game;
	
	public MyGame() {
		game = new Game(800,600, "My Game",Gamestates.ThreeD);

		CFrame a = new CFrame(0,0,0);
		//System.out.println(a.BackVector.tostring());
		CFrame b = new CFrame(1,0,0);

		CFrame c = a.mul(b);

		//System.out.println("Test Calculation: "+c.Position.tostring());

		//new Color3(168,168,168).encodetoHex();

		//new Color3().encodetoHex();

		//Vector3 a = new Vector3(1,0,0);
		//Vector3 b = new Vector3(0,1,1);
		//a.cross(b);
		//game.getScreenFactory().showScreen(new MyScreen(game.getScreenFactory()));
	}
	
	public static void main(String[] args) {
		new MyGame();
	}
	
}
