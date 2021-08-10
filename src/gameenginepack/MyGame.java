package gameenginepack;

public class MyGame {
	
	private Game game;
	
	public MyGame() {
		game = new Game(600,400, "My Game");
		game.getScreenFactory().showScreen(new MyScreen(game.getScreenFactory()));
	}
	
	public static void main(String[] args) {
		new MyGame();
	}
	
}
