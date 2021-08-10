package gameenginepack;

public abstract class BasePart extends Instance {

	public Vector2 Position = new Vector2(0,0);
	public boolean Shown = true;
	public double Rotation = 0;
	public String BrickColor = "gray";
	public Vector2 Size = new Vector2(40,20);
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
	public void setPropertyChild(String PropertyName, String Property) {
		// TODO Auto-generated method stub
		switch(PropertyName) {
		case "Position":
			this.Position = Vector2.fromString(Property);
		break;
		case "Size":
			this.Size = Vector2.fromString(Property);
		break;
		case "Velocity":
			this.Velocity = Vector2.fromString(Property);

		break;
		case "BrickColor":
			this.BrickColor = Property;
		break;
		case "CanCollide":
			boolean state = false;
			if (Property.toLowerCase()=="true") {
				state = true;
			}
			this.CanCollide = state; 
		break;
		case "Rotation":
			this.Rotation = Double.parseDouble(Property);
		break;
		
		default:
			setPropertyChildChild(PropertyName,Property);	
		}
	}
	
	public abstract void setPropertyChildChild(String PropertyName, String Property);

	/*@Override
	public void setvar(String var, Object value) {
		// TODO Auto-generated method stub
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
