package gameenginepack.Instances;

import gameenginepack.Vector2;

public class Triangle extends BasePart {

	public Triangle() {
		//super("BasePart.Square");
	}
	
	public Triangle(Vector2 pos) {
		//super("BasePart");
		this.setPosition(pos);
	}
	
	public Triangle(Vector2 pos,Vector2 size) {
		//super("BasePart");
		this.setPosition(pos);
		this.setSize(size);
	}
	
	public Triangle(Vector2 pos,Vector2 size,String BrickColor) {
		//super("BasePart");
		this.setPosition(pos);
		this.setSize(size);
		this.BrickColor = BrickColor;
	}

	@Override
	public void setProperty(String PropertyName, String Property) {
		super.setProperty(PropertyName, Property);
	}
}
