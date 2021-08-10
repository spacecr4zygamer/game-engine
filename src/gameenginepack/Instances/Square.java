package gameenginepack.Instances;

import gameenginepack.Vector2;

public class Square extends BasePart {

	public Square() {
		//super("BasePart.Square");
	}
	
	public Square(Vector2 pos) {
		//super("BasePart");
		this.Position = pos;
	}
	
	public Square(Vector2 pos,Vector2 size) {
		//super("BasePart");
		this.Position = pos;
		this.Size = size;
	}
	
	public Square(Vector2 pos,Vector2 size,String BrickColor) {
		//super("BasePart");
		this.Position = pos;
		this.Size = size;
		this.BrickColor = BrickColor;
	}

	@Override
	public void setProperty(String PropertyName, String Property) {
		super.setProperty(PropertyName, Property);
	}
}
