package gameenginepack.Instances;

import gameenginepack.DataTypes.Vector2;

import java.util.ArrayList;

public class Square extends BasePart {

	public Square() {
		//super("BasePart.Square");
		init();
	}
	
	public Square(Vector2 pos) {
		//super("BasePart");
		init();
		this.setPosition(pos);
	}
	
	public Square(Vector2 pos,Vector2 size) {
		//super("BasePart");
		init();
		this.setPosition(pos);
		this.setSize(size);
	}
	
	public Square(Vector2 pos,Vector2 size,String BrickColor) {
		//super("BasePart");
		init();
		this.setPosition(pos);
		this.setSize(size);
		this.BrickColor = BrickColor;

	}

	private void init() {
		this.Name = "Square";
		Vector2 a = new Vector2(-1,1),
				b = new Vector2(1,1),
				c = new Vector2(-1,-1),
				d = new Vector2(1,-1);
		ArrayList<Vector2> Verts = new ArrayList<Vector2>(4);
		Verts.add(0,a);
		Verts.add(1,b);
		Verts.add(2,d);
		Verts.add(3,c);
		this.setVertices(Verts);
	}

	@Override
	public void setProperty(String PropertyName, String Property) {
		super.setProperty(PropertyName, Property);
	}
}
