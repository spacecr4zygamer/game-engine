package gameenginepack.Instances;

import gameenginepack.ExplorerTools;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public abstract class Instance {

	protected boolean Archivable;
	protected String Name;

	protected ArrayList<Instance> Children = new ArrayList<>();
	@Nullable protected Instance Parent;



	public void setProperty(String PropertyName, String Property) {
		switch (PropertyName) {
			case "Archivable":
				boolean state = false;
				if (Property.toLowerCase() == "true") {
					state = true;
				}
				this.Archivable = state;
				break;
			case "Name":
				this.Name = Property;
				break;
		}
	}

	public String getName() { return Name; };


	public Instance FindFirstChild(String Name) {
		for (Instance child : this.Children) {
			if (child.getName().equals(Name)) {
				return child;
			}
		}
		return null;
	}
	public void SetParent(@Nullable Instance Parent) {
		this.Parent = Parent;
	}
	public Instance FindFirstChildOfClass(String ClassName) {
		for (Instance child : this.Children) {
			if (child.getClass().getSimpleName().equals(ClassName)) {
				return child;
			}
		}
		return null;
	}
	public Instance FindFirstChildWhichIsA(String Class) {
		for (Instance child : this.Children) {
			if (child.getClass().getName().contains(Class.subSequence(0,Class.length()))) {
				return child; //System.out.println("yes is a subclass of or instance");
			}
		}

		return null;
	}
	public ArrayList<Instance> GetChildren() {
		return this.Children;
	}
	public ArrayList<Instance> GetDescendants() {
		return null;
	}
	public Instance GetParent() {
		return this.Parent;
	}
	public void AddChild(Instance Child) {
		this.Children.add(Child);
	}

	//public abstract void setvar(String var, Object value);
	/* Verändert Variablen
	    *  String var = Variablen Name
	    *  Object value = Neuer Wert der Variable
	    * 
	    public void setvar(String var, Object value) {
	        try {
	            // Variable abrufen
	            Field f1 = this.getClass().getField(var);
	            // Variable verändern
	            f1.set(this, value);
	        } catch (NoSuchFieldException | IllegalAccessException e) {
	            e.printStackTrace();
	        }
	    }

	    public Object getvar(String var) {
	        try {
	            // Variable abrufen
	            Field f1 = this.getClass().getField(var);
	            // Wert zurück geben
	            return f1.get(this);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	}*/
	
}
