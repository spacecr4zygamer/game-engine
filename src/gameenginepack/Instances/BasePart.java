package gameenginepack.Instances;

import gameenginepack.Color3;
import gameenginepack.Vector2;

public abstract class BasePart extends Geomotry2D {

	//public Vector2 Position = new Vector2(0,0);
	public boolean Shown = true;
	//public double Rotation = 0;
	public String BrickColor = "gray";
	public Color3 Color = new Color3(145,145,145);
	//public Vector2 Size = new Vector2(40,20);
	public Vector2 Velocity = new Vector2(0,0);
	public boolean CanCollide = false;
	
	/*public BasePart(Vector2 pos) {
		this.Position = pos;
	}



	public BasePart(Vector2 pos,Vector2 size) {
		this.Position = pos;
		this.Size = size;
	}

	public BasePart(Vector2 pos,Vector2 size,Color BrickColor) {
		this.Position = pos;
		this.Size = size;
		this.BrickColor = BrickColor;
	}*/

	//public abstract BasePart Touched();

	@Override
	public void setProperty(String PropertyName, String Property) {
		switch(PropertyName) {
			case "Position":
				setPosition(Vector2.fromString(Property));
				break;
			case "Size":
				this.setSize(Vector2.fromString(Property));
				break;
			case "Velocity":
				this.Velocity = Vector2.fromString(Property);
				break;
			case "BrickColor":
				this.BrickColor = Property;
				break;
			case "CanCollide":
				boolean state = Property.toLowerCase() == "true";
				this.CanCollide = state;
				break;
			case "Rotation":
				this.setOrientation(Double.parseDouble(Property));
				break;
			default:
				super.setProperty(PropertyName,Property);
				break;
		}
	}

	/*@Override
	public void setvar(String var, Object value) {
		 try {
	            // Variable abrufen
	            Field f1 = this.getClass().getField(var);
	            // Variable verändern
	            f1.set(this, value);
	        } catch (NoSuchFieldException | IllegalAccessException e) {
	            e.printStackTrace();
	        }
	}*/

}
