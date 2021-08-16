package gameenginepack;

public class MyGame {
	
	private Game game;
	
	public MyGame() {
		game = new Game(800,600, "My Game",Gamestates.ThreeD);

		//Vector3 a = new Vector3(1,0,0);
		//Vector3 b = new Vector3(0,1,1);
		//a.cross(b);
		//game.getScreenFactory().showScreen(new MyScreen(game.getScreenFactory()));
	}
	
	public static void main(String[] args) {
		new MyGame();
	}
	
}
