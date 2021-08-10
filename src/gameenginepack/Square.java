package gameenginepack;

import java.awt.Color;

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
	public void setPropertyChildChild(String PropertyName, String Property) {
		// TODO Auto-generated method stub
		switch (PropertyName) {
		
		}
	}
	
}
