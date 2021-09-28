package gameenginepack.Instances;

import gameenginepack.Vector2;

import java.util.ArrayList;

public class Triangle extends BasePart {

	public Triangle() {
		//super("BasePart.Square");
		init();
	}
	
	public Triangle(Vector2 pos) {
		//super("BasePart");
		init();
		this.setPosition(pos);
	}
	
	public Triangle(Vector2 pos,Vector2 size) {
		//super("BasePart");
		init();
		this.setPosition(pos);
		this.setSize(size);
	}
	
	public Triangle(Vector2 pos,Vector2 size,String BrickColor) {
		//super("BasePart");
		init();
		this.setPosition(pos);
		this.setSize(size);
		this.BrickColor = BrickColor;
	}

	private void init() {
		this.Name = "Triangle";
		Vector2 a = new Vector2(-1,1),
				b = new Vector2(1,1),
				c = new Vector2(-1,-1);
		ArrayList<Vector2> Verts = new ArrayList<Vector2>(3);
		Verts.add(0,a);
		Verts.add(1,c);
		Verts.add(2,b);
		this.setVertices(Verts);
	}

	@Override
	public void setProperty(String PropertyName, String Property) {
		super.setProperty(PropertyName, Property);
	}
}
