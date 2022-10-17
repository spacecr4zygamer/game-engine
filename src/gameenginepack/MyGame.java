package gameenginepack;

import gameenginepack.DataTypes.MatrixTwoD;
import gameenginepack.DataTypes.Vector2;

public class MyGame {
	
	private final Game game;
	
	public MyGame() {


//		System.out.println(Math.floorMod(168,16));
//		System.out.println(Math.floorDiv(168,16));
//		System.out.println(168%16);
//		System.out.println(168f*0.0625)

//		MatrixTwoD a = new MatrixTwoD(0,0);
//		MatrixTwoD b = MatrixTwoD.Angles(45);
//
//		CFrame ac = new CFrame(5,5,5);
//		CFrame bc = CFrame.Angles(0,Math.toRadians(90),0);
//		System.out.println(new Vector2(-1,1).unit().toString());
//		System.out.println(a);
//		System.out.println(b);
//
//		MatrixTwoD c = a.mul(b);
//		System.out.println(c);
//		System.out.println(c.mul(b));
//
//		System.out.println(b);
//		//System.out.println(bc);
//		for (int i = 0; i<8;i++) {
//			System.out.println(a);
//			//System.out.println(ac+"<- CFrame");
//			a = a.mul(b);
//			//ac = ac.mul(bc);
//		}


		MatrixTwoD a = MatrixTwoD.Angles(45);
		Vector2 b = new Vector2(1,2);
		System.out.println(a);
		System.out.println(a.mul(b));

		MatrixTwoD e = a.inverse();
		MatrixTwoD test = new MatrixTwoD(5,3).mul(MatrixTwoD.Angles(45));
		System.out.println("This: "+test);
		System.out.println("This inv: "+test.inverse());
		System.out.println(a.mul(e));

		//new Ray2D(new Vector2(0,0),new Vector2(1,0),1).cast(new Vector2(5,5),new Vector2(5,-5));


		game = new Game(800,600, "My Game",Gamestates.Raytracer);


		//System.out.println(1f/2);



//		for (int i=0;i<65;i++) {
//			System.out.println(i+" "+Math.floorMod(i, 16));
//		}

		//Vector3 m = new Vector3(5,2,3), n = new Vector3(0,3,1);

		//System.out.println(m.sub(n).tostring());
		//System.out.println(m.sub(n).normalize().tostring());

		//CFrame a = new CFrame(0,0,0);
		//System.out.println(a.BackVector.tostring());
		//CFrame b = new CFrame(1,0,0);

		//CFrame c = a.mul(b);

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
